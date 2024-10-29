package game.material;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Tile {
  private final ArrayList<String> places;
  private final ArrayList<String> listAnimals;
  private boolean ideal;

	public Tile(){
		// Constructeur initialisant les listes et le booléen
		this.places = new ArrayList<>();
		this.listAnimals = new ArrayList<>();
		this.ideal = false;
	}


  public Tile addPlaces(String... places) {
    this.places.addAll(Arrays.asList(places));
      return this;
    }

  public Tile addAnimals(String... animals) {
    this.listAnimals.addAll(Arrays.asList(animals));
    return this;
    }

  public Tile setBoolean(String choice) {
  	this.ideal = choice.equals("Oui");
    return this;
  }

  public static Tile[][] startTile() {
		// Définition des habitats de départ en dur
		var startTiles = new Tile[5][3];
		startTiles[0][0] = new Tile().addPlaces("Marais").addAnimals("Buse").setBoolean("Oui");
		startTiles[0][1] = new Tile().addPlaces("Riviere", "Foret").addAnimals("Saumon", "Wapiti", "Buse").setBoolean("Non");
		startTiles[0][2] = new Tile().addPlaces("Montagne", "Prairie").addAnimals("Ours", "Saumon").setBoolean("Non");
		startTiles[1][0] = new Tile().addPlaces("Montagne").addAnimals("Ours").setBoolean("Oui");
		startTiles[1][1] = new Tile().addPlaces("Riviere", "Prairie").addAnimals("Saumon", "Ours").setBoolean("Non");
		startTiles[1][2] = new Tile().addPlaces("Foret", "Marais").addAnimals("Wapiti", "Buse", "Renard").setBoolean("Non");
		startTiles[2][0] = new Tile().addPlaces("Foret").addAnimals("Wapiti").setBoolean("Oui");
		startTiles[2][1] = new Tile().addPlaces("Montagne").addAnimals("Buse", "Wapiti", "Ours").setBoolean("Non");
		startTiles[2][2] = new Tile().addPlaces("Prairie", "Marais").addAnimals("Renard", "Saumon").setBoolean("Non");
		startTiles[3][0] = new Tile().addPlaces("Riviere").addAnimals("Saumon").setBoolean("Oui");
		startTiles[3][1] = new Tile().addPlaces("Prairie", "Foret").addAnimals("Saumon", "Wapiti", "Ours").setBoolean("Non");
		startTiles[3][2] = new Tile().addPlaces("Montagne", "Marais").addAnimals("Renard", "Buse").setBoolean("Non");
		startTiles[4][0] = new Tile().addPlaces("Prairie").addAnimals("Renard").setBoolean("Oui");
		startTiles[4][1] = new Tile().addPlaces("Marais", "Riviere").addAnimals("Saumon", "Buse", "Renard").setBoolean("Non");
		startTiles[4][2] = new Tile().addPlaces("Montagne", "Foret").addAnimals("Ours", "Wapiti").setBoolean("Non");
		return startTiles;
	}

	public static ArrayList<Tile> ExploitCsv() throws IOException {
		ArrayList<Tile> tiles = new ArrayList<>();
		try {
			FileReader filereader = new FileReader("Cascadia Game/data/Tuiles.csv");
			BufferedReader reader = new BufferedReader(filereader);
			var line = reader.readLine();
			while(line != null){
				var Tile = new Tile();
				var array = line.split(";");
				var biomes = array[0];
				var animals = array[1];
				if (biomes.contains(",")){
					var splitted = array[0].split(",");
					for (var data : splitted){
						tiles.add(Tile.addPlaces(data));
					}	
				} else {
					tiles.add(Tile.addPlaces(biomes));
				}
				if (animals.contains(",")){
					var splitted  = array[1].split(",");
					for(var animal : splitted){
						tiles.add(Tile.addAnimals(animal));
					}
				} else {
					tiles.add(Tile.addAnimals(animals));
				}
				System.out.println(array[2]);
				tiles.add(Tile.setBoolean(array[2]));
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e) {
			throw new IllegalArgumentException("Error : no file/path found");
		}
		return tiles;
	}
	public static void shuffTiles(ArrayList<Tile> tiles){
		// Mélange des tuiles pour la distribution au hasars
		Collections.shuffle(tiles);
	}
		
	public static void drawTiles(){
		System.out.println("---"); // Coin supérieur gauche, bord supérieur, coin supérieur droit
		System.out.println("| |");     // Bord gauche, espace vide, bord droit
		System.out.println("---"); // Coin inférieur gauche, bord inférieur, coin inférieur droit

	}
	public static void displayTiles(ArrayList<Tile> tiles) {
    //Affichage des tuiles
		for (Tile tile : tiles) {
			System.out.println(tile);
		}
	}



  @Override
  public String toString(){
    // Affichage sous forme de liste
    var builder  = new StringBuilder();
    builder.append("[")
    .append(places.toString())
    .append(",")
    .append(listAnimals.toString())
    .append(",")
    .append(Boolean.toString(ideal))
    .append("]");
    return builder.toString();

  }
}
