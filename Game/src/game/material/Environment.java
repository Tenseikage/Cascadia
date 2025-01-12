package game.material;
import game.graphic.WindowInfo;
import game.logic.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class which represents the environment of the player
 * @author Christophe TARATIBU
 */
public class Environment{
	private final Map<PeerTileToken,Position> tokenTilesList = new HashMap<>();
	/**
	 * Chooses the starting tiles,
	 * @param tokenTiles ArrayList of tiles and null tokens
	 * @return HashMap of tiles and null tokens with tile positions (start habitat)
	 */
	public Map<PeerTileToken,Position> choseStartTile(List<PeerTileToken> tokenTiles){
		Objects.requireNonNull(tokenTiles);
		int[] index = {0};
		var data = tokenTiles.stream().collect(Collectors.toMap(Function.identity(), _ -> new Position(0,index[0]++)));
		return new HashMap<>(data);
	}
	
	/**
	 * Returns the key of a map by its value
	 * @param position position to search
	 * @return key
	 */

	public PeerTileToken getKeyByPos(Position position){
		Objects.requireNonNull(position);
		return tokenTilesList.entrySet().stream().filter(p -> p.getValue().equals(position)).map(Map.Entry::getKey).findAny().orElse(null);
		
		
	}

	/**
	 * Adds a tile and a token to the player's environment
	 * @param tile Tile
	 * @param position position of the tile
	 */
	public void addTilePlayer(Tile tile,Position position){	
		Objects.requireNonNull(tile);
		tokenTilesList.put(new PeerTileToken(tile,null), position);
	}

	/**
	 * Checks if the token can be placed on the tile
	 * @param tile tile to check
	 * @param token token to check
	 * @return true if the token can be placed, false otherwise
	 */
	public Boolean checkPutToken(Tile tile, Token token){
		Objects.requireNonNull(tile);
		Objects.requireNonNull(token);
		return tile.getListAnimals().get(0).equals(token.espece())
    || tile.getListAnimals().get(1).equals(token.espece());
	}

	/**
	 *  Adds a token to the player's environment
	 * @param peer peer Tile/Token
	 * @param token token to add
	 * @param position position of the tile
	 * @param displayMode Game mode (0: terminal and 1 : graphique)
	 * @return true if the token has been added, false otherwise
	 */
		public boolean addTokenPlayer(PeerTileToken peer,Token token, Position position, int displayMode){
			Objects.requireNonNull(token);
			Objects.requireNonNull(position);
			var tile = peer.getTile();
			var peerToken = peer.getToken();
			if(peerToken == null){
				if(checkPutToken(tile, token)){
					peer.setToken(token);
					return true;
				} else {
					if (displayMode == 0) {
						System.out.println("Erreur : Placement du jeton impossible");
						return false;
					} else {
						WindowInfo.messageInfoError("Erreur : Placement du jeton impossible", "Erreur");
						return false;
					}
				}

			} else {
				if (displayMode == 0) {
					System.out.println("Erreur : Placement du jeton impossible");
					return false;
				} else {
					WindowInfo.messageInfoError("Erreur : Placement du jeton impossible", "Erreur");
					return false;
				}

			}

	  }
	
	/**
	 * Returns the environment of the player
	 * @return the environment of the player
	 */
	public Map<PeerTileToken,Position> getEnvironment(){
		return tokenTilesList;
	}
	/**
	 * Sets the environment of the player with starting tiles
	 * @param data data to set
	 */
	public void setEnvironment(Map<PeerTileToken,Position> data){
		Objects.requireNonNull(data);
		tokenTilesList.putAll(data);
	}

	/**
	 * Creates a list which contains the positions of the tiles
	 * @return list of positions
	 */
	public List<Position> getPositions(){
		List<Position> listPositions = new ArrayList<>();
		for (var entry : tokenTilesList.entrySet()){
			listPositions.add(entry.getValue());
		}
		return listPositions;

	}
	
	/**
	 * This method checks if the position is valid
	 * @param positions list of positions
	 * @param position position to check
	 * @return true if the position is valid, false otherwise
	 */
	public boolean validPos(List<Position> positions, Position position){
		Objects.requireNonNull(positions);
		Objects.requireNonNull(position);
		return positions.contains(position);
	}
	
	/**
	 * This method checks if there is no tile in the position
	 * @param position position to check
	 * @return true if there is no tile in the position, false otherwise
	 */
	public boolean noTileInPos(Position position){
		return getKeyByPos(position) != null;
	}

}
