package game.material;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Random;

public class Choice {
	//Environnement du joueur contenant les tuiles et jetons
	private final LinkedHashMap<Tile,Token> choiceBoard = new LinkedHashMap<>();		

	public void addTokensTiles(ArrayList<Tile> tiles,ArrayList<Token> chosenTokens){
		for(int i = 0; i < 4; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choiceBoard.put(tiles.remove(indexTile), chosenTokens.get(i));
		}
	}

	public void createChoiceBoard(ArrayList<Tile> tiles, ArrayList<Token> tokens){
		ArrayList<Token> chosenTokens = new ArrayList<>();
		Objects.requireNonNull(tiles, "Erreur liste de tuiles nulle !");
		Objects.requireNonNull(tokens, "Erreur : liste de tokens nulle !"); // Environnement du joueur
		chosenTokens = Token.chooseTokens(tokens,chosenTokens);
		//System.out.println(chosenTokens + " : avant l'ajout");
		if(!Token.checkOvercrowding(chosenTokens)){
		  addTokensTiles(tiles, chosenTokens);
			System.out.println("Pas surpopulation");
		} else {
				var discardedTokens = Token.discardTokens(chosenTokens);
				Token.chooseTokens(tokens, chosenTokens); // choix des nouveaux tokens après surpopulation
				tokens.addAll(discardedTokens);
		}		
		//System.out.println(chosenTokens + "  : après l'ajout");
	}

  @Override
	public String toString(){
		return choiceBoard.toString();
	}


}

	

