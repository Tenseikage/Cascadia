package game.material;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import game.material;
public class Tile {
  private final ArrayList<String> places = new ArrayList<>();
  private final ArrayList<Animal> listAnimals  = new ArrayList<>();
  private boolean ideal;

  public Tile addPlace(String place) {
    // Ajout d'une place dans la tuile
    places.add(place);
    return this;
  }

  public Tile addAnimals(String animal){
    // Ajout d'animaux dans la tuile
    listAnimals.add(new Animal(animal));
    return this;
  }
  public Tile setBoolean(String choice){
    // Booléen habitat idéal (true/false)
    ideal = choice.equals("Oui");
    return this;
    
  }
  public static ArrayList<Tile> ExploitCsv() throws IOException {
    ArrayList<Tile> tiles = new ArrayList<>();
		try {
			FileReader filereader = new FileReader("Cascadia Game\\data\\Tuiles.csv");
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
						tiles.add(Tile.addPlace(data));
					}	
				} else {
					tiles.add(Tile.addPlace(biomes));
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
	public static void displayTiles(ArrayList<Tile> tiles) {
    //Affichage des tuiles
		for (Tile tile : tiles) {
			System.out.println(tile);
		}
	}

  @Override
  public String toString(){
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
  public static void main(String[] args) throws IOException {
		var tiles = ExploitCsv();
		displayTiles(tiles);
	}
  
 



}
