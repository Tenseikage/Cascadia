package game.logic;
import game.display.Display;
import game.material.PeerTileToken;
import game.material.Tile;
import game.material.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;



/**
 * This class represents the choice of tiles and tokens
 */
public class Choice {
	private final ArrayList<PeerTileToken> choicesBoard = new ArrayList<>();
	/**
	 * This method adds tokens and tiles to the choice board
	 * @param tiles list of tiles of the game
	 * @param tokens list of tokens created
	 */
	public void addTokensTiles(ArrayList<Tile> tiles,ArrayList<Token> tokens){
		Objects.requireNonNull(tiles, "Error: Null tile list");
		Objects.requireNonNull(tokens, "Error: Null chosen token list");
		for(int i = 0; i < 4; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			//choiceBoard.put(tiles.remove(indexTile), chosenTokens.get(i));
			choicesBoard.add(new PeerTileToken(tiles.remove(indexTile),tokens.get(i)));
		}
	}
	/**
	 * This method chooses the starting tiles,
	 * @param tokenTiles ArrayList of tiles and null tokens
	 * @return HashMap of tiles and null tokens with tile positions (start habitat)
	 */
	public HashMap<PeerTileToken,Position> choseStartTile(ArrayList<PeerTileToken> tokenTiles){
		Objects.requireNonNull(tokenTiles, "Data for start tiles is null");
		int[] index = {0};
		var data = tokenTiles.stream().collect(Collectors.toMap(Function.identity(), _ -> new Position(0,index[0]++)));
		return new HashMap<>(data);
	}
  /**
	 * This method return the list of chosen tokens
	 * @return
	 */
	public ArrayList<Token> listChosenToken(){
    ArrayList<Token> chosenTokens = new ArrayList<>();
		choicesBoard.stream().filter(peer -> peer.getToken() != null).forEach(peer -> chosenTokens.add(peer.getToken()));
		return chosenTokens;
	}

	/**
	 * This method add each token to an associated tile
	 * @param token
	 */
	private void setTokens(ArrayList<Token> chosenTokens){
		AtomicInteger i = new AtomicInteger(0);
		choicesBoard.stream().forEach(peer -> peer.setToken(chosenTokens.get(i.getAndIncrement())));
	}


	/**
	 * This method set each tile associated to tile to null
	 * @param discardedToken
	 */
	private void removeDiscardedTokens(Token discardedToken){
		choicesBoard.stream().filter(peer -> peer.getToken().equals(discardedToken)).forEach(peer -> peer.setToken(null));

	}

	/**
	 * This method updates the choice board after players have chosen 1 tile and 1 token
	 * @param tiles list of tiles of the game
	 * @param chosenTokens list of tokens chosen by the player
	 */
	public void updateTokenTiles(ArrayList<Tile> tiles, ArrayList<Token> tokens){
		Objects.requireNonNull(tiles, "Error : tile list is null");
		Objects.requireNonNull(tokens, "Error : token list is null");
		int size = 4 - choicesBoard.size(); 
		for (int i = 0; i < size; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choicesBoard.add(new PeerTileToken(tiles.remove(indexTile), tokens.get(3 - i)));
		}
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
		var chosenTokens = Token.chooseTokens(tokens,this);
		addTokensTiles(tiles, chosenTokens);
		System.out.println("\n");
		System.out.println("Choix des jetons et tuiles :");
		display.displayAll(this);
		System.out.println("\n");
		if(Token.checkOvercrowding(this, scanner)){
			System.out.println("Surpopulation : choix de nouveaux jetons");
			var discardedTokens = Token.discardTokens(choicesBoard);
			System.out.println(discardedTokens + "discarded tokens");
			removeDiscardedTokens(discardedTokens.get(0));
			var newTokens = Token.chooseTokens(tokens, this); 
			setTokens(newTokens);
			tokens.addAll(discardedTokens);
		}
	}
  /**
	 * This method completes the board after done player choices
	 * @param tiles
	 * @param tokens
	 */
	public void completeTilesTokenList(ArrayList<Tile> tiles,ArrayList<Token> tokens){
		int size = choicesBoard.size();
		for(int i = 0; i < 4 - size; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choicesBoard.add(new PeerTileToken(tiles.remove(indexTile), tokens.get(i)));
		}
	}
  
	/**
	 * This method updates the choice board after the players have chosen 1 tile and 1 token
	 * @param tiles list of tiles of the game
	 * @param tokens list of tokens of the game
	 * @param chosenTokens list of tokens chosen by the player
	 * @param scanner scanner object
	 */
	public void updateChoiceBoard(ArrayList<Tile> tiles, ArrayList<Token> tokens, Scanner scanner){
		Objects.requireNonNull(tiles, "Error: tile list is null");
		Objects.requireNonNull(tokens, "Error: token list is null");
		Objects.requireNonNull(scanner, "Error: scanner is null");
		completeTilesTokenList(tiles, tokens);
		if(!Token.checkOvercrowding(this,scanner)){
			updateTokenTiles(tiles, listChosenToken());
		} else {
			  System.out.println("Surpopulation");
				var discardedTokens = Token.discardTokens(choicesBoard);
				var newTokens  = Token.chooseTokens(tokens, this);
				setTokens(newTokens);
				tokens.addAll(discardedTokens);
		}	
	}

	/**
	 * This method returns the choice board
	 * @return ArrayList of tiles and tokens
	 */
	public ArrayList<PeerTileToken> getChoiceBoard(){
		return choicesBoard;
	}

	public void displayBoard(){
		for(var elem : choicesBoard){
			if(elem.getTile() != null && elem.getToken() != null){
				System.out.println("Tile: " + elem.getTile() + ", Token: " + elem.getToken());
			} else {
				System.out.println("Tile: " + elem.getTile() + ", Token: null ");
			}
		}
	}

	


}

	

