package game.material;
import game.logic.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class which represents the environment of the player
 */
public class Environment{
	private final HashMap<PeerTileToken,Position> tokenTilesList2 = new HashMap<>();

	/**
	 * This method returns the key of a map by its value
	 * @param map map to search in
	 * @param position position to search
	 * @return key
	 */

	public static PeerTileToken getKeyByPos(HashMap<PeerTileToken,Position> map, Position position){
		for (var entry : map.entrySet()) {
			if (entry.getValue().equals(position)){
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * This method returns the key of a map by its value
	 * @param map map to search in
	 * @param token token to search
	 * @return key
	 */
	public static Tile getKeyByToken(HashMap<Tile, Token> map, Token token){
		for (var entry : map.entrySet()) {
			if (entry.getValue() == null){
				return entry.getKey();
			}
			else if (entry.getValue().equals(token)){
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
	public void addTilePlayer(Tile tile,Position position){	
		Objects.requireNonNull(tile, "Error : Null tile");
		tokenTilesList2.put(new PeerTileToken(tile,null), position);
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
			var peer = Environment.getKeyByPos(tokenTilesList2, position);
			peer.setToken(token);
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
	public HashMap<PeerTileToken,Position> getEnvironment(){
		return tokenTilesList2;
	}
	/**
	 * This method sets the environment of the player with starting tiles
	 * @param data data to set
	 */
	public void setEnvironment(HashMap<PeerTileToken,Position> data){
		Objects.requireNonNull(data, "Erreur : données requises pour la mise à jour !");
		tokenTilesList2.putAll(data);
	}

	/**
	 * This method a list which contains the positions of the tiles
	 * @return list of positions
	 */
	public ArrayList<Position> getPositions(){
		ArrayList<Position> listPositions = new ArrayList<>();
		for (var entry : tokenTilesList2.entrySet()){
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
		return getKeyByPos(tokenTilesList2,position) != null;
	}
	
	@Override
	public String toString(){
		return tokenTilesList2.toString();
	}

}
