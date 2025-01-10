package game.material;
import game.graphic.WindowInfo;
import game.logic.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class which represents the environment of the player
 */
public class Environment{
	private final HashMap<PeerTileToken,Position> tokenTilesList = new HashMap<>();


	/**
	 * This method chooses the starting tiles,
	 * @param tokenTiles ArrayList of tiles and null tokens
	 * @return HashMap of tiles and null tokens with tile positions (start habitat)
	 */
	public HashMap<PeerTileToken,Position> choseStartTile(ArrayList<PeerTileToken> tokenTiles){
		Objects.requireNonNull(tokenTiles, "Data for start tiles is null");
		int[] index = {0};
		var data = tokenTiles.stream().collect(Collectors.toMap(Function.identity(), _ -> new Position(0,index[0]++)));
		return new HashMap<>(data);
	}
	
	/**
	 * This method returns the key of a map by its value
	 * @param map map to search in
	 * @param position position to search
	 * @return key
	 */

	public PeerTileToken getKeyByPos(Position position){
		Objects.requireNonNull(position);
		return tokenTilesList.entrySet().stream().filter(p -> p.getValue().equals(position)).map(Map.Entry::getKey).findAny().orElse(null);
		
		
	}

	
	/**
	 * This method adds a tile and a token to the player's environment
	 * @param key key of the map
	 * @param position position of the tile
	 */
	public void addTilePlayer(Tile tile,Position position){	
		Objects.requireNonNull(tile, "Error : Null tile");
		tokenTilesList.put(new PeerTileToken(tile,null), position);
	}

	/**
	 * This method checks if the token can be placed on the tile
	 * @param tile tile to check
	 * @param token token to check
	 * @return true if the token can be placed, false otherwise
	 */
	public Boolean checkPutToken(Tile tile, Token token){
		return tile.getListAnimals().get(0).equals(token.espece())
    || tile.getListAnimals().get(1).equals(token.espece());
	}

	/**
	 * This method adds a token to the player's environment
	 * @param tile tile to add
	 * @param token token to add
	 * @param position position of the tile
	 * @return true if the token has been added, false otherwise
	 */
	public boolean addTokenPlayer(PeerTileToken peer,Token token, Position position, int displayMode){
		Objects.requireNonNull(token, "Error : Null token");
		Objects.requireNonNull(position, "Error : Null position");
		var tile = peer.getTile();
		if(checkPutToken(tile, token)){
			peer.setToken(token);
			return true;
		} else if (displayMode == 0) {
			System.out.println("Erreur : Placement du jeton impossible");
			return false;
		} else {
			WindowInfo.messageInfoError("Erreur : Placement du jeton impossible", "Erreur");
			return false;
		}

	}
	
	/**
	 * This method returns the environment of the player
	 * @return the environment of the player
	 */
	public HashMap<PeerTileToken,Position> getEnvironment(){
		return tokenTilesList;
	}
	/**
	 * This method sets the environment of the player with starting tiles
	 * @param data data to set
	 */
	public void setEnvironment(HashMap<PeerTileToken,Position> data){
		Objects.requireNonNull(data, "Erreur : données requises pour la mise à jour !");
		tokenTilesList.putAll(data);
	}

	/**
	 * This method a list which contains the positions of the tiles
	 * @return list of positions
	 */
	public ArrayList<Position> getPositions(){
		ArrayList<Position> listPositions = new ArrayList<>();
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
	public boolean validPos(ArrayList<Position> positions, Position position){
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
	
	@Override
	public String toString(){
		return tokenTilesList.toString();
	}

}
