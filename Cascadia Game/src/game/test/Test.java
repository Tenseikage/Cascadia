package game.test;
import game.display.Display;
import game.material.*;
import java.io.IOException;


public class Test {
	public void displayInstruction() {
	}
	
	/*
	public void diplayGameBoard(Player p1) {
		System.out.println("Game board of : "+p1.name());
		for (Tile tile : p1.board()) {
			System.out.println(tile);
		}
	}*/

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
		var display = new Display();
		display.displayTile(board);
		display.displayToken(board);
	}
}
