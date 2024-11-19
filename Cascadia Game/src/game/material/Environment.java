package game.material;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import game.logic.Position;

public class Environment{
	LinkedHashMap<HashMap<Tile,Token>,Position> tokenTilesList = new LinkedHashMap<>(); // Environnement du joueur
	public static <Key,Value> Key getKeyByValue(LinkedHashMap<Key, Value> map, Value value){
		for (Map.Entry<Key, Value> entry : map.entrySet()) {
			if (entry.getValue().equals(value)){
				return entry.getKey();
			}
		}
		throw new IllegalAccessError("Valeur introuvable");
	}

	public static <Key,Value> Key getKeyByValue(HashMap<Key, Value> map, Value value){
		for (Map.Entry<Key, Value> entry : map.entrySet()) {
			if (entry.getValue() == null){
				return entry.getKey();
			}
			else if (entry.getValue().equals(value)){
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
	public Boolean checkPutToken(Tile tile, Token token){
		System.out.println(tile.getListAnimals().get(0) + " animal tile");
		System.out.println(token.espece() + " animal token");
		System.out.println("\n");
		System.out.println(tile.getListAnimals().get(0).equals(token.espece()) + "blak");
		System.out.println(tile.getListAnimals().get(1) + " animal tile");
		System.out.println(token.espece() + " animal token");
		//System.out.println(tile.getListAnimals().get(1).equals(token.espece() + "white"));
		return tile.getListAnimals().get(0).equals(token.espece())
    || tile.getListAnimals().get(1).equals(token.espece());
	}

	public boolean addTokenPlayer(Tile tile,Token token, Position position){
		Objects.requireNonNull(token, "Erreur jeton nul !");
		Objects.requireNonNull(tile," Erreur :  tuile requise pour l'ajout !");
		if(checkPutToken(tile, token)){
			var key = Environment.getKeyByValue(tokenTilesList, position);
			key.put(tile, token);
			tokenTilesList.put(key,position);
			return true;
		} else {
			System.out.println("Erreur : Placement du jeton impossible");
			return false;
		}

	}
	
	/*public void addTokenPlayer(Tile tile,Token token,Position position){
		// Ajout du jeton par le joueur
		Objects.requireNonNull(token, "Erreur jeton nul !");
		Objects.requireNonNull(tile," Erreur :  tuile requise pour l'ajout !");
		var key = Environment.getKeyByValue(tokenTilesList, position);
		key.put(tile, token);
		tokenTilesList.put(key,position);
	}*/
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
	
	public ArrayList<Position> getValidVoisin(Position p) {
		ArrayList<Position> list = new ArrayList<Position>();
		for (Position voisin: p.voisin()) {
			if (tokenTilesList.containsValue(voisin)) {
				list.add(voisin);
			}
		}
		return list;
	}
	
	@Override
	public String toString(){
		return tokenTilesList.toString();
	}

}
