package game.graphic;

import game.material.Environment;
import game.material.PeerTileToken;
import game.player.Player;
import java.util.ArrayList;

public record DataPlayer(Player player, Environment envPlayer) {
	public DataPlayer(Player player,Environment envPlayer){
		this.envPlayer = envPlayer;
		this.player = player;
	}
	public void start(ArrayList<PeerTileToken> startTiles){
		envPlayer.setEnvironment(envPlayer.choseStartTile(startTiles));
	}
	



}
