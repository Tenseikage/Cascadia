package game.graphic;
import java.awt.Graphics2D;
import java.util.Objects;
import com.github.forax.zen.ApplicationContext;
import game.logic.Position;
import game.material.PeerTileToken;
import game.material.Tile;
import game.material.Token;
import game.player.Player;


/**
 * 
 * This class stores all relative datas of graphic
 * Some methods belongs to vincent, author of SimpleGameView.java 
 * @author Christophe TARATIBU
 * @param poScreenX position X on the screen
 * @param poScreenY position Y on the screen
 * @param squareSize Side of the square
 * @param dataGame Data of the game
 * @param rows Max number of rows
 * @param columns Max number of columns
 */
public record GraphicGame(int poScreenX, int poScreenY,int squareSize, DataGame dataGame,int rows, int columns) {
	/**
	 * Author : vincent
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
  /**
	 * Checks if the object position is in the required range
	 * @param position Position of the mouse pointer in the invisible grid
	 * @return boolean 
	 */
	private boolean checkRangeCoord(Position position){
		int coordI = position.getX();
		int coordJ = position.getY();
		if(coordI >= rows || coordJ >= columns){
			WindowInfo.messageInfoError("Dépasssement de zone : Affichage de la tuile impossible", "Erreur");
			return false;

		} else{
			return true;
		}
	}

	/**
	 * Author : vincent
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
	 * Author : vincent
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
	 * Author : vincent
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
	 * Author : vincent
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
	 * Author : vincent
	 * Gets base abscissa for the column of index i.
	 * 
	 * @param i Column index.
	 * @return Base abscissa of the column.
	 */
	private float xFromI(int i) {
		return realCoordFromIndex(i, poScreenX);
	}

	/**
	 * Author : vincent
	 * Gets base ordinate for the row of index j.
	 * 
	 * @param j Row index.
	 * @return Base ordinate of the row.
	 */
	private float yFromJ(int j) {
		return realCoordFromIndex(j, poScreenY);
	}

	/**
	 * verifies if a position is adjacent to an other position
	 * @param position Position (x,y)
	 * @param player Player's data
	 * @return boolean
	 */
	private boolean isAdjacent(Position position, Player player){
		var listAdjacentPos = position.voisinAdjacent();
		var envPos = player.boardPlayer().getPositions();
		return listAdjacentPos.stream().filter(pos -> player.boardPlayer().validPos(envPos, pos)).findFirst().isPresent();
	}
	/**
	 * Draws a token with data written on it, on the screen
	 * @param graphics  Graphics engine that will display the data of token and shape
	 * @param coordI Coordinate I of place on the grid
	 * @param coordJ Coordinate J of place on the grid
	 * @param token Token of the game
	 */
  private void drawToken(Graphics2D graphics,int coordI, int coordJ,Token token){
		int smallSquareSize = 75;
		var posX = (int)xFromI(coordI);
		var posY = (int)yFromJ(coordJ);
		int posSmallX = posX + (squareSize - smallSquareSize)/2;
		int posSmallY = posY + (squareSize - smallSquareSize)/2;
		var color = dataGame.setColorAnimal(token.color());
		graphics.setColor(color.getColor());
		graphics.fillRect(posSmallX, posSmallY, smallSquareSize, smallSquareSize);
		Fonts.fontManageToken(graphics, posSmallX,posSmallY, smallSquareSize,token,15);

	}

	/**
	 * Draws a tile with data written on it, on the screen
	 * @param graphics  Graphics engine that will display the data of tile and shape
	 * @param coordI Coordinate I of place on the grid
	 * @param coordJ Coordinate J of place on the grid
	 * @param tile Tile of the game
	 */
  private void drawTile(Graphics2D graphics,int coordI, int coordJ,Tile tile){
		var posX = xFromI(coordI);
		var posY = yFromJ(coordJ);
		var color = dataGame.setColorPlace(tile.getPlace());
		graphics.setColor(color.getColor());
	  graphics.drawRect((int)posX, (int)posY, squareSize, squareSize);
		graphics.fillRect((int)posX, (int)posY, squareSize, squareSize);
		Fonts.fontManageTiles(graphics,(int)posX, (int)posY, squareSize, tile,20);

	}

	/**
	 * Draws the tiles of player's environment
	 * @param graphics  Graphics engine that will display the data of env
	 * @param player Player's data which contains his/her environment
	 */
	public void drawEnvTile(Graphics2D graphics,Player player){
		var envPlayer = player.boardPlayer().getEnvironment();
		envPlayer.entrySet().stream().forEach(element -> {
			var position = element.getValue();
			var tile = element.getKey().getTile();
			drawTile(graphics, position.getX(), position.getY(), tile);
		});
	}
 
	/**
	 * Draws the tokens of player's environment
	 * @param graphics  Graphics engine that will display the data of environment
	 * @param player Player's data which contains his/her environment
	 */
	public void drawEnvToken(Graphics2D graphics,Player player){
		Objects.requireNonNull(graphics);
		Objects.requireNonNull(player);
		var envPlayer = player.boardPlayer().getEnvironment();
		envPlayer.entrySet().stream().forEach(element -> {
			var position = element.getValue();
			var token = element.getKey().getToken();
			if(token != null){
				drawToken(graphics, position.getX(), position.getY(), token);
			}
		});
	}

