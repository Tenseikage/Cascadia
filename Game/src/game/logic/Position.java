package game.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class which represents a position on the grid
 * @author Christophe TARATIBU
 */
public record Position(int posX,int posY) {

	/**
	 * Gets the x coordinate of the position
	 * @return the x coordinate
	 */
	public int getX(){
		return posX;
	}

	/**
	 * Gets the y coordinate of the position
	 * @return the y coordinate
	 */
	public int getY(){
		return posY;
	}
	
	/**
	 * Updates the position of the player
	 * @param direction the direction to move
	 * @return the new position
	 */
	public Position updatePosition(String direction){
		Objects.requireNonNull(direction);
		return switch (direction) {
			case "Haut" -> new Position(posX, posY - 1);
			case "Bas" -> new Position(posX, posY + 1);
			case "Gauche" -> new Position(posX - 1, posY);
			case "Droite" -> new Position(posX + 1, posY);
			default -> throw new IllegalArgumentException("Direction invalide: " + direction);
		};
	}

	/**
	 * Checks if the position is valid
	 * @param maxX the maximum x coordinate
	 * @param maxY the maximum y coordinate
	 * @return True if the position is valid, false otherwise
	 */
	public boolean isValid(int maxX, int maxY) {
		return posX >= 0 && posX < maxX && posY >= 0 && posY < maxY;
	}

	/**
	 * Sets the maximum position
	 * @return the maximum position
	 */
	public static Position setMaxPos(){
		return new Position(25, 25);
	}

	/**
	 * Gets the position of the player
	 * @param positionStr the position of the player
	 * @return the position of the player
	 */
	public static Position fromString(String positionStr) {
		if (positionStr == null || !positionStr.matches("\\(\\d+,\\d+\\)")) {
				throw new IllegalArgumentException("Erreur : Position invalide ou inconnue");
		}
		positionStr = positionStr.replaceAll("[()]", "");
		String[] parts = positionStr.split(",");
		int x = Integer.parseInt(parts[0].trim());
		int y = Integer.parseInt(parts[1].trim());
		return new Position(x, y);
  }  
	
	/**
	 * Gets the adjacent positions
	 * @return list of the adjacent positions
	 */
	public List<Position> voisinAdjacent() {
		List<Position> list = new ArrayList<>();
		List<Position> validPositions = new ArrayList<>();
    Position north = new Position(posX,posY - 1);
		Position south = new Position(posX, posY + 1);
		Position east = new Position(posX + 1, posY);
		Position west = new Position(posX - 1, posY);
		list.add(north);
		list.add(south);
		list.add(east);
		list.add(west);
		for(var elem : list){
			if (elem.isValid(25, 25)){
				validPositions.add(elem);
			}
		}
		return validPositions;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		Position position = (Position) object;
		return posX == position.getX() && posY == position.getY();
  }

	@Override
	public int hashCode(){
		return Objects.hash(posX,posY);
	}
	

	@Override
	public String toString(){
		return "(" + posX + "," + posY + ")";
	}
}
