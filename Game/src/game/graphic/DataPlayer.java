package game.graphic;

import game.logic.Position;
import game.material.Environment;
import game.material.PeerTileToken;
import game.player.Player;
import java.util.ArrayList;
import java.util.Objects;

public record DataPlayer(Player player, Environment envPlayer) {
	public DataPlayer(Player player,Environment envPlayer){
		this.envPlayer = envPlayer;
		this.player = player;
	}
	public void start(ArrayList<PeerTileToken> startTiles){
		envPlayer.setEnvironment(envPlayer.choseStartTile(startTiles));
	}
	public boolean isColored(Position position){
		Objects.requireNonNull(position);
		var peer = envPlayer.getKeyByPos(position);
		return peer.getToken() != null;
	}



}