  /**
	 * Sets and draw the tile on the environment
	 * @param context using an existing context {@code ApplicationContext}.
	 * @param player Player's data
	 * @param coordI Coordinate I of the grid
	 * @param coordJ Coordinate J of the grid
	 * @param peer Peer of tile and token
	 * @return Boolean which confirm if the tile is set and drawn
	 */
	public boolean setTileAndDraw(ApplicationContext context,Player player,int coordI, int coordJ,PeerTileToken peer){
		Objects.requireNonNull(context);
		Objects.requireNonNull(player);
		Objects.requireNonNull(peer);
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
	/**
	 * Sets and draw the token on the environment
	 * @param context  using an existing context {@code ApplicationContext}.
	 * @param player Player's data
	 * @param coordI Coordinate I of the grid
	 * @param coordJ Coordinate J of the grid
	 * @param peer Peer of tile and token
	 * @return Boolean which confirm if the token is set and drawn
	 */
	public boolean setTokenAndDraw(ApplicationContext context,Player player,int coordI, int coordJ,PeerTileToken peer){
		Objects.requireNonNull(context);
		Objects.requireNonNull(player);
		Objects.requireNonNull(peer);
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
				return dataGame.returnToken(token);
			}
      
		} else {
			WindowInfo.messageInfoError("Aucune tuile trouvée", "Erreur");
			return false;
		}

	}
	/**
	 * Draws the choices (tiles and token) from choiceboard
	 * @param graphics Graphics engine that will display the data of choiceBoard
	 * @param startX Coordinate X of starting
	 * @param startY Coordinate Y of starting
	 */
	public void drawChoices(Graphics2D graphics,int startX, int startY){
		Objects.requireNonNull(graphics);
		var choice = dataGame.choiceboard().getChoiceBoard();
		if(choice.size() <= 2){
			dataGame.choiceboard().updateChoiceBoard(dataGame.tiles(), dataGame.tokens(), null, 1);
			choice =  dataGame.choiceboard().getChoiceBoard();
		}
		for(int j = 0; j < choice.size(); j ++){
			Token token = choice.get(j).getToken();
			var color = dataGame.setColorAnimal(token.color());
			graphics.setColor(color.getColor());
			graphics.fillRect(startX, startY + (squareSize + 25) * j, squareSize, squareSize);
			Fonts.fontManageToken(graphics, startX,startY + (squareSize + 25) * j, squareSize, choice.get(j).getToken(),20);
			var colorPlace = dataGame.setColorPlace(choice.get(j).getTile().getPlace());
			graphics.setColor(colorPlace.getColor());
			graphics.drawRect(startX + 175, startY + (squareSize + 25) * j, squareSize, squareSize);
			graphics.fillRect(startX + 175, startY + (squareSize + 25) * j, squareSize, squareSize);
			Fonts.fontManageTiles(graphics, startX + 175,startY + (squareSize + 25) * j, squareSize, choice.get(j).getTile(),15);
		}
	}

	/**
	 * Draws the starting habitat of player's environment (3 tiles)
	 * @param graphics Graphics engine that will display the data of starting tiles
	 * @param startX Coordinate X of start
	 * @param startY Coordinate Y of start
	 * @param player Player's data
	 */
	private void drawStartTiles(Graphics2D graphics,int startX, int startY,Player player){
		Objects.requireNonNull(graphics);
		Objects.requireNonNull(player);
		var starTiles = dataGame.getBeginTiles();
		var envPlayer = player.boardPlayer();
		Objects.requireNonNull(graphics);
		for(int i = 0; i < 3; i ++){
			var color = dataGame.setColorPlace(starTiles.get(i).getTile().getPlace());
			graphics.setColor(color.getColor());
			graphics.drawRect(startX, startY + squareSize * i, squareSize, squareSize);
			graphics.fillRect(startX, startY + squareSize * i, squareSize, squareSize);
			Fonts.fontManageTiles(graphics, startX,startY + squareSize * i , squareSize, starTiles.get(i).getTile(),20);
		}
		envPlayer.setEnvironment(envPlayer.choseStartTile(starTiles));		
	}
  /**
	 * Update the graphic window
	 * @param context using an existing context {@code ApplicationContext}.
	 * @param player Player's data
	 */
	public void updateScreen(ApplicationContext context,Player player){
		Objects.requireNonNull(context);
		Objects.requireNonNull(player);
		var screenInfo = context.getScreenInfo();
    var width = screenInfo.width();
    var height = screenInfo.height();
		context.renderFrame(graphics -> {
			graphics.clearRect(0, 0, width, height);
			Fonts.drawPlayerName(context, player, 0, 0, 20);
			drawEnvTile(graphics, player);
			drawEnvToken(graphics, player);
			drawChoices(graphics, 1100, 35);
		});
	}

	/**
	 * Clear's the graphic window
	 * @param context using an existing context {@code ApplicationContext}.
	 */
	public void cleanScreen(ApplicationContext context){
		Objects.requireNonNull(context);
		var screenInfo = context.getScreenInfo();
    var width = screenInfo.width();
    var height = screenInfo.height();
		context.renderFrame(graphics -> {
			graphics.clearRect(0, 0, width, height);
		});

	}
	/**
	 * Draws the first state of the game
	 * @param context  using an existing context {@code ApplicationContext}.
	 * @param player Player's data
	 * @param graphicGame Data of graphic game
	 */
	public static void drawBegin(ApplicationContext context, Player player, GraphicGame graphicGame){
		Objects.requireNonNull(context);
		Objects.requireNonNull(player);
		Objects.requireNonNull(graphicGame);
		context.renderFrame(graphics -> {
			graphicGame.drawStartTiles(graphics, 50, 50, player);
		  graphicGame.drawChoices(graphics, 1100, 35);} );
	}
}
