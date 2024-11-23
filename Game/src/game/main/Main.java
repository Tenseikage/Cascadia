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
import java.util.Scanner;


public class Main {

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
	public static void loopWithExecptions(Scanner scanner,Display display,Player player1, Player player2, Choice board, 
		Environment env1,Environment env2, DisplayTools grid1, 
		DisplayTools grid2, ArrayList<Token> tokens,ArrayList<Tile> tiles){
			var logic = new GameLogic();
			int nbturns = 6;
			for(int i = 0; i < nbturns; i++){
				boolean validInput = false;
				while(!validInput){
					try {
						logic.gameTurn(scanner, display, player1, board, env1, grid1, tokens);
						logic.gameTurn(scanner, display, player2, board, env2, grid2, tokens);
						board.updateChoiceBoard(tiles, tokens, board.completeTokenList(),scanner);
						validInput = true;
					} catch (Exception e) {
							System.err.println("Invalid entry");
							scanner.nextLine();
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
			var env1 = new Environment();
			var env2 = new Environment();
			var board = new Choice();
			var tile = new Tile();
			var grid1 = new DisplayTools();
			var grid2 = new DisplayTools();
			display.displayRules();
			int mode = display.displayChoiceMod(scanner);
			var tiles = Tile.ExploitCsv();
			var tokens = Token.tokenList();
			var player1 = new Player("Toto",18,0,env1);
			var player2 = new Player("Tota",18,0,env2);
			tile.startTiles();
			grid1.initGrid();
			grid2.initGrid();
			var choice1 = tile.getStartiles();
			var choice2 = tile.getStartiles(); 
			var finalTiles1 = board.choseStartTile(choice1);
			var finalTiles2 = board.choseStartTile(choice2);
			env1.setEnvironment(finalTiles1);
			env2.setEnvironment(finalTiles2);
			board.createChoiceBoard(tiles, tokens,display,scanner);
			loopWithExecptions(scanner,display,player1,player2,board,env1,env2,grid1,grid2,tokens,tiles);
		  if (mode == 1) {
				var posAnimals1 = score.checkTokens(env1);
				var posAnimals2 = score.checkTokens(env2);
				int score1 = score.scoreMode(posAnimals1, mode);
				int score2 = score.scoreMode(posAnimals2, mode);
				Player.displayWinner(player1, score1, player2, score2);
			} else {
					var posAnimals1 = score.checkTokens(env1);
					var posAnimals2 = score.checkTokens(env2);
					int score1 = score.scoreMode(posAnimals1, mode);
					int score2 = score.scoreMode(posAnimals2, mode);
					Player.displayWinner(player1, score1, player2, score2);
			}
	}
}

