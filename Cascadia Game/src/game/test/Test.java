package game.test;
import game.material.Tile;
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
		Tile.displayTiles(tiles);
		Tile.drawTiles();

		/*Test des jetons */
		/*var tokens = Token.tokenList();
		Token.shuffeTokens(tokens);
		Token.displayTokens(tokens);
		System.out.println(tokens.size());*/
	}
}
