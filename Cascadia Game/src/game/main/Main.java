package game.main;
import game.display.Display;
import game.display.DisplayTools;
import game.logic.Position;
import game.material.Choice;
import game.material.Environment;
import game.material.Tile;
import game.material.Token;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
  public static <Key,Value> Key getKeyByIndex(LinkedHashMap<Key, Value> map, int index){
		if(index < 0 || index >= map.size()){
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		int position = 0;
		for (Map.Entry<Key, Value> entry : map.entrySet()) {
			if (position == index){
				return entry.getKey();
			}
			position++;
		}
		throw new IllegalAccessError("Index introuvable");
	}

	public static void main(String[] args) throws IOException {
		int numberTurns = 0;
		var position  = new Position(0,0); // Initialisation
		int maxY = 1000;
		int maxX = 1000;
		var display = new Display();
		display.displayRules();
		var tiles = Tile.ExploitCsv();
		var tokens = Token.tokenList();
		var env1 = new Environment();
		var board = new Choice();
		var tile = new Tile();
		var array = tile.startTiles();
		var grid1 = new DisplayTools();
		grid1.initGrid();
		var choice1 = tile.getStartiles();// Contient 3 tuiles sans les positions
		var finalTiles1 = board.choseStartTile(choice1);
		env1.setEnvironment(finalTiles1); // Ajout tuiles de départ à l'environnement
		board.createChoiceBoard(tiles, tokens);
		var choiceBoard = board.getChoiceBoard();

		try(Scanner scanner = new Scanner(System.in)){
			while (numberTurns < 20) {
				//RECAP : a chaque tour il faut afficher 1 le choix, le board du jour 1, géréer les entrées du j1, faire la meme pour le joueur 2;
				numberTurns++;
				//tour joueur 1;
				System.out.println("PLAYER 1\n");
				display.displayTile(board); 
				display.displayToken(board);
				System.out.println("  ( 01 )      ( 02 )      ( 03 )      ( 04 )\n");
				System.out.println("WHICH PAIR YOU CHOOSE (1 - 2 - 3 - 4) ? : ");
				int choiceJ1 = scanner.nextInt();
				System.out.println("Vous avez choisi : \n"+choiceJ1);
				var chosenTile = getKeyByIndex(choiceBoard, choiceJ1 - 1);
				System.out.println("Choisissez la position où la tuile sera adjacente");
				String answerPos = scanner.next();
				var chosenPos = Position.fromString(answerPos);
				System.out.println("Choisissez l'endroit où poser la tuile");
				System.out.println("Haut/Bas/Gauche/Droite");
				String answer = scanner.next();
				chosenPos = chosenPos.updatePosition(answer);
				System.out.println(chosenPos + ": position");
				if(!chosenPos.isValid(maxX, maxY)){
					System.err.println("Erreur : position impossible ! ");
				} else {
					HashMap<Tile,Token> tilesTokens = new HashMap<>();
					tilesTokens.put(chosenTile,null);
					env1.addTilePlayer(tilesTokens, chosenPos);
				}
				//System.out.println(answer.equals("Haut"));
				//position.updatePosition(answer);



				//env1.addTilePlayer(chosenTile);
				//env1.addToken(choiceBoard.get(chosenTile));
				System.out.println("Environnement joueur 1");
				choiceBoard.remove(chosenTile);
				display.displayEnvPlayer(env1,grid1);
				//display.displayEnvPlayer(env1);
	//			switch (choiceJ1) {
	//				case 1 -> ;
	//				case 2 -> ;
	//				case 3 -> ;
	//				case 4 -> ;
	//			}
				
				//tour joueur 2;
				/*System.out.println("PLAYER 2\n");
				display.displayTile(board); 
				display.displayToken(board);	
				System.out.println("  ( 01 )      ( 02 )      ( 03 )      ( 04 )\n");
				System.out.println("WHICH PAIR YOU CHOOSE (1 - 2 - 3 - 4) ? : ");
				int choiceJ2 = scanner.nextInt();
				System.out.println("Vous avez choisis : \n"+choiceJ2);
				chosenTile = getKeyByIndex(choiceBoard, choiceJ2 - 1);
				//env2.addTilePlayer(chosenTile);
				//env2.addToken(choiceBoard.get(chosenTile));
				choiceBoard.remove(chosenTile);
				//display.displayEnvPlayer(env2);
	//			switch (choiceJ2) {
	//				case 1 -> ;
	//				case 2 -> ;
	//				case 3 -> ;
	//				case 4 -> ;
	//			}*/
	      System.out.println(board.uncompleteTokenList());
	      board.updateChoiceBoard(tiles, tokens, board.uncompleteTokenList());
			}
			
		}	
  }

}
