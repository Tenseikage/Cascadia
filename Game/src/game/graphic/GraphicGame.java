package game.graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;

import com.github.forax.zen.ApplicationContext;

import game.logic.Position;

public record GraphicGame(int poScreenX, int poScreenY,int squareSize, DataGame dataGame,int rows, int column) {
	/*public static GraphicGame initGame(int poScreenX,int poScreenY, int squareSize, DataGame dataGame){
		Objects.requireNonNull(dataGame);
		return new GraphicGame(poScreenX, poScreenY, squareSize, dataGame);

	}*/

	
	/**
	 * Throws exception if the value is not in the required range.
	 * 
	 * @param min   Minimal acceptable value.
	 * @param value Value being tested.
	 * @param max   Maximal acceptable value.
	 */
	private static void checkRange(double min, double value, double max) {
		if (value < min || value > max) {
			WindowInfo.messageInfo("Dépasssement de zone", "Erreur");
		}
	}

	private boolean checkRangeCoord(Position position){
		int coordI = position.getX();
		int coordJ = position.getY();
		//System.out.println("row : " + coordI + "rows : "+ rows);
		//System.out.println("column : " + coordJ + "Columns : "+ column);
		if(coordI >= rows || coordJ >= column){
			WindowInfo.messageInfo("Dépasssement de zone : Affichage de la tuile impossible", "Erreur");
			return false;

		} else{
			//System.out.println("oui");
			return true;
		}
	}

	/**
	 * Gets row or column number based on the given coordinate and the origin of the
	 * display area for this coordinate.
	 * 
	 * @param coord  Coordinate whose row / column we want to know.
	 * @param origin Origin coordinate of the display area.
	 * @return Row / column index that was pointed at by coord.
	 */
	private int indexFromRealCoord(float coord, int origin) {
		return (int) ((coord - origin) / squareSize);
	}

	/**
	 * Transforms a real y-coordinate into the index of the corresponding line.
	 * 
	 * @param y a float y-coordinate
	 * @return the index of the corresponding line.
	 * @throws IllegalArgumentException if the float coordinate does not fit in the
	 *                                  game board.
	 */
	public int lineFromY(float y) {
		checkRange(poScreenY, y, y + squareSize);
		return indexFromRealCoord(y, poScreenY);
	}

	/**
	 * Transforms a real x-coordinate into the index of the corresponding column.
	 * 
	 * @param x a float x-coordinate
	 * @return the index of the corresponding column.
	 * @throws IllegalArgumentException if the float coordinate does not fit in the
	 *                                  game board.
	 */
	public int columnFromX(float x) {
		checkRange(poScreenX, x, x + squareSize);
		return indexFromRealCoord(x, poScreenX);
	}

	/**
	 * Gets base coordinate for the given row /column.
	 * 
	 * @param index  Index of the row / column.
	 * @param origin Base coordinate of the display area.
	 * @return Base coordinate of the row / column.
	 */
	private float realCoordFromIndex(int index, int origin) {
		return origin + index * squareSize;
	}

	/**
	 * Gets base abscissa for the column of index i.
	 * 
	 * @param i Column index.
	 * @return Base abscissa of the column.
	 */
	private float xFromI(int i) {
		return realCoordFromIndex(i, poScreenX);
	}

	/**
	 * Gets base ordinate for the row of index j.
	 * 
	 * @param j Row index.
	 * @return Base ordinate of the row.
	 */
	private float yFromJ(int j) {
		return realCoordFromIndex(j, poScreenY);
	}
	private boolean isAdjacent(Position position, DataPlayer dataPlayer){
		var listAdjacentPos = position.voisinAdjacent();
		var envPos = dataPlayer.envPlayer().getPositions();
		var posi =  listAdjacentPos.stream().filter(pos -> dataPlayer.envPlayer().validPos(envPos, pos)).findFirst().isPresent();
		System.out.println(posi);
		return posi;
	}

	public boolean drawTile(Graphics2D graphics,DataPlayer dataPlayer,int coordI, int coordJ){
		//Ignore the start Tiles
		if(!(coordI == 0 && (coordJ == 0 || coordJ == 1 || coordJ == 2))){
			var position = new Position(coordI, coordJ);
			var envPlayer = dataPlayer.envPlayer();
			var envListPos = envPlayer.getPositions();
		  if (envPlayer.validPos(envListPos, position)){
				WindowInfo.messageInfo("Tuile déja présente", "Erreur");
				return true;
			} else if (isAdjacent(position, dataPlayer) && checkRangeCoord(position)){
					var posX = xFromI(coordI);
					var posY = yFromJ(coordJ);
					System.out.println(position);
					graphics.drawRect((int)posX, (int)posY, squareSize, squareSize);
					return true;
			} else{
					WindowInfo.messageInfo("La tuile à poser doit être adjacente aux autres tuiles", "Erreur");
					return false;
			}
		} else{
			WindowInfo.messageInfo("Tuile déja présente", "Erreur");
			return true;
		}
		
	}

	//Draw startTiles env of player at the begining
	private void drawStartTiles(Graphics2D graphics,int startX, int startY,DataPlayer dataPlayer, DataGame dataGame){
		//Tile.startTiles();
		//var startChoice = Tile.getStartiles();
		var starTiles = dataGame.getBeginTiles();
		Objects.requireNonNull(graphics);
		graphics.setColor(Color.RED);
		for(int i = 0; i < 3; i ++){
			graphics.drawRect(startX, startY + squareSize * i, squareSize, squareSize);
			Fonts.fontManageTiles(graphics, startX,startY + squareSize * i , squareSize, starTiles.get(i),20);
		}
		dataPlayer.start(starTiles);

		
	}
	public static void drawAll(ApplicationContext context, DataGame dataGame, DataPlayer dataPlayer, GraphicGame graphicGame){
		context.renderFrame(graphics -> graphicGame.drawStartTiles(graphics, 50, 50, dataPlayer, dataGame) );
	}

}
