package game.logic;
import game.display.Display;
import game.material.Tile;
import game.material.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;



/**
 * This class represents the choice of tiles and tokens
 */
public class Choice {
	private final LinkedHashMap<Tile,Token> choiceBoard = new LinkedHashMap<>();
	/**
	 * This method adds tokens and tiles to the choice board
	 * @param tiles list of tiles of the game
	 * @param chosenTokens 	list of tokens chosen randomly
	 */
	public void addTokensTiles(ArrayList<Tile> tiles,ArrayList<Token> chosenTokens){
		Objects.requireNonNull(tiles, "Error: Null tile list");
		Objects.requireNonNull(chosenTokens, "Error: Null chosen token list");
		for(int i = 0; i < 4; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choiceBoard.put(tiles.remove(indexTile), chosenTokens.get(i));
		}
	}
	/**
	 * This method chooses the starting tiles
	 * @param tokenTiles HashMap of tiles and null tokens
	 * @return LinkedHashMap of tiles and null tokens with tile positions (start habitat)
	 */
	public LinkedHashMap<HashMap<Tile,Token>,Position> choseStartTile(HashMap<Tile,Token> tokenTiles){
		Objects.requireNonNull(tokenTiles, "Data for start tiles is null");
		int i = 0;
		LinkedHashMap<HashMap<Tile,Token>,Position> startTiles = new LinkedHashMap<>(); 
		for (var entry : tokenTiles.entrySet()){
			HashMap<Tile, Token> entryMap = new HashMap<>();
			entryMap.put(entry.getKey(), entry.getValue());
			startTiles.put(entryMap, new Position(0, i));
			i++;
		}
		return startTiles;

	}

	/**
	 * This method updates the choice board after players have chosen 1 tile and 1 token
	 * @param tiles list of tiles of the game
	 * @param chosenTokens list of tokens chosen by the player
	 */
	public void updateTokenTiles(ArrayList<Tile> tiles, ArrayList<Token> chosenTokens){
		Objects.requireNonNull(tiles, "Error : tile list is null");
		Objects.requireNonNull(chosenTokens, "Error : token list is null"); 
		int size = 4 - choiceBoard.size();
		for (int i = 0; i < size; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choiceBoard.put(tiles.remove(indexTile), chosenTokens.get(3 - i));
		}
	}

	/**
	 * This method completes the list of tokens for the next turn
	 * @return ArrayList of tokens
	 */
	public ArrayList<Token> completeTokenList(){
		ArrayList<Token> chosenTokens = new ArrayList<>();
		for(var entry : choiceBoard.entrySet()){
			chosenTokens.add(entry.getValue());
		}
		return chosenTokens;
	}

	/**
	 * This method creates the choice board
	 * @param tiles list of tiles of the game
	 * @param tokens list of tokens of the game
	 * @param display display object
	 * @param scanner scanner object
	 */
	public void createChoiceBoard(ArrayList<Tile> tiles, ArrayList<Token> tokens,Display display, Scanner scanner){
		Objects.requireNonNull(display, "Error: display is null");
		Objects.requireNonNull(scanner, "Error: scanner is null");
		Objects.requireNonNull(tiles, "Error: tile list is null");
		Objects.requireNonNull(tokens, "Error: token list is null");
		ArrayList<Token> chosenTokens = new ArrayList<>();
		chosenTokens = Token.chooseTokens(tokens,chosenTokens);
		addTokensTiles(tiles, chosenTokens);
		System.out.println("\n");
		System.out.println("Choix des jetons et tuiles :");
		display.displayAll(this);
		System.out.println("\n");
		if(Token.checkOvercrowding(chosenTokens, scanner)){
			System.out.println("Surpopulation : choix de nouveaux jetons");
			choiceBoard.clear();
			var discardedTokens = Token.discardTokens(chosenTokens);
			Token.chooseTokens(tokens, chosenTokens); 
			addTokensTiles(tiles, chosenTokens);
			tokens.addAll(discardedTokens);
		}
	}
  
	/**
	 * This method updates the choice board after the players have chosen 1 tile and 1 token
	 * @param tiles list of tiles of the game
	 * @param tokens list of tokens of the game
	 * @param chosenTokens list of tokens chosen by the player
	 * @param scanner scanner object
	 */
	public void updateChoiceBoard(ArrayList<Tile> tiles, ArrayList<Token> tokens,ArrayList<Token> chosenTokens, Scanner scanner){
		Objects.requireNonNull(tiles, "Error: tile list is null");
		Objects.requireNonNull(tokens, "Error: token list is null");
		Objects.requireNonNull(chosenTokens, "Error: chosen token list is null");
		Objects.requireNonNull(scanner, "Error: scanner is null");
		int size = 4 - chosenTokens.size();
		Token.completeTokenList(tokens, chosenTokens, size);
		if(!Token.checkOvercrowding(chosenTokens,scanner)){
			updateTokenTiles(tiles, chosenTokens);
		} else {
			  System.out.println("Surpopulation");
				var discardedTokens = Token.discardTokens(chosenTokens);
				Token.chooseTokens(tokens, chosenTokens);
				tokens.addAll(discardedTokens);
		}		
	}

	/**
	 * This method returns the choice board
	 * @return LinkedHashMap of tiles and tokens
	 */
	public  LinkedHashMap<Tile,Token> getChoiceBoard(){
		return choiceBoard;
	}

	@Override
	public String toString(){
		return choiceBoard.toString();
	}


}

	

