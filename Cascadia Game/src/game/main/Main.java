package game.main;
import game.display.Display;
import game.display.DisplayTools;
import game.logic.Choice;
import game.logic.GameLogic;
import game.logic.Score;
import game.material.Environment;
import game.material.Tile;
import game.material.Token;
import game.player.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void loopWithExecptions(Scanner scanner,Display display,Player player1, Player player2, Choice board, 
		Environment env1,Environment env2, DisplayTools grid1, 
		DisplayTools grid2, ArrayList<Token> tokens,ArrayList<Tile> tiles){
			var logic = new GameLogic();
			int nbturns = 0;
			while(nbturns < 2){
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
				nbturns++;
			}
		}
		public static void main(String[] args) throws IOException,IllegalAccessError {
			Scanner scanner = new Scanner(System.in);
			Score score = new Score();
			var display = new Display();
			var env1 = new Environment();
			var env2 = new Environment();
			var board = new Choice();
			var tile = new Tile();
			var grid1 = new DisplayTools();
			var grid2 = new DisplayTools();
			display.displayRules();
			int mode = display.displayChoiceMod(scanner);
			var tiles = Tile.ExploitCsv(); // Liste de tuiles
			var tokens = Token.tokenList(); // Liste de jetons
			var player1 = new Player("Toto",18,0,env1);
			var player2 = new Player("Tota",18,0,env2);
			tile.startTiles();
			grid1.initGrid();
			grid2.initGrid();
			var choice1 = tile.getStartiles();// Contient 3 tuiles sans les positions
			var choice2 = tile.getStartiles(); //
			var finalTiles1 = board.choseStartTile(choice1);
			var finalTiles2 = board.choseStartTile(choice2);
			env1.setEnvironment(finalTiles1); // Ajout tuiles de départ à l'environnement
			env2.setEnvironment(finalTiles2);
			board.createChoiceBoard(tiles, tokens);
			loopWithExecptions(scanner,display,player1,player2,board,env1,env2,grid1,grid2,tokens,tiles);
		if (mode == 1) {
			System.out.println("Le score du J1 est : "+score.calculModeFamille(env1));
	  }
	}
}
