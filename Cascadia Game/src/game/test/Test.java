package game.test;
import game.material.*;
import java.io.IOException;


public class Test {
	public static void main(String[] args) throws IOException {
		/*Test des joueurs */
		/*var player1 = new Player("Loïc", 20, 0, 0);
		var player2 = new Player("Christophe",20,0,0);
		var lst = new ListPlayers();
		lst.add(player1);
		lst.add(player2);
		System.out.println(lst);

		/*Test des tuiles */
		var tiles = Tile.ExploitCsv();
		Tile.shuffTiles(tiles);
		//System.out.println(tiles);

		/*Test choix tuiles */
		/*Tests tuiles départ */
		/*var starTiles = Tile.startTile();
		System.out.println(starTiles);*/


		/*Test des jetons */
		var tokens = Token.tokenList();
		Token.shuffeTokens(tokens);
		//System.out.println(tokens);
		//System.out.println(tokens.size());
		// Affichage des choix de tuiles et jetons pour le jeu(Choix)
	  var board = new Choice();
		board.createChoiceBoard(tiles, tokens);
		/*var display = new Display();
		display.displayTile(board); 
		display.displayToken(board);
		display.displayRules();*/

		//Test affichage tuiles départ joueur
		var tile = new Tile();
		var array = tile.startTiles();
		System.out.println(array.size() + " size begin");
		var choice1 = tile.getStartiles();// Contient 3 tuiles sans les positions
		var choice2 = tile.getStartiles();
		var finalTiles1 = board.choseStartTile(choice1);
		var finalTiles2 = board.choseStartTile(choice2);
		System.out.println(finalTiles1);
		System.out.println(finalTiles2);
		System.out.println(array.size() + " size end");

		
	}
}
