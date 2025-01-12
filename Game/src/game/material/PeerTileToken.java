package game.material;
/**
 * This class creates the a peer which contains a tile and a token
 * @author Christophe TARATIBU
 */
public class PeerTileToken{
	private Tile tile;
	private Token token;

	/**
	 * Sets the tile and token
	 * @param tile Tile of game
	 * @param token Token of the game
	 */
	public PeerTileToken(Tile tile, Token token) {
		this.tile = tile;
		this.token = token;
  }

	/**
	 * Gets the tile
	 * @return tile
	 */
	public Tile getTile() {
		return tile;
	}

  /**
	 * Gets the token
	 * @return token
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * Sets the token to the PeerTileToken
	 * @param token Token to set
	 */
	public void setToken(Token token) {
		this.token = token;
	}
  /**
	 * Sets the tile to the PeerTileToken
	 * @param tile Tile to set
	 */
	public void setTile(Tile tile){
		this.tile = tile;
	}

}
