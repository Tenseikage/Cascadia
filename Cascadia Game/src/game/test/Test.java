package game.test;

import game.player.ListPlayers;
import game.player.Player;

public class Test {

	public static void main(String[] args) {
		var player1 = new Player("Loïc", 20, 0, 0);
		var player2 = new Player("Christophe",20,0,0);
		var lst = new ListPlayers();
		lst.add(player1);
		lst.add(player2);
		System.out.println(lst);
	}
}
