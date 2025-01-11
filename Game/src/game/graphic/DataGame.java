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

public record DataGame(ArrayList<Tile> tiles, ArrayList<Token> tokens,Choice choiceboard, ArrayList<ArrayList<PeerTileToken>> startTiles) {
	//Initialisation
	public DataGame() throws IOException{
		this(Tile.ExploitCsv(), Token.tokenList(), new Choice(), Tile.startTiles());
	}

	public ArrayList<PeerTileToken> getBeginTiles(){
		return Tile.getStartiles();
	}
  public void createBoard(){
		choiceboard.createChoiceBoard(tiles, tokens,null, 1);
	}
	public void updateChoiceBoard(){
		choiceboard.updateChoiceBoard(tiles, tokens, null, 1);

	}
	public boolean returnToken(Token token){
		int choice = WindowInfo.choiceReturnToken();
		if(choice == 0){
			tokens.add(token);
			return true;
		} else {
			return false;
		}

	}
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
