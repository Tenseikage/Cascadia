package game.material;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Tile {
  private final ArrayList<String> places;
  private final ArrayList<String> listAnimals;

	public Tile(){
		// Constructeur initialisant les listes et le booléen
		this.places = new ArrayList<>();
		this.listAnimals = new ArrayList<>();
	}


  public Tile addPlaces(String... places) {
    this.places.addAll(Arrays.asList(places));
      return this;
    }

  public Tile addAnimals(String... animals) {
    this.listAnimals.addAll(Arrays.asList(animals));
    return this;
    }

  public static ArrayList<ArrayList<Tile>> startTile() {
		// Définition des habitats de départ en dur
		ArrayList<ArrayList<Tile>> startTiles = new ArrayList<>();
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Marais").addAnimals("Buse","Renard"),
		new Tile().addPlaces("Rivieres").addAnimals("Saumon", "Buse"),
		new Tile().addPlaces("Prairies").addAnimals("Ours", "Saumon"))));
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Montagne").addAnimals("Ours","Buse"),
		new Tile().addPlaces("Marais").addAnimals("Saumon", "Ours"),
		new Tile().addPlaces("Forets").addAnimals("Wapiti", "Renard"))));
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Foret").addAnimals("Wapiti","Buse"),
		new Tile().addPlaces("Montagnes").addAnimals("Buse", "Ours"),
		new Tile().addPlaces( "Marais").addAnimals("Renard", "Saumon"))));
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Riviere").addAnimals("Saumon","Buse"),
		new Tile().addPlaces("Forets").addAnimals( "Wapiti", "Ours"),
		new Tile().addPlaces("Montagnes").addAnimals("Renard", "Buse"))));
		startTiles.add(new ArrayList<>(Arrays.asList(new Tile().addPlaces("Prairie").addAnimals("Renard","Buse"),
		new Tile().addPlaces("Rivieres").addAnimals("Saumon", "Buse"),
		new Tile().addPlaces("Montagnes").addAnimals("Ours", "Wapiti"))));
		return startTiles;
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
          tile.addPlaces(parts[0].split(","));
          tile.addAnimals(parts[1].split(","));
          tiles.add(tile);
        }
			}	
		} catch(IOException e){
			  throw new IOException("file not found");
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
