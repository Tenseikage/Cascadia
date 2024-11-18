package game.material;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class Tile {
  private String places = new String();
  private final ArrayList<String> listAnimals = new ArrayList<>();
	private final ArrayList<HashMap<Tile,Token>> starTiles = new ArrayList<>();

	public Tile addAnimals(String... animals) {
    this.listAnimals.addAll(Arrays.asList(animals));
    return this;
  }

	public Tile setPlace(String place) {
		this.places = place;
		return this;
}

	public String getPlace(){
		return places;
	}

	public ArrayList<String> getListAnimals(){
		return listAnimals;
	}

	public HashMap<Tile, Token> addStart1(){
		// Première tuile de départ
		HashMap<Tile, Token> tilesTokens1 = new HashMap<>();
		tilesTokens1.put(new Tile().setPlace("Ma").addAnimals("Bu","Re"), null);
		tilesTokens1.put(new Tile().setPlace("Ri").addAnimals("Sa", "Bu"),null);
		tilesTokens1.put(new Tile().setPlace("Pr").addAnimals("Ou", "Sa"),null);
		return tilesTokens1;
	}
	public HashMap<Tile, Token> addStart2(){
		//Deuxième tuile départ
		HashMap<Tile, Token> tilesTokens2 = new HashMap<>();
		tilesTokens2.put(new Tile().setPlace("Mo").addAnimals("Ou","Bu"),null);
		tilesTokens2.put(new Tile().setPlace("Ma").addAnimals("Sa", "Ou"),null);
		tilesTokens2.put(new Tile().setPlace("Fo").addAnimals("Wa", "Re"), null);
		return tilesTokens2;
	}

	public HashMap<Tile, Token> addStart3(){
		//Troisième tuile de départ
		HashMap<Tile, Token> tilesTokens3 = new HashMap<>();
		tilesTokens3.put(new Tile().setPlace("Fo").addAnimals("Wa","Bu"),null);
		tilesTokens3.put(new Tile().setPlace("Mo").addAnimals("Bu", "Ou"),null);
		tilesTokens3.put(new Tile().setPlace( "Ma").addAnimals("Re", "Sa"),null);
		return tilesTokens3;

	}
	public HashMap<Tile, Token> addStart4(){
		// Quatrième tuile de départ
		HashMap<Tile, Token> tilesTokens4 = new HashMap<>();
		tilesTokens4.put(new Tile().setPlace("Ri").addAnimals("Sa","Bu"),null);
		tilesTokens4.put(new Tile().setPlace("Fo").addAnimals( "Wa", "Ou"),null);
		tilesTokens4.put(new Tile().setPlace("Mo").addAnimals("Re", "Bu"),null);
		return tilesTokens4;
	}

	public HashMap<Tile, Token> addStart5(){
		//Cinquième tuile de départ
		HashMap<Tile, Token> tilesTokens5 = new HashMap<>();
		tilesTokens5.put(new Tile().setPlace("Pr").addAnimals("Re","Bu"),null);
		tilesTokens5.put(new Tile().setPlace("Ri").addAnimals("Sa", "Bu"),null);
		tilesTokens5.put(new Tile().setPlace("Mo").addAnimals("Ou", "Wa"),null);
		return tilesTokens5;
    
	}

	public ArrayList<HashMap<Tile, Token>> startTiles(){
		starTiles.add(addStart1());
		starTiles.add(addStart2());
		starTiles.add(addStart3());
		starTiles.add(addStart4());
		starTiles.add(addStart5());
		Collections.shuffle(starTiles);
		return starTiles;
	}	

	public HashMap<Tile, Token> getStartiles(){
		// HashMap contenant 3 tuiles
		var chosenTokenTile = starTiles.remove(0);
		return chosenTokenTile;
	}

	public static ArrayList<Tile> ExploitCsv() throws IOException {
		// Création des tuiles du jeu
		ArrayList<Tile> tiles = new ArrayList<>();
		try {
			Path path = Path.of("Cascadia Game/data/Tuiles.csv");
			var dataList = Files.readAllLines(path);
			for(var line : dataList){
				String[] parts = line.split(";");
				if (parts.length >= 2) {
          Tile tile = new Tile();
          tile.setPlace(parts[0].substring(0, 2));
					var animals = parts[1].split(",");
          tile.addAnimals(animals[0].substring(0, 2));
					tile.addAnimals(animals[1].substring(0, 2));
          tiles.add(tile);
        }
			}	
		} catch(IOException e){
			  throw new IOException("file not found");
		}
		return tiles;
	}
	public static void shuffTiles(ArrayList<Tile> tiles){
		// Mélange des tuiles pour la distribution au hasard
		Collections.shuffle(tiles);
	}
	@Override
	public String toString() {
		return "Tile{" +
				"place='" + places + '\'' +
				", animals=" + listAnimals +
				'}';
	}
		
}
