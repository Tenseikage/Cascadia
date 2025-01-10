package game.graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;
import com.github.forax.zen.ApplicationContext;
import game.logic.Position;
import game.material.PeerTileToken;
import game.material.Tile;
import game.material.Token;
import game.player.Player;



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
			WindowInfo.messageInfoError("Dépasssement de zone", "Erreur");
		}
	}

	private boolean checkRangeCoord(Position position){
		int coordI = position.getX();
		int coordJ = position.getY();
		//System.out.println("row : " + coordI + "rows : "+ rows);
		//System.out.println("column : " + coordJ + "Columns : "+ column);
		if(coordI >= rows || coordJ >= column){
			WindowInfo.messageInfoError("Dépasssement de zone : Affichage de la tuile impossible", "Erreur");
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

	private boolean isAdjacent(Position position, Player player){
		var listAdjacentPos = position.voisinAdjacent();
		var envPos = player.boardPlayer().getPositions();
		var posi =  listAdjacentPos.stream().filter(pos -> player.boardPlayer().validPos(envPos, pos)).findFirst().isPresent();
		//System.out.println(posi);
		return posi;
	}
  private void drawToken(Graphics2D graphics,int coordI, int coordJ,Token token){
		int smallSquareSize = 75;
		var posX = (int)xFromI(coordI);
		var posY = (int)yFromJ(coordJ);
		int posSmallX = posX + (squareSize - smallSquareSize)/2;
		int posSmallY = posY + (squareSize - smallSquareSize)/2;
		var color = dataGame.setColor(token.color());
		graphics.setColor(color.getColor());
		graphics.fillRect(posSmallX, posSmallY, smallSquareSize, smallSquareSize);
		Fonts.fontManageToken(graphics, posSmallX,posSmallY, smallSquareSize,token,15);

	}

  private void drawTile(Graphics2D graphics,int coordI, int coordJ,Tile tile){
		//var tile = peer.getTile();
		var posX = xFromI(coordI);
		var posY = yFromJ(coordJ);
	  graphics.drawRect((int)posX, (int)posY, squareSize, squareSize);
		Fonts.fontManageTiles(graphics,(int)posX, (int)posY, squareSize, tile,20);

	}

	public void drawEnvTile(Graphics2D graphics,Player player){
		var envPlayer = player.boardPlayer().getEnvironment();
		envPlayer.entrySet().stream().forEach(element -> {
			var position = element.getValue();
			//System.out.println(position);
			var tile = element.getKey().getTile();
			drawTile(graphics, position.getX(), position.getY(), tile);
		});
	}

	public void drawEnvToken(Graphics2D graphics,Player player){
		var envPlayer = player.boardPlayer().getEnvironment();
		envPlayer.entrySet().stream().forEach(element -> {
			var position = element.getValue();
			//System.out.println(position);
			var token = element.getKey().getToken();
			if(token != null){
				drawToken(graphics, position.getX(), position.getY(), token);
			}
		});
	}


	public boolean setTileAndDraw(ApplicationContext context,Player player,int coordI, int coordJ,PeerTileToken peer){
		if(!(coordI == 0 && (coordJ == 0 || coordJ == 1 || coordJ == 2))){
			var position = new Position(coordI, coordJ);
			var envPlayer = player.boardPlayer();
			var envListPos = envPlayer.getPositions();
			var tile = peer.getTile();
		  if (envPlayer.validPos(envListPos, position)){
				WindowInfo.messageInfoError("Tuile déja présente", "Erreur");
				return false;
			} else if (isAdjacent(position, player) && checkRangeCoord(position)){
				  player.boardPlayer().addTilePlayer(tile, position);
					context.renderFrame(graphics -> {
						drawTile(graphics,coordI, coordJ,tile);
					});
					return true;
			} else{
					WindowInfo.messageInfoError("La tuile à poser doit être adjacente aux autres tuiles", "Erreur");
					return false;
			}
		} else{
			WindowInfo.messageInfoError("Tuile déja présente", "Erreur");
			return false;
		}
		
	}
	public boolean setTokenAndDraw(ApplicationContext context,Player player,int coordI, int coordJ,PeerTileToken peer){
		var position = new Position(coordI, coordJ);
		var envPlayer = player.boardPlayer();
		var foundPeer = envPlayer.getKeyByPos(position);
		var token = peer.getToken();
		if(foundPeer != null){
			if(envPlayer.addTokenPlayer(foundPeer, token, position, 1)){
				context.renderFrame(graphics -> {
					drawToken(graphics, coordI, coordJ, token);
				});
				return true;
			} else {
				WindowInfo.messageInfoError("Positionnement impossible", "Erreur");
				return false;
			}
      
		} else {
			WindowInfo.messageInfoError("Aucune tuile trouvée", "Erreur");
			return false;
		}

	}
	public void drawChoices(Graphics2D graphics,int startX, int startY){
		var choice = dataGame.choiceboard().getChoiceBoard();
		for(int j = 0; j < choice.size(); j ++){
			Token token = choice.get(j).getToken();
			var color = dataGame.setColor(token.color());
			graphics.setColor(color.getColor());
			graphics.fillRect(startX, startY + (squareSize + 25) * j, squareSize, squareSize);
			Fonts.fontManageToken(graphics, startX,startY + (squareSize + 25) * j, squareSize, choice.get(j).getToken(),20);
			graphics.setColor(Color.BLACK);
			graphics.drawRect(startX + 175, startY + (squareSize + 25) * j, squareSize, squareSize);
			Fonts.fontManageTiles(graphics, startX + 175,startY + (squareSize + 25) * j, squareSize, choice.get(j).getTile(),15);
		}
	}

	//Draw startTiles env of player at the begining
	private void drawStartTiles(Graphics2D graphics,int startX, int startY,Player player){
		//Tile.startTiles();
		//var startChoice = Tile.getStartiles();
		var starTiles = dataGame.getBeginTiles();
		var envPlayer = player.boardPlayer();
		Objects.requireNonNull(graphics);
		graphics.setColor(Color.BLACK);
		for(int i = 0; i < 3; i ++){
			graphics.drawRect(startX, startY + squareSize * i, squareSize, squareSize);
			Fonts.fontManageTiles(graphics, startX,startY + squareSize * i , squareSize, starTiles.get(i).getTile(),20);
		}
		envPlayer.setEnvironment(envPlayer.choseStartTile(starTiles));

		
	}

	public void updateScreen(ApplicationContext context,Player player){
		var screenInfo = context.getScreenInfo();
    var width = screenInfo.width();
    var height = screenInfo.height();
		context.renderFrame(graphics -> {
			graphics.clearRect(0, 0, width, height);
			drawEnvTile(graphics, player);
			drawEnvToken(graphics, player);
			drawChoices(graphics, 1100, 35);
		});
	}

	public void cleanScreen(ApplicationContext context){
		var screenInfo = context.getScreenInfo();
    var width = screenInfo.width();
    var height = screenInfo.height();
		context.renderFrame(graphics -> {
			graphics.clearRect(0, 0, width, height);
		});

	}
	public static void drawBegin(ApplicationContext context, Player player, GraphicGame graphicGame){
		context.renderFrame(graphics -> {
			//graphics.clearRect(0, 0, width, height);
			graphicGame.drawStartTiles(graphics, 50, 50, player);
		  graphicGame.drawChoices(graphics, 1100, 35);} );
	}



}
