package game.material;
import game.logic.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
//

public class Environment{
	LinkedHashMap<HashMap<Tile,Token>,Position> tokenTilesList = new LinkedHashMap<>();
	public static <Key,Value> Key getKeyByValue(LinkedHashMap<Key, Value> map, Value value){
		for (Map.Entry<Key, Value> entry : map.entrySet()) {
			if (entry.getValue() == value){
				return entry.getKey();
			}
		}
		throw new IllegalAccessError("Valeur introuvable");
	}

	public void addTilePlayer(HashMap<Tile,Token> key,Position position){	
			
		// Ajout D'une tuile à l'environnement du joueur
		Objects.requireNonNull(key," Erreur :  tuile requise pour l'ajout !");
		tokenTilesList.put(key, position);
	}
	
	public void addTokenPlayer(Tile tile,Token token,Position position){
		// Ajout du jeton par le joueur
		Objects.requireNonNull(token, "Erreur jeton nul !");
		Objects.requireNonNull(tile," Erreur :  tuile requise pour l'ajout !");
		var key = Environment.getKeyByValue(tokenTilesList, position);
		key.put(tile, token);
		tokenTilesList.put(key,position);
	}
	public LinkedHashMap<HashMap<Tile,Token>,Position> getEnvironment(){
		return tokenTilesList;
	}
	public void setEnvironment(LinkedHashMap<HashMap<Tile,Token>,Position> data){
		Objects.requireNonNull(data, "Erreur : données requises pour la mise à jour !");
		tokenTilesList.putAll(data);
	}

	public ArrayList<Position> getPositions(){
		ArrayList<Position> listPositions = new ArrayList<>();
		for (var entry : tokenTilesList.entrySet()){
			listPositions.add(entry.getValue());
		}
		return listPositions;

	}
	@Override
	public String toString(){
		return tokenTilesList.toString();
	}

}
