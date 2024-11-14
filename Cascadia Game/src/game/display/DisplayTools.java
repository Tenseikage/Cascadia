package game.display;
import game.logic.Position;

public class DisplayTools {
	private final String[][] gridEnv; // Grille d'affichage de l'environnement
	private final int maxX;
	private final int maxY;

	public DisplayTools(){
		this.maxX = 10;
		this.maxY = 10;
		gridEnv = new String[maxX + 1][maxY + 1];
	}

	public void initGrid(){
		for (int i = 0; i <= maxY; i++) {
			for (int j = 0; j <= maxX; j++) {
				gridEnv[i][j] = "          "; // Espace vide pour les tuiles non placées
			}
	  }
	}

	public void addTileRepresent(int posY,int posX, String tileRepresent){
		gridEnv[posY][posX] = tileRepresent;
	}

	public String[][] getGrid(){
		return gridEnv;
	}
	public Position getMaxDim(){
		return new Position(maxX,maxY);
	}



}
