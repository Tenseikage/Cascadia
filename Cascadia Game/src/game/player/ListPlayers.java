package game.player;

import java.util.LinkedHashMap;

public class ListPlayers {
	private final LinkedHashMap<Joueur, String> players;
	
	public ListPlayers() {
		players = new LinkedHashMap<Joueur, String>();
	}
	
	public void add(Joueur player) {
	    players.put(player, player.name());
	  }
	
	@Override
	public String toString() {
		var builder = new StringBuilder();
		var separator = "";
		for (var elem: players.values()) {
			builder.append(separator).append(elem);
			separator = ", ";
		}
		return builder.toString();
	}
	
}
