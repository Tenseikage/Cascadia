package game.material;
/**
 * @author Christophe TARATIBU
 */
public class PeerTileToken{
	private Tile tile;
	private Token token;
	
	public PeerTileToken(Tile tile, Token token) {
		this.tile = tile;
		this.token = token;
  }

	public Tile getTile() {
		return tile;
	}


	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public void setTile(Tile tile){
		this.tile = tile;
	}

	@Override
	public String toString(){
		return tile.toString() + " : Tuile " + token.toString() + " : token";
	}

}
