package game.graphic;
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
		//System.out.println(Tile.getStartiles());
		return Tile.getStartiles();
	}
  public void createBoard(){
		choiceboard.createChoiceBoard(tiles, tokens,null, 1);
	}
	public void updateChoiceBoard(){
		choiceboard.updateChoiceBoard(tiles, tokens, null, 1);

	}
  /*public static void main(String[] args) throws IOException {
		var data = new dataGame();
		
	}*/
	public GraphicAnimalColor setColor(String color){
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
}   
