package game.logic;
import game.graphic.WindowInfo;
import game.material.PeerTileToken;
import game.material.Tile;
import game.material.Token;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * This class represents the choice of tiles and tokens
 * @author Christophe TARATIBU
 */
public class Choice {
	private final ArrayList<PeerTileToken> choicesBoard = new ArrayList<>();
	/**
	 * Adds tokens and tiles to the choice board
	 * @param tiles list of tiles of the game
	 * @param tokens list of tokens created
	 */
	public void addTokensTiles(ArrayList<Tile> tiles,ArrayList<Token> tokens){
		Objects.requireNonNull(tiles);
		Objects.requireNonNull(tokens);
		for(int i = 0; i < 4; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choicesBoard.add(new PeerTileToken(tiles.remove(indexTile),tokens.get(i)));
		}
	}
	
  /**
	 * Return the list of chosen tokens
	 * @return Return list of chosenTokens
	 */
	public ArrayList<Token> listChosenToken(){
    ArrayList<Token> chosenTokens = new ArrayList<>();
		choicesBoard.stream().filter(peer -> peer.getToken() != null).forEach(peer -> chosenTokens.add(peer.getToken()));
		return chosenTokens;
	}

	/**
	 * Adds each token to an associated tile
	 * @param chosenTokens list of tokens alone (They're four)
	 */
	private void setTokens(ArrayList<Token> chosenTokens){
		Objects.requireNonNull(chosenTokens);
		AtomicInteger i = new AtomicInteger(0);
		choicesBoard.stream().forEach(peer -> peer.setToken(chosenTokens.get(i.getAndIncrement())));
	}


	/**
	 * Set each tile associated to tile to null
	 * @param discardedToken Discarded token
	 */
	private void removeDiscardedTokens(Token discardedToken){
		choicesBoard.stream().filter(peer -> peer.getToken().equals(discardedToken)).forEach(peer -> peer.setToken(null));

	}

	/**
	 * Updates the choice board after players have chosen 1 tile and 1 token
	 * @param tiles list of tiles of the game
	 * @param tokens list of tokens of game
	 */
	public void updateTokenTiles(ArrayList<Tile> tiles, ArrayList<Token> tokens){
		Objects.requireNonNull(tiles);
		Objects.requireNonNull(tokens);
		int size = 4 - choicesBoard.size(); 
		for (int i = 0; i < size; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choicesBoard.add(new PeerTileToken(tiles.remove(indexTile), tokens.get(size - i)));
		}
	}

	/**
	 * Creates the choice board
	 * @param tiles list of tiles of the game
	 * @param tokens list of tokens of the game
	 * @param scanner scanner object
	 * @param displayMode Display mode(0 : terminal, 1 : graphic)
	 */
	public void createChoiceBoard(ArrayList<Tile> tiles, ArrayList<Token> tokens, Scanner scanner, int displayMode){
		Objects.requireNonNull(tiles);
		Objects.requireNonNull(tokens);
		var chosenTokens = Token.chooseTokens(tokens,this);
		addTokensTiles(tiles, chosenTokens);
		if (displayMode == 0){
			System.out.println("\n");
			if(Token.checkOvercrowding(this, scanner,displayMode)){
				System.out.println("Surpopulation : choix de nouveaux jetons");
				var discardedTokens = Token.discardTokens(choicesBoard);
				removeDiscardedTokens(discardedTokens.get(0));
				var newTokens = Token.chooseTokens(tokens, this); 
				setTokens(newTokens);
				tokens.addAll(discardedTokens);
		  }
		}else if (displayMode == 1){
			if(Token.checkOvercrowding(this,null,displayMode)){
				WindowInfo.messageInfoError("Surpopulation : choix de nouveaux jetons","Surpopulation");
				var discardedTokens = Token.discardTokens(choicesBoard);
				removeDiscardedTokens(discardedTokens.get(0));
				var newTokens = Token.chooseTokens(tokens, this); 
				setTokens(newTokens);
				tokens.addAll(discardedTokens);
		  }
		}
	}
  /**
	 * Completes the board after done player choices
	 * @param tiles List of tiles
	 * @param tokens List of tokens
	 */
	public void completeTilesTokenList(ArrayList<Tile> tiles,ArrayList<Token> tokens){
		Objects.requireNonNull(tiles);
		Objects.requireNonNull(tokens);
		int size = choicesBoard.size();
		for(int i = 0; i < 4 - size; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choicesBoard.add(new PeerTileToken(tiles.remove(indexTile), tokens.get(i)));
		}
	}
  
	/**
	 * Updates the choice board after the players have chosen 1 tile and 1 token
	 * @param tiles list of tiles of the game
	 * @param tokens list of tokens of the game
	 * @param scanner scanner object
	 * @param displayMode Display mode(0 : terminal, 1 : graphic)
	 */
	public void updateChoiceBoard(ArrayList<Tile> tiles, ArrayList<Token> tokens, Scanner scanner, int displayMode){
		Objects.requireNonNull(tiles);
		Objects.requireNonNull(tokens);
		if(choicesBoard.size() <= 2){
			if(displayMode == 0){
				completeTilesTokenList(tiles, tokens);
				if(!Token.checkOvercrowding(this,scanner,displayMode)){
					updateTokenTiles(tiles, listChosenToken());
				} else {
						System.out.println("Surpopulation");
						var discardedTokens = Token.discardTokens(choicesBoard);
						var newTokens  = Token.chooseTokens(tokens, this);
						setTokens(newTokens);
						tokens.addAll(discardedTokens);
				}	
			} else{
				completeTilesTokenList(tiles, tokens);
				if(!Token.checkOvercrowding(this,scanner,displayMode)){
					updateTokenTiles(tiles, listChosenToken());
				} else {
					WindowInfo.messageInfoError("Surpopulation : choix de nouveaux jetons","Surpopulation");
					var discardedTokens = Token.discardTokens(choicesBoard);
					var newTokens  = Token.chooseTokens(tokens, this);
					setTokens(newTokens);
					tokens.addAll(discardedTokens);
				}	
			}
		}
	}

	/**
	 * Returns the choice board
	 * @return ArrayList of tiles and tokens
	 */
	public ArrayList<PeerTileToken> getChoiceBoard(){
		return choicesBoard;
	}
  
	/**
	 * Displays the board
	 */
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

	

