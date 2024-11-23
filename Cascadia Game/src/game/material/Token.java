package game.material;
import game.player.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Class which represents a token of the game
 */
public record Token(String espece, String color) {
	public Token(String espece,String color){
		this.espece = espece;
		this.color = color;
		Objects.requireNonNull(espece,"Specie allowed for the token !");
		Objects.requireNonNull(color, "Color allowed for the token");
	}

	/**
	 * Method to create a list of tokens
	 * @return the list of tokens
	 */
	public static ArrayList<Token> tokenList(){

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
	 * Method to fill the list of tokens
	 * @param tokens list of tokens
	 * @param chosenTokens list of chosen tokens
	 * @return the list of chosen tokens
	 */
	public static ArrayList<Token> fillTokenList(ArrayList<Token> tokens, ArrayList<Token> chosenTokens){
		Objects.requireNonNull(tokens, "Error : Null token list");
		Objects.requireNonNull(chosenTokens, "Error : Null chosen token list");
		for (int i = 0; i < 4; i++){
			Random random = new Random();
			int indexToken = random.nextInt(tokens.size());
			chosenTokens.add(tokens.remove(indexToken));
		}
		return chosenTokens;
	}

	/**
	 * Method to complete the list of tokens
	 * @param tokens list of tokens
	 * @param chosenTokens list of chosen tokens
	 * @param size size of the list
	 * @return the list of chosen tokens
	 */
	public static ArrayList<Token> completeTokenList(ArrayList<Token> tokens, ArrayList<Token> chosenTokens,int size){
		Objects.requireNonNull(tokens, "Error : Null token list");
		Objects.requireNonNull(chosenTokens, "Error : Null chosen token list");
		for (int i = 0; i < size; i++) {
			Random random = new Random();
			int indexToken = random.nextInt(tokens.size());
			chosenTokens.add(tokens.remove(indexToken));		
		}
		return chosenTokens;
	}


	/**
	 * Method to change the list of tokens when there is overcrowding
	 * @param tokens list of tokens
	 * @param chosenTokens list of chosen tokens
	 * @return the list of chosen tokens
	 */
	public static ArrayList<Token> changeTokenList(ArrayList<Token> tokens,ArrayList<Token> chosenTokens){
		Objects.requireNonNull(tokens, "Error : Null token list");
		Objects.requireNonNull(chosenTokens, "Error : Null chosen token list");
		for (int i = 1; i < 4; i++){
			Random random = new Random();
			int indexToken = random.nextInt(tokens.size());
			chosenTokens.add(tokens.remove(indexToken));
		}
		return chosenTokens;
	}

	/**
	 * Method to choose the tokens
	 * @param tokens list of tokens
	 * @param chosenTokens list of chosen tokens
	 * @return the list of chosen tokens
	 */
	public static ArrayList<Token> chooseTokens(ArrayList<Token> tokens,ArrayList<Token> chosenTokens){
		Objects.requireNonNull(tokens, "Error : Null token list");
		Objects.requireNonNull(chosenTokens, "Error : Null chosen token list");
		if(tokens.isEmpty()){
			throw new IllegalArgumentException("Erreur : Liste vide");
		}
		if(chosenTokens.isEmpty()){
			chosenTokens =  Token.fillTokenList(tokens, chosenTokens);
		}else if (chosenTokens.size() <= 2){
			int size = 4 - chosenTokens.size();
			chosenTokens = Token.completeTokenList(tokens, chosenTokens, size);
		}
		else{
			chosenTokens = Token.changeTokenList(tokens, chosenTokens);
		}
		return chosenTokens;
	}

	/**
	 * Method to discard the tokens
	 * @param chosenTokens list of chosen tokens
	 * @return the list of discarded tokens
	 */
	public static ArrayList<Token> discardTokens(ArrayList<Token> chosenTokens){
		Objects.requireNonNull(chosenTokens, "Error : Null chosen token list");
		ArrayList<Token> discardedTokens = new ArrayList<>();
		if(chosenTokens.isEmpty()){
			throw new IllegalArgumentException("Error : Empty list of tokens");
		}
		Set<Token> uniqueTokens = new HashSet<>();
		for(var element  : chosenTokens){
			if(!uniqueTokens.add(element)){
				discardedTokens.add(element);
			}
		}
		discardedTokens.add(discardedTokens.get(0));
	  chosenTokens.removeAll(discardedTokens); 
		return discardedTokens;

	}

	/**
	 * Method to shuffle the tokens
	 * @param tokens list of tokens
	 */
	public static void shuffeTokens(ArrayList<Token> tokens){
		Collections.shuffle(tokens);
	}

	/**
	 * Method to count the occurrences of the tokens
	 * @param tokens list of tokens
	 * @return the map of occurrences
	 */
	public static Map<Token,Integer> countOccurrences(ArrayList<Token> tokens){
		Objects.requireNonNull(tokens, "Error : Null token list");
		Map<Token,Integer> mapOccur = new HashMap<>();
		for(var item : tokens){
			mapOccur.put(item,mapOccur.getOrDefault(item, 0) + 1);
		}
		return mapOccur;
				
	}

	/**
	 * Method to check if there is overcrowding
	 * @param chosenTokens list of chosen tokens
	 * @param scanner scanner to read the player's choice
	 * @return true if there is overcrowding, false otherwise
	 */
	public static boolean checkOvercrowding(ArrayList<Token> chosenTokens, Scanner scanner){
		Objects.requireNonNull(chosenTokens, "Error : Null chosen token list");
		Objects.requireNonNull(scanner, "Error : Null scanner");
		if (chosenTokens.isEmpty()){
			return false;
		}
		var mapTokens = countOccurrences(chosenTokens);
	  int sameToken = Collections.max(mapTokens.values());
    return switch (sameToken) {
        case 3 -> Player.choiceKeepOrPass(scanner);
        case 4 -> true;
        default -> false;
    }; 
	}

	@Override
	public String toString(){
		return "[" + espece.substring(0, 2) + "," + color.substring(0,2) + "]";
	}

	/**
	 * Method to return the token in the list of tokens (bag)
	 * @param token the token to return
	 * @param tokens the list of tokens
	 * @return the list of tokens
	 */
	public static ArrayList<Token> returnToken(Token token, ArrayList<Token> tokens){
		tokens.add(token);
		return tokens;
	}

	


}