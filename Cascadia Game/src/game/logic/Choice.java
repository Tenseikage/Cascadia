package game.logic;
import game.material.Tile;
import game.material.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

//Modifier les methodes en réimplémentant les fonctions
public class Choice {
	//Choix contenant les tuiles et jetons
	private final LinkedHashMap<Tile,Token> choiceBoard = new LinkedHashMap<>();
	private final LinkedHashMap<HashMap<Tile,Token>,Position> startTiles = new LinkedHashMap<>();
		
	public void addTokensTiles(ArrayList<Tile> tiles,ArrayList<Token> chosenTokens){
		for(int i = 0; i < 4; i++){
			Random random = new Random();
			int indexTile = random.nextInt(tiles.size());
			choiceBoard.put(tiles.remove(indexTile), chosenTokens.get(i));
		}
	}
	public LinkedHashMap<HashMap<Tile,Token>,Position> choseStartTile(HashMap<Tile,Token> tokenTiles){
		int i = 0;
		for (var entry : tokenTiles.entrySet()){
			HashMap<Tile, Token> entryMap = new HashMap<>();
			entryMap.put(entry.getKey(), entry.getValue());
			startTiles.put(entryMap, new Position(0, i));
			i++;
		}
		return startTiles;

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
	public ArrayList<Token> completeTokenList(){
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
		if(!Token.checkOvercrowding(chosenTokens, null)){
			addTokensTiles(tiles, chosenTokens);
		} else {
			  System.out.println("Surpopulation : choix de nouveaux jetons");
				var discardedTokens = Token.discardTokens(chosenTokens);
				Token.chooseTokens(tokens, chosenTokens); 
				addTokensTiles(tiles, chosenTokens); // Ajouts des nouveaux jetons
				tokens.addAll(discardedTokens);
		}		
	}

	public void updateChoiceBoard(ArrayList<Tile> tiles, ArrayList<Token> tokens,ArrayList<Token> chosenTokens, Scanner scanner){
		int size = 4 - chosenTokens.size();
		Token.completeTokenList(tokens, chosenTokens, size);
		if(!Token.checkOvercrowding(chosenTokens,scanner)){
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

	

