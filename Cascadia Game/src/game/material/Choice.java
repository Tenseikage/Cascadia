package game.material;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Random;

public class Choice {
	//Choix contenant les tuiles et jetons
	private final LinkedHashMap<Tile,Token> choiceBoard = new LinkedHashMap<>();	
	public void addTokensTiles(ArrayList<Tile> tiles,ArrayList<Token> chosenTokens){
		for(int i = 0; i < 4; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choiceBoard.put(tiles.remove(indexTile), chosenTokens.get(i));
		}
	}

	public void updateTokenTiles(ArrayList<Tile> tiles, ArrayList<Token> chosenTokens){
		Objects.requireNonNull(tiles, "Erreur liste de tuiles nulle !");
		Objects.requireNonNull(chosenTokens, "Erreur : liste de tokens nulle !"); 
		int size = 4 - choiceBoard.size();
		for (int i = 0; i < size; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choiceBoard.put(tiles.remove(indexTile), chosenTokens.get(3 - i));
		}
	}
	public ArrayList<Token> uncompleteTokenList(){
		ArrayList<Token> chosenTokens = new ArrayList<>();
		for(var entry : choiceBoard.entrySet()){
			chosenTokens.add(entry.getValue());
		}
		return chosenTokens;
	}

	public void createChoiceBoard(ArrayList<Tile> tiles, ArrayList<Token> tokens){
		ArrayList<Token> chosenTokens = new ArrayList<>();
		Objects.requireNonNull(tiles, "Erreur liste de tuiles nulle !");
		Objects.requireNonNull(tokens, "Erreur : liste de tokens nulle !"); 
		chosenTokens = Token.chooseTokens(tokens,chosenTokens);
		if(!Token.checkOvercrowding(chosenTokens)){
			addTokensTiles(tiles, chosenTokens);
			System.out.println("Pas surpopulation");
		} else {
				var discardedTokens = Token.discardTokens(chosenTokens);
				Token.chooseTokens(tokens, chosenTokens); 
				tokens.addAll(discardedTokens);
		}		
	}

	public void updateChoiceBoard(ArrayList<Tile> tiles, ArrayList<Token> tokens,ArrayList<Token> chosenTokens){
		int size = 4 - chosenTokens.size();
		Token.completeTokenList(tokens, chosenTokens, size);
		if(!Token.checkOvercrowding(chosenTokens)){
			updateTokenTiles(tiles, chosenTokens);
		} else {
				var discardedTokens = Token.discardTokens(chosenTokens);
				Token.chooseTokens(tokens, chosenTokens); // choix des nouveaux tokens après surpopulation
				tokens.addAll(discardedTokens);
		}		
	}

	public  LinkedHashMap<Tile,Token> getChoiceBoard(){
		return choiceBoard;
	}

	@Override
	public String toString(){
		return choiceBoard.toString();
	}


}

	

