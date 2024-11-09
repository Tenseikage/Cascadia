package game.material;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

// Création des jetons du jeux
public record Token(String espece, String color) {
	public Token(String espece,String color){
		this.espece = espece;
		this.color = color;
		Objects.requireNonNull(espece,"Specie allowed for the token !");
		Objects.requireNonNull(color, "Color allowed for the token");
	}
	public static ArrayList<Token> tokenList(){
		// Création des jetons de faune
		var tokens = new ArrayList<Token>();
		for (int i = 0; i < 100; i++) {
			if (i < 20) {
				tokens.add(new Token("Ours", "Marron"));
			} else if ((i >= 20) && (i < 40)){
				tokens.add(new Token("Wapiti","Taupe"));
			} else if ((i >= 40) && (i < 60)){
				tokens.add(new Token("Saumon","Rose"));
					
			} else if ((i >= 60) && (i < 80)){
				tokens.add(new Token("Buse","Bleu"));
			} else {
				tokens.add(new Token("Renard","Orange"));
			}
		}
		return tokens;	
		
	}

	public static ArrayList<Token> chooseTokens(ArrayList<Token> tokens,ArrayList<Token> chosenTokens){
		Objects.requireNonNull(tokens, "Erreur : liste de tokens nulle !");
		if(tokens.isEmpty()){
			throw new IllegalArgumentException("Erreur : Liste vide");
		}
		if(chosenTokens.isEmpty()){
			for (int i = 0; i < 4; i++){
				//Liste contenant les 4 jetons
				Random random = new Random();
				int indexToken = random.nextInt(tokens.size());
				chosenTokens.add(tokens.remove(indexToken));
			}
		} else{
				for (int i = 1; i < 4; i++){
					//Nouveux jetons s'il y a surpopulation
					Random random = new Random();
					int indexToken = random.nextInt(tokens.size());
					chosenTokens.add(tokens.remove(indexToken));
				}
		}
		return chosenTokens;

	}

	public static ArrayList<Token> discardTokens(ArrayList<Token> chosenTokens){
		Objects.requireNonNull(chosenTokens, "Erreur : liste de tokens nulle !");
		ArrayList<Token> discardedTokens = new ArrayList<>();
		if(chosenTokens.isEmpty()){
			throw new IllegalArgumentException("Erreur : Liste vide");
		}
		Set<Token> uniqueTokens = new HashSet<>();
		for(var element  : chosenTokens){
			if(!uniqueTokens.add(element)){
				discardedTokens.add(element);
			}
		}
		discardedTokens.add(discardedTokens.get(0));
	  chosenTokens.removeAll(discardedTokens); // écartement des jetons
	  //System.out.println(discardedTokens);
		System.out.println("Surpopulation");
		return discardedTokens;

	}
	public static void shuffeTokens(ArrayList<Token> tokens){
		//Mélange des jetons faune
		Collections.shuffle(tokens);
	}

	public static boolean checkOvercrowding(ArrayList<Token> chosenTokens){
		//Vérifie s'il y a surpopulation
		if (chosenTokens.isEmpty()){
			return false;
		}
		var firstElem = chosenTokens.get(0);
		int sameToken = 0;
		for(var token: chosenTokens){
			if(firstElem.equals(token)){
				sameToken++;
			}
		}
    return switch (sameToken) {
				// Cas ou le joueur decide d'écarter les jetons
				// A faire pour le case 3
        case 3 -> true;
        case 4 -> true;
        default -> false;
    }; 
    
	}

	@Override
	// Affichage du jeton
	public String toString(){
		return "[" + espece + "," + color + "]";
	}


}