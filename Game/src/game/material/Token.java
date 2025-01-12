package game.material;
import game.logic.Choice;
import game.player.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


/**
 * Class which represents a token of the game
 * @author Christophe TARATIBU
 * @param espece 2 letters of the animal
 * @param color Color of the token
 */
public record Token(String espece, String color) {
	/**
	 * Creates the token
	 * @param espece Animal (2 first letters of animal name)
	 * @param color Color of token
	 */
	public Token(String espece,String color){
		Objects.requireNonNull(espece);
		Objects.requireNonNull(color);
		this.espece = espece;
		this.color = color;
	}

	/**
	 *Creates a list of tokens
	 * @return the list of tokens
	 */
	public static List<Token> tokenList(){
		var tokens = new ArrayList<Token>();
		for (int i = 0; i < 100; i++) {
			if (i < 20) {
				tokens.add(new Token("Ou", "Ma"));
			} else if ((i >= 20) && (i < 40)){
				tokens.add(new Token("Wa","Ta"));
			} else if ((i >= 40) && (i < 60)){
				tokens.add(new Token("Sa","Ro"));
					
			} else if ((i >= 60) && (i < 80)){
				tokens.add(new Token("Bu","Bl"));
			} else {
				tokens.add(new Token("Re","Or"));
			}
		}
		return tokens;	
	}

	/**
	 * Fills the list of tokens
	 * @param tokens list of tokenspeers
	 * @param chosenTokens list of chosen tokens
	 * @return the list of chosen tokens
	 */
	public static List<Token> fillTokenList(List<Token> tokens, List<Token> chosenTokens){
		Objects.requireNonNull(tokens);
		Objects.requireNonNull(chosenTokens);
		for (int i = 0; i < 4; i++){
			Random random = new Random();
			int indexToken = random.nextInt(tokens.size());
			chosenTokens.add(tokens.remove(indexToken));
		}
		return chosenTokens;
	}


	/**
	 * Changes the list of tokens when there is overcrowding
	 * @param board Choice board of the game
	 * @param tokens list of tokens
	 * @return the list of chosen tokens
	 */
	public static List<Token> changeTokenList(Choice board ,List<Token> tokens){
		Objects.requireNonNull(tokens);
		Objects.requireNonNull(board);
		var chosenTokens = board.listChosenToken();
		tokens.addAll(chosenTokens);
		chosenTokens.clear();
		for (int i = 0; i < 4; i++){
			Random random = new Random();
			int indexToken = random.nextInt(tokens.size());
			chosenTokens.add(tokens.remove(indexToken));
		}
		return chosenTokens;
	}

	/**
	 * Completes the list of tokens
	 * @param tokens list of tokens
	 * @param chosenTokens list of chosen tokens
	 * @param size size of the list
	 * @return the list of chosen tokens
	 */
	public static List<Token> completeTokenList(List<Token> tokens,List<Token> chosenTokens,int size){
		Objects.requireNonNull(tokens);
		Objects.requireNonNull(chosenTokens);
		for (int i = 0; i < size; i++) {
			Random random = new Random();
			int indexToken = random.nextInt(tokens.size());
			chosenTokens.add(tokens.remove(indexToken));		
		}
		return chosenTokens;
	}

	/**
	 * Chooses the tokens
	 * @param tokens list of tokens
	 * @param board Choice board of the game
	 * @return the list of chosen tokens
	 */
	public static List<Token> chooseTokens(List<Token> tokens, Choice board){
		Objects.requireNonNull(tokens);
		Objects.requireNonNull(board);
		if(tokens.isEmpty()){
			throw new IllegalArgumentException("Error: empty list");
		}
		var chosenTokens = board.listChosenToken();
		if(chosenTokens.isEmpty()){
			chosenTokens =  Token.fillTokenList(tokens, chosenTokens);
		}else if (chosenTokens.size() <= 2){
			int size = 4 - chosenTokens.size();
			chosenTokens = Token.completeTokenList(tokens, chosenTokens, size);
		}
		else{
			chosenTokens = Token.changeTokenList(board,tokens);
		}
		return chosenTokens;
	}

	/**
	 * Discards the tokens
	 * @param peer list of peers (only tokens)
	 * @return the list of discarded tokens
	 */
	public static List<Token> discardTokens(List<PeerTileToken> peer){
		Objects.requireNonNull(peer);
		ArrayList<Token> discardedTokens = new ArrayList<>();
		if(peer.isEmpty()){
			throw new IllegalArgumentException("Error : Empty list of tokens");
		}
		Set<Token> uniqueTokens = new HashSet<>();
		for(var element : peer){
			var token  = element.getToken();
			if(!uniqueTokens.add(token)){
				discardedTokens.add(token);
			}
		}
		discardedTokens.add(discardedTokens.get(0));
	  //set to null;
		return discardedTokens;

	}

	/**
	 * Shuffles the tokens
	 * @param tokens list of tokens
	 */
	public static void shuffeTokens(List<Token> tokens){
		Collections.shuffle(tokens);
	}

	/**
	 * Counts the occurrences of the tokens
	 * @param tokens list of tokens
	 * @return the map of occurrences
	 */
	public static Map<Token,Integer> countOccurrences(List<Token> tokens){
		Objects.requireNonNull(tokens);
		Map<Token,Integer> mapOccur = new HashMap<>();
		for(var item : tokens){
			mapOccur.put(item,mapOccur.getOrDefault(item, 0) + 1);
		}
		return mapOccur;
				
	}

	/**
	 * Check if there's overcrowding
	 * @param board Choice board
	 * @param scanner scanner to read the player's choice
	 * @param displayMode Display moode (terminal 0 or 1 graphic)
	 * @return true if there is overcrowding, false otherwise
	 */
	public static boolean checkOvercrowding(Choice board, Scanner scanner, int displayMode){
		Objects.requireNonNull(board);
		var peer = board.getChoiceBoard();
		if (peer.isEmpty()){
			return false;
		}
		var mapTokens = countOccurrences(board.listChosenToken());
	  int sameToken = Collections.max(mapTokens.values());
    return switch (sameToken) {
			case 3 -> Player.choiceKeepOrPass(scanner,displayMode); 
			case 4 -> true;
			default -> false;
	};
	}

	@Override
	public String toString(){
		return "[" + espece.substring(0, 2) + "," + color.substring(0,2) + "]";
	}

	/**
	 * Returns the token in the list of tokens (bag)
	 * @param token the token to return
	 * @param tokens the list of tokens
	 * @return the list of tokens
	 */
	public static List<Token> returnToken(Token token, List<Token> tokens){
		tokens.add(token);
		return tokens;
	}

	


}