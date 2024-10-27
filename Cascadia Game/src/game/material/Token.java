package game.material;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

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
	public static void shuffeTokens(ArrayList<Token> tokens){
		Collections.shuffle(tokens);
	}

	public static void displayTokens(ArrayList<Token> tokens){
		for(var element: tokens){
			System.out.println(element);
		}
	}

	@Override
	// Affichage du jeton
	public String toString(){
		return "[" + espece + "," + color + "]";
	}


}