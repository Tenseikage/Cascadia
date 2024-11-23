package game.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Position(int posX,int posY) {
	public int getX(){
		return posX;
	}
	public int getY(){
		return posY;
	}
	
	public Position droit() {
		return new Position(posX+1, posY);
	}
	
	public Position gauche() {
		return new Position(posX-1, posY);
	}
	
	public Position haut() {
		return new Position(posX, posY+1);
	}
	
	public Position bas() {
		return new Position(posX, posY-1);
	}
	
	public Position updatePosition(String direction){
		Objects.requireNonNull(direction,"Direction obligatoire");
		return switch (direction) {
			case "Haut" -> new Position(posX, posY - 1);
			case "Bas" -> new Position(posX, posY + 1);
			case "Gauche" -> new Position(posX - 1, posY);
			case "Droite" -> new Position(posX + 1, posY);
			default -> throw new IllegalArgumentException("Direction invalide: " + direction);
		};
	}
	public boolean isValid(int maxX, int maxY) {
		return posX >= 0 && posX < maxX && posY >= 0 && posY < maxY;
	}

	public static Position setMaxPos(){
		return new Position(25, 25); // Dimension maximale de la grille
	}

	public static Position fromString(String positionStr) {
		if (positionStr == null || !positionStr.matches("\\(\\d+,\\d+\\)")) {
				throw new IllegalArgumentException("Erreur : Position invalide ou inconnue");
		}
		positionStr = positionStr.replaceAll("[()]", ""); // Supprime les parenthèses
		String[] parts = positionStr.split(",");
		int x = Integer.parseInt(parts[0].trim());
		int y = Integer.parseInt(parts[1].trim());
		return new Position(x, y);
  }  
	
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
