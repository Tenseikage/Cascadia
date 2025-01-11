package game.graphic;
import game.color.GraphicAnimalColor;
import game.color.GraphicPlaceColor;
import game.logic.Choice;
import game.material.PeerTileToken;
import game.material.Tile;
import game.material.Token;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * 	@author Christophe TARATIBU
 *  This class stores all data relative of the game
 *  @param tiles list containing the tiles of game
 *  @param tokens list containing the tokens of game
 *  @param choiceboard board containing 4 tokens and tiles (a player chooses a peer tile/token)
 */
public record DataGame(ArrayList<Tile> tiles, ArrayList<Token> tokens,Choice choiceboard, ArrayList<ArrayList<PeerTileToken>> startTiles) {
	/**
	 * This constructor initializes the record's fields
	 * @throws IOException
	 */
	public DataGame() throws IOException{
		this(Tile.ExploitCsv(), Token.tokenList(), new Choice(), Tile.startTiles());
	}
  /**
	 * Returns the starting habitat (composed by 3 tiles)
	 * @return list of 3 tiles (start tiles)
	 */
	public ArrayList<PeerTileToken> getBeginTiles(){
		return Tile.getStartiles();
	}
	/**
	 * Creates the choiceBoard which contains the peers tile/token to choose
	 */
  public void createBoard(){
		choiceboard.createChoiceBoard(tiles, tokens,null, 1);
	}
	/**
	 *  Updates the choiceboard when there aren't enough tile/token peers
	 */
	public void updateChoiceBoard(){
		choiceboard.updateChoiceBoard(tiles, tokens, null, 1);

	}
	/**
	 *  Asks the player if he/her wants to return the chosen token
	 * @param token
	 * @return
	 */
	public boolean returnToken(Token token){
		int choice = WindowInfo.choiceReturnToken();
		if(choice == 0){
			tokens.add(token);
			return true;
		} else {
			return false;
		}

	}
	/**
	 * Sets the color on the token following the name of the animal (2 first letters)
	 * @param color
	 * @return color of animal (ANSI code symbolized by GraphicAnimalColor object)
	 */
	public GraphicAnimalColor setColorAnimal(String color){
		Objects.requireNonNull(color, "Erreur : couleur inexistante !");
		return switch (color) {
			case "Ma" -> GraphicAnimalColor.BROWN;
			case "Ta" -> GraphicAnimalColor.TAUPE;
			case "Ro" -> GraphicAnimalColor.PINK;
			case "Bl" -> GraphicAnimalColor.BLUE;
			case "Or" -> GraphicAnimalColor.ORANGE;
			default -> null;
		};
	}
	/**
	 * Sets the color on the tile following the name of place (2 first letters)
	 * @param color
	 * @return color of animal (awt color symbolized by GraphicPlaceColor object)
	 */
	public GraphicPlaceColor setColorPlace(String place){
		Objects.requireNonNull(place, "Erreur : couleur inexistante !");
		return switch (place) {
			case "Mo" -> GraphicPlaceColor.GREY;
			case "Fo" -> GraphicPlaceColor.DARKGREEN;
			case "Pr" -> GraphicPlaceColor.YELLOW;
			case "Ma" -> GraphicPlaceColor.LIGHTGREEN;
			case "Ri" -> GraphicPlaceColor.LIGHTBLUE;
			default -> null;
		};
	}
}   
