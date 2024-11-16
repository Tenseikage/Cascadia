package game.main;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

import game.display.Display;
import game.display.DisplayTools;
import game.logic.Position;
import game.material.Choice;
import game.material.Environment;
import game.material.Score;
import game.material.Tile;
import game.material.Token;


public class Main {
  public static <Key,Value> Key getKeyByIndex(LinkedHashMap<Key, Value> map, int index){
		if(index < 0 || index >= map.size()){
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		int position = 0;
		for (var entry : map.entrySet()) {
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
		var tiles = Tile.ExploitCsv(); // Liste de tuiles
		var tokens = Token.tokenList(); // Liste de jetons
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
				
				System.out.println("SELECTIONNER LE MODE :\n\t(1) -> MODE FAMILIALE\n\t(2) -> MODE INTERMEDIAIRE\n");
				int mode = scanner.nextInt();
				if (mode == 1) {
					System.out.println("MODE FAMILIALE \nMarquez des points pour chaque groupe d’animaux de la même espèce, en fonction de sa taille. La forme du groupe n'importe pas. \nEntrez ok pour commencer.");
					scanner.next();
				} else if (mode == 2) {
					System.out.println("MODE INTERMEDIAIRE \nLa variante intermédiaire se joue de la même manière que la variante familiale, mais en utilisant la carte Décompte de la faune spécifique à la variante intermédiaire. \nEntrez ok pour commencer.");
					scanner.next();
				} else {
					System.out.println("ERREUR SUR LA SELECTION -> MODE FAMILIALE PAR DEFAULT.");
					mode = 1;
				}
				
				//tour joueur 1;
				System.out.println("PLAYER 1\n");
				display.displayTile(board); 
				display.displayToken(board);
				System.out.println("  ( 01 )      ( 02 )      ( 03 )      ( 04 )\n");
				System.out.println("WHICH PAIR YOU CHOOSE (1 - 2 - 3 - 4) ? : ");
				int choiceJ1 = scanner.nextInt();
				System.out.println("Vous avez choisi : \n"+choiceJ1);
				var chosenTile = getKeyByIndex(choiceBoard, choiceJ1 - 1); // Tuile choisie
				var chosenToken = choiceBoard.get(chosenTile);// Jeton choisi
				HashMap<Tile,Token> tilesTokens1 = new HashMap<>(); // Map pour l'ajout des tuiles (Sans jetons)

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
					//System.out.println("bawls \n");
					tilesTokens1.put(chosenTile,null);
					env1.addTilePlayer(tilesTokens1, chosenPos);
				}
				//Vérifier que la position est danns l'environnement
				System.out.println("Environnement joueur 1");
				choiceBoard.remove(chosenTile);
				display.displayEnvPlayer(env1,grid1);
				System.out.println("Choisissez la position où poser votre jeton");
				answerPos = scanner.next();
				chosenPos = Position.fromString(answerPos); // Choix de la position pour poser la tuile
				var maptilePos = Environment.linkGetKeyByValue(env1.getEnvironment(), chosenPos);
				var tilePos = Environment.hashGetKeyByValue(maptilePos, null);
				env1.addTokenPlayer(tilePos, chosenToken, chosenPos); // Ajout du jeton sur une tuile
				display.displayEnvPlayer(env1,grid1);





				
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
			Score score = new Score();
			System.out.println("Le score du J1 est : "+score.calcul(env1));
		}	
  }

}
