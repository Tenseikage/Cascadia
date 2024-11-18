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
	
	public List<Position> voisin() {
		List<Position> list = new ArrayList<>();
		list.add(new Position(posX-1,posY));
		list.add(new Position(posX+1,posY));
		list.add(new Position(posX,posY-1));
		list.add(new Position(posX,posY+1));
		return list;
	}

	@Override
	public String toString(){
		return "(" + posX + "," + posY + ")";
	}
}
