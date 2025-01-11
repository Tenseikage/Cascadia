package game.main;
import game.display.Display;
import game.display.DisplayTools;
import game.logic.Choice;
import game.logic.GameLogic;
import game.logic.Scores;
import game.material.Environment;
import game.material.Tile;
import game.material.Token;
import game.player.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {

	public static List<Player> listPlayers(Scanner scanner, int nbPlayers){
		if(nbPlayers <= 0 || nbPlayers > 2){
			System.err.println("Nombre incorrect");
			return null;
		} 
		List<Player> players = new ArrayList<>();
		for(int i = 0; i < nbPlayers; i++){
			System.out.println("Veuillez entrer un Nom");
			String name = scanner.next();
			int age = -1;
			while (age < 0) {
				System.out.println("Veuillez entrer votre âge");
				if (scanner.hasNextInt()) {
					age = scanner.nextInt();
					if (age < 0) {
						System.err.println("L'âge doit être un nombre positif. Veuillez réessayer.");
					}
				} else {
					System.err.println("Entrée invalide. Veuillez entrer un nombre entier.");
					scanner.next(); // Consommer l'entrée invalide
				}
		  }
			players.add(new Player(name, age, 0, new Environment()));
		}
		return players;
	}

	public static List<DisplayTools> listGrids(int nbPlayers){
		if(nbPlayers <= 0 || nbPlayers > 2){
			System.err.println("Nombre incorrect");
			return null;
		}
		List<DisplayTools> grids = new ArrayList<>();
		for(int i = 0; i < nbPlayers; i++){
      var grid = new DisplayTools();
			grid.initGrid();
			grids.add(grid);
		}
		return grids;
	}
	/**
	 * Method to loop the game with exceptions
	 * @param scanner scanner
	 * @param display display
	 * @param player1 first player
	 * @param player2 second player
	 * @param board choice board
	 * @param env1 first player environment
	 * @param env2 second player environment
	 * @param grid1 first player grid
	 * @param grid2 second player grid
	 * @param tokens list of tokens
	 * @param tiles list of tiles
	 */
	public static void loopWithExecptions(Scanner scanner,Display display,List<Player> players, Choice board, 
		List<DisplayTools> grids, ArrayList<Token> tokens,ArrayList<Tile> tiles){
			var logic = new GameLogic();
			int nbturns = 6;
			for(int i = 0; i < nbturns; i++){
				boolean validInput = false;
				while(!validInput){
					try {
						logic.gameTurn(scanner, display, players.get(i%2), board, grids.get(i%2), tokens);
						board.updateChoiceBoard(tiles, tokens,scanner,0);
						validInput = true;
					} catch (Exception e) {
						System.err.println("Invalid entry");
					} catch(IllegalAccessError e){
						System.err.println("Invalid position");
						scanner.nextLine();
					}
				}
			}

		}

		/**
		 * Main method
		 * @param args arguments
		 * @throws IOException Exceptions of the input/output of the game
		 * @throws IllegalAccessError Exceptions of the access of the game
		 */
		public static void main(String[] args) throws IOException,IllegalAccessError {
			Scanner scanner = new Scanner(System.in);
			Scores score = new Scores();
			var display = new Display();
			var players = listPlayers(scanner, 2);
			var board = new Choice();
			var listGrids = listGrids(2);
			display.displayRules();
			int mode = display.displayChoiceMod(scanner);
			var tiles = Tile.ExploitCsv();
			var tokens = Token.tokenList();
			tiles = Tile.tileBag(tiles, 2);
			Tile.startTiles();
			var choice1 = Tile.getStartiles();
			var choice2 = Tile.getStartiles(); 
			var playTiles1 = players.get(0).boardPlayer().choseStartTile(choice1);
			var playTiles2 = players.get(1).boardPlayer().choseStartTile(choice2);
			players.get(0).boardPlayer().setEnvironment(playTiles1);
			players.get(1).boardPlayer().setEnvironment(playTiles2);
			board.createChoiceBoard(tiles,tokens,scanner,0); // Ajout mode Affichage
			loopWithExecptions(scanner, display, players, board, listGrids, tokens, tiles);
			var player1 = players.get(0);
			var player2 = players.get(1);
			var env1 = players.get(0).boardPlayer();
			var env2 = players.get(1).boardPlayer();
		  if (mode == 1) {
				var posAnimals1 = score.checkTokens(env1);
				var posAnimals2 = score.checkTokens(env2);
				int score1 = score.scoreMode(posAnimals1, mode);
				int score2 = score.scoreMode(posAnimals2, mode);
				Player.displayWinner(player1, score1, player2, score2,0);
			} else {
					var posAnimals1 = score.checkTokens(env1);
					var posAnimals2 = score.checkTokens(env2);
					int score1 = score.scoreMode(posAnimals1, mode);
					int score2 = score.scoreMode(posAnimals2, mode);
					Player.displayWinner(player1, score1, player2, score2,0);
			}
	}
}

