package game.display;
import game.logic.Position;

/**
 * This class is used to display the environment
 *  @author Christophe TARATIBU
 */
public class DisplayTools {
	private final String[][] gridEnv; // Grille d'affichage de l'environnement
	private final int maxX;
	private final int maxY;

	/**
	 * Constructor which initializes the grid
	 */
	public DisplayTools(){
		this.maxX = 5;
		this.maxY = 5;
		gridEnv = new String[maxX + 1][maxY + 1];
	}

	/**
	 * Initializes the grid
	 */
	public void initGrid(){
		for (int i = 0; i <= maxY; i++) {
			for (int j = 0; j <= maxX; j++) {
				gridEnv[i][j] = ""; // Espace vide pour les tuiles non placées
			}
	  }
	}

	/**
	 * Adds a tile to the grid
	 * @param posY position Y of the tile
	 * @param posX position X of the tile
	 * @param tileRepresent the tile to add
	 */
	public void addTileRepresent(int posY,int posX, String tileRepresent){
		gridEnv[posY][posX] = tileRepresent;
	}

	/**
	 * Returns the grid
	 * @return Grid env
	 */
	public String[][] getGrid(){
		return gridEnv;
	}

	/**
	 * Returns the maximum dimensions of the grid
	 * @return Max position
	 */
	public Position getMaxDim(){
		return new Position(maxX,maxY);
	}

	/**
	 * Returns the length of the grid
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
