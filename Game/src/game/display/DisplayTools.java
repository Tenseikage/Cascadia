package game.display;
import game.logic.Position;

/**
 * @author Christophe TARATIBU
 * This class is used to display the environment
 */
public class DisplayTools {
	private final String[][] gridEnv; // Grille d'affichage de l'environnement
	private final int maxX;
	private final int maxY;

	public DisplayTools(){
		this.maxX = 10;
		this.maxY = 10;
		gridEnv = new String[maxX + 1][maxY + 1];
	}

	/**
	 * This method initializes the grid
	 */
	public void initGrid(){
		for (int i = 0; i <= maxY; i++) {
			for (int j = 0; j <= maxX; j++) {
				gridEnv[i][j] = ""; // Espace vide pour les tuiles non placées
			}
	  }
	}

	/**
	 * This method adds a tile to the grid
	 * @param posY position Y of the tile
	 * @param posX position X of the tile
	 * @param tileRepresent the tile to add
	 */
	public void addTileRepresent(int posY,int posX, String tileRepresent){
		gridEnv[posY][posX] = tileRepresent;
	}

	/**
	 * This method returns the grid
	 * @return
	 */
	public String[][] getGrid(){
		return gridEnv;
	}

	/**
	 * This method returns the maximum dimensions of the grid
	 * @return
	 */
	public Position getMaxDim(){
		return new Position(maxX,maxY);
	}

	/**
	 * This method returns the length of the grid
	 * @return length
	 */
	public int lengthGrid(){
		int length = 0;
		for(int i = 0; i < maxX; i++){
			for(int j = 0; j < maxY; j++){
				if(!gridEnv[i][j].equals("")){
					length++;
				}
			}
		}
		return length;
	}

}
