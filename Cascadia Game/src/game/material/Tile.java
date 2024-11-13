package game.material;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class Tile {
  private final ArrayList<String> places;
  private final ArrayList<String> listAnimals;
	private final ArrayList<HashMap<Tile,Token>> starTiles;
	public Tile(){
		// Constructeur initialisant les listes
		this.places = new ArrayList<>();
		this.listAnimals = new ArrayList<>();
		this.starTiles = new  ArrayList<>();
	}

  public Tile addPlaces(String... places) {
    this.places.addAll(Arrays.asList(places));
      return this;
    }

  public Tile addAnimals(String... animals) {
    this.listAnimals.addAll(Arrays.asList(animals));
    return this;
  }

	public ArrayList<String> getPlace(){
		return places;
	}

	public ArrayList<String> getListAnimals(){
		return listAnimals;
	}


  /*public static ArrayList<ArrayList<Tile>> startTile() {
		// Définition des habitats de départ en dur
		ArrayList<ArrayList<Tile>> startTiles = new ArrayList<>();
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Marais").addAnimals("Buse","Renard"),
		new Tile().addPlaces("Rivieres").addAnimals("Saumon", "Buse"),
		new Tile().addPlaces("Prairies").addAnimals("Ours", "Saumon"))));///
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Montagne").addAnimals("Ours","Buse"),
		new Tile().addPlaces("Marais").addAnimals("Saumon", "Ours"),
		new Tile().addPlaces("Forets").addAnimals("Wapiti", "Renard"))));///
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Foret").addAnimals("Wapiti","Buse"),
		new Tile().addPlaces("Montagnes").addAnimals("Buse", "Ours"),
		new Tile().addPlaces( "Marais").addAnimals("Renard", "Saumon"))));///
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Riviere").addAnimals("Saumon","Buse"),
		new Tile().addPlaces("Forets").addAnimals( "Wapiti", "Ours"),
		new Tile().addPlaces("Montagnes").addAnimals("Renard", "Buse"))));///
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Prairie").addAnimals("Renard","Buse"),
		new Tile().addPlaces("Rivieres").addAnimals("Saumon", "Buse"),
		new Tile().addPlaces("Montagnes").addAnimals("Ours", "Wapiti"))));
		return startTiles;
	}*/
	public HashMap<Tile, Token> addStart1(){
		// Première tuile de départ
		HashMap<Tile, Token> tilesTokens1 = new HashMap<>();
		tilesTokens1.put(new Tile().addPlaces("Ma").addAnimals("Bu","Re"), null);
		tilesTokens1.put(new Tile().addPlaces("Ri").addAnimals("Sa", "Bu"),null);
		tilesTokens1.put(new Tile().addPlaces("Pr").addAnimals("Ou", "Sa"),null);
		return tilesTokens1;
	}
	public HashMap<Tile, Token> addStart2(){
		//Deuxième tuile départ
		HashMap<Tile, Token> tilesTokens2 = new HashMap<>();
		tilesTokens2.put(new Tile().addPlaces("Mo").addAnimals("Ou","Bu"),null);
		tilesTokens2.put(new Tile().addPlaces("Ma").addAnimals("Sa", "Ou"),null);
		tilesTokens2.put(new Tile().addPlaces("Fo").addAnimals("Wa", "Re"), null);
		return tilesTokens2;
	}

	public HashMap<Tile, Token> addStart3(){
		//Troisième tuile de départ
		HashMap<Tile, Token> tilesTokens3 = new HashMap<>();
		tilesTokens3.put(new Tile().addPlaces("Fo").addAnimals("Wa","Bu"),null);
		tilesTokens3.put(new Tile().addPlaces("Mo").addAnimals("Bu", "Ou"),null);
		tilesTokens3.put(new Tile().addPlaces( "Ma").addAnimals("Re", "Sa"),null);
		return tilesTokens3;

	}
	public HashMap<Tile, Token> addStart4(){
		// Quatrième tuile de départ
		HashMap<Tile, Token> tilesTokens4 = new HashMap<>();
		tilesTokens4.put(new Tile().addPlaces("Ri").addAnimals("Sa","Bu"),null);
		tilesTokens4.put(new Tile().addPlaces("Fo").addAnimals( "Wa", "Ou"),null);
		tilesTokens4.put(new Tile().addPlaces("Mo").addAnimals("Re", "Bu"),null);
		return tilesTokens4;
	}

	public HashMap<Tile, Token> addStart5(){
		//Cinquième tuile de départ
		HashMap<Tile, Token> tilesTokens5 = new HashMap<>();
		tilesTokens5.put(new Tile().addPlaces("Pr").addAnimals("Re","Bu"),null);
		tilesTokens5.put(new Tile().addPlaces("Ri").addAnimals("Sa", "Bu"),null);
		tilesTokens5.put(new Tile().addPlaces("Mo").addAnimals("Ou", "Wa"),null);
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

	/*public LinkedHashMap<HashMap<Tile,Token>, Position> starTiles(){
		var tilesTokens1 = new HashMap<>();
		tilesTokens.put(new Tile, this)
	}*/
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
          tile.addPlaces(parts[0].substring(0, 2));
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
  public String toString(){
    // Affichage sous forme de liste
    var builder  = new StringBuilder();
    builder.append("[")
    .append(places.toString())
    .append(",")
    .append(listAnimals.toString())
    .append("]").append("\n");
    return builder.toString();
  }
}
