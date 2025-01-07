package game.material;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * Class which represents a tile of the game
 */
public class Tile {
  private String places = new String();
  private final ArrayList<String> listAnimals = new ArrayList<>();
	private final static ArrayList<ArrayList<PeerTileToken>> startTiles = new ArrayList<>();

	/**
	 * Method to add animals to the tile
	 * @param animals the animals to add
	 * @return the tile with the animals
	 */
	public Tile addAnimals(String... animals) {
    this.listAnimals.addAll(Arrays.asList(animals));
    return this;
  }

	/**
	 * Method to set the place of the tile
	 * @param place	the place of the tile
	 * @return the tile with the place
	 */
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

	/**
	 * Method to add the first starting tile
	 * @return the first starting tile
	 */
	public static ArrayList<PeerTileToken> addStart1(){
		ArrayList<PeerTileToken> tilesTokens1 = new ArrayList<>();
		tilesTokens1.add(new PeerTileToken(new Tile().setPlace("Ma").addAnimals("Bu","Re"), null));
		tilesTokens1.add(new PeerTileToken(new Tile().setPlace("Ri").addAnimals("Sa", "Bu"), null));
		tilesTokens1.add(new PeerTileToken(new Tile().setPlace("Pr").addAnimals("Ou", "Sa"), null));
		return tilesTokens1;
	}

	 /**
     * Method to add the second starting tile
     * @return the second starting tile
     */
    public static ArrayList<PeerTileToken> addStart2() {
        ArrayList<PeerTileToken> tilesTokens2 = new ArrayList<>();
        tilesTokens2.add(new PeerTileToken(new Tile().setPlace("Mo").addAnimals("Ou", "Bu"), null));
        tilesTokens2.add(new PeerTileToken(new Tile().setPlace("Ma").addAnimals("Sa", "Ou"), null));
        tilesTokens2.add(new PeerTileToken(new Tile().setPlace("Fo").addAnimals("Wa", "Re"), null));
        return tilesTokens2;
    }


	/**
	 * Method to add the third starting tile
	 * @return the third starting tile
	 */
	public static ArrayList<PeerTileToken> addStart3(){
		//Troisième tuile de départ
		ArrayList<PeerTileToken> tilesTokens3 = new ArrayList<>();
		tilesTokens3.add(new PeerTileToken(new Tile().setPlace("Fo").addAnimals("Wa","Bu"),null));
		tilesTokens3.add(new PeerTileToken(new Tile().setPlace("Mo").addAnimals("Bu", "Ou"),null));
		tilesTokens3.add(new PeerTileToken(new Tile().setPlace( "Ma").addAnimals("Re", "Sa"),null));
		return tilesTokens3;

	}

	/**
	 * Method to add the fourth starting tile
	 * @return the fourth starting tile
	 */
	public static ArrayList<PeerTileToken> addStart4(){
		// Quatrième tuile de départ
		ArrayList<PeerTileToken> tilesTokens4 = new ArrayList<>();
		tilesTokens4.add(new PeerTileToken(new Tile().setPlace("Ri").addAnimals("Sa","Bu"),null));
		tilesTokens4.add(new PeerTileToken(new Tile().setPlace("Fo").addAnimals( "Wa", "Ou"),null));
		tilesTokens4.add(new PeerTileToken(new Tile().setPlace("Mo").addAnimals("Re", "Bu"),null));
		return tilesTokens4;
	}


	/**
	 * Method to add the fifth starting tile
	 * @return the fifth starting tile
	 */
	public static ArrayList<PeerTileToken> addStart5(){
		//Cinquième tuile de départ
		ArrayList<PeerTileToken> tilesTokens5 = new ArrayList<>();
		tilesTokens5.add(new PeerTileToken(new Tile().setPlace("Pr").addAnimals("Re","Bu"),null));
		tilesTokens5.add(new PeerTileToken(new Tile().setPlace("Ri").addAnimals("Sa", "Bu"),null));
		tilesTokens5.add(new PeerTileToken(new Tile().setPlace("Mo").addAnimals("Ou", "Wa"),null));
		return tilesTokens5;
    
	}


	/**
	 * Method to add the starting tiles
	 * @return the list of starting tiles
	 */
	public static ArrayList<ArrayList<PeerTileToken>> startTiles(){
    startTiles.add(addStart1());
		startTiles.add(addStart2());
		startTiles.add(addStart3());
		startTiles.add(addStart4());
		startTiles.add(addStart5());
	  Collections.shuffle(startTiles);
		return startTiles;
	}	

	/**
	 * Method to get the starting tiles (3 tiles)
	 * @return the starting tiles
	 */
	public static  ArrayList<PeerTileToken> getStartiles(){
		var chosenTokenTile = startTiles.remove(0);
		return chosenTokenTile;
	}


	/**
	 * Method to exploit the csv file which contains the tiles
	 * @return the list of tiles
	 * @throws IOException if the file is not found
	 */
	public static ArrayList<Tile> ExploitCsv() throws IOException {
		ArrayList<Tile> tiles = new ArrayList<>();
		try {
		  //Path path = Path.of("bin/game/data/Tuiles.csv"); // Jar file
			Path path = Path.of("Game/data/Tuiles.csv"); // IDE
      //System.out.println(Files.exists(path) + " fichier existe");
			var dataList = Files.readAllLines(path);
			for(var line : dataList){
				String[] parts = line.split(";");
				if (parts.length >= 2) {
          Tile tile = new Tile();
					var place = parts[0].substring(0, 2);
					if (Biome.contains(Places.class, place)){
						tile.setPlace(parts[0].substring(0, 2));
					} else {
						throw new IllegalArgumentException("Place inconnue");
					}
					var animals = parts[1].split(",");
					var animal1 = animals[0].substring(0, 2);
					var animal2 = animals[1].substring(0, 2);
					if (Biome.contains(Animal.class,animal1) && Biome.contains(Animal.class,animal2)){
						tile.addAnimals(animal1);
						tile.addAnimals(animal2);
          	tiles.add(tile);
					} else {
						  throw new IllegalArgumentException("Animaux inconnus");
					} 
        }
			}	
		} catch(IOException e){
			  throw new IOException("file not found");
		}
		return tiles;
	}

	/**
	 * Method to shuffle the tiles
	 * @param tiles the list of tiles
	 */
	public static void shuffTiles(ArrayList<Tile> tiles){
		Collections.shuffle(tiles);
	}

	public static ArrayList<Tile> tileBag(ArrayList<Tile> tiles, int nbPlayers){
		var tilePool  = new ArrayList<Tile>();
		var nbTiles = 20 * nbPlayers + 3;
		var iterator = tiles.iterator();
		int count = 0;
		while (iterator.hasNext() && count < nbTiles) {
			tilePool.add(iterator.next());
			iterator.remove();
			count++;
	  }  
    return tilePool;
	}


	@Override
	public String toString() {
		return "Tile{" +
				"place='" + places + '\'' +
				", animals=" + listAnimals +
				'}';
	}
		
}
