package game.material;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
//

public class Environment{
	private final LinkedHashMap<Tile,Token> board = new LinkedHashMap<>();
	private final ArrayList<Token> lisTokens = new ArrayList<>();

	public void addTilePlayer(Tile tile){
		// Ajout D'une tuile à l'environnement du joueur
		Objects.requireNonNull(tile," Erreur :  tuile requise pour l'ajout !");
		board.put(tile, null);
	}
	
	public void addTokenPlayer(Tile tile,Token token){
		// Ajout du jeton par le joueur
		Objects.requireNonNull(token, "Erreur jeton nul !");
		Objects.requireNonNull(tile," Erreur :  tuile requise pour l'ajout !");
		board.put(tile, token);
	}
	public void addToken(Token token){
		lisTokens.add(token);
	}

  public ArrayList<Token> getLisTokens(){
		return lisTokens;
	}
	
	public LinkedHashMap<Tile,Token> getEnvironment(){
		return board;
	}

	@Override
	public String toString(){
		return board.toString();
	}

}
