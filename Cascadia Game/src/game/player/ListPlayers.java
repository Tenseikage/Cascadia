package game.player;

import java.util.LinkedHashMap;

public class ListPlayers {
	private final LinkedHashMap<String, Player> players;
	
	public ListPlayers() {
		players = new LinkedHashMap<String, Player>();
	}
	
	public void add(Player player) {
	    players.put(player.name(), player);
	  }
	
	public void winner() {
		Player winner = null;
		int scoreMax = 0;
		var iterator = players.values().iterator();
		while (iterator.hasNext()) {
			Player next = iterator.next();
			if (scoreMax < next.points()) {
				scoreMax = next.points();
				winner = next;
				
			}
		}
	}
	
	@Override
	public String toString() {
		var builder = new StringBuilder();
		var separator = "";
		for (var elem: players.values()) {
			builder.append(separator).append(elem);
			separator = "\n";
		}
		return builder.toString();
	}
	
}
