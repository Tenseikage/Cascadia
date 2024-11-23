package game.material;
import game.logic.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class which represents the environment of the player
 */
public class Environment{
	private final LinkedHashMap<HashMap<Tile,Token>,Position> tokenTilesList = new LinkedHashMap<>();

	/**
	 * This method returns the key of a map by its value
	 * @param <Key> type of the key
	 * @param <Value> type of the value
	 * @param map map to search in
	 * @param index index of the key
	 * @return key
	 */
	public static <Key,Value> Key getKeyByValue(LinkedHashMap<Key, Value> map, Value value){
		for (Map.Entry<Key, Value> entry : map.entrySet()) {
			if (entry.getValue().equals(value)){
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * This method returns the key of a map by its value
	 * @param <Key> type of the key
	 * @param <Value> type of the value
	 * @param map map to search in
	 * @param value value to search
	 * @return key
	 */
	public static <Key,Value> Key getKeyByValue(HashMap<Key, Value> map, Value value){
		for (Map.Entry<Key, Value> entry : map.entrySet()) {
			if (entry.getValue() == null){
				return entry.getKey();
			}
			else if (entry.getValue().equals(value)){
				return entry.getKey();
			}
		}
		return null;
	}


	/**
	 * This method adds a tile and a token to the player's environment
	 * @param key key of the map
	 * @param position position of the tile
	 */
	public void addTilePlayer(HashMap<Tile,Token> key,Position position){	
		Objects.requireNonNull(key, "Error : Null key");
		tokenTilesList.put(key, position);
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
	public boolean addTokenPlayer(Tile tile,Token token, Position position){
		Objects.requireNonNull(tile, "Error : Null tile");
		Objects.requireNonNull(token, "Error : Null token");
		Objects.requireNonNull(position, "Error : Null position");
		if(checkPutToken(tile, token)){
			var key = Environment.getKeyByValue(tokenTilesList, position);
			key.put(tile, token);
			return true;
		} else {
			System.out.println("Erreur : Placement du jeton impossible");
			return false;
		}

	}
	
	/**
	 * This method returns the environment of the player
	 * @return the environment of the player
	 */
	public LinkedHashMap<HashMap<Tile,Token>,Position> getEnvironment(){
		return tokenTilesList;
	}
	/**
	 * This method sets the environment of the player with starting tiles
	 * @param data data to set
	 */
	public void setEnvironment(LinkedHashMap<HashMap<Tile,Token>,Position> data){
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
		return getKeyByValue(tokenTilesList,position) != null;
	}
	
	@Override
	public String toString(){
		return tokenTilesList.toString();
	}

}
