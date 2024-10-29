package game.test;
import java.io.IOException;

import game.material.Tile;


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
		Tile.displayTiles(tiles);
		Tile.drawTiles();

		/*Test des jetons */
		/*var tokens = Token.tokenList();
		Token.shuffeTokens(tokens);
		Token.displayTokens(tokens);
		System.out.println(tokens.size());*/
	}
}
