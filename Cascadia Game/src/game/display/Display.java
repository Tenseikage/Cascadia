package game.display;
import game.logic.Choice;
import game.logic.Position;
import game.material.Environment;
import game.material.Tile;
import game.material.Token;
import game.player.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
/**
 * Class which implements token and tile displaying on terminal
 */
public class Display {
	/**
	 * Method which displays the tile in ASCII
	 * @param choiceBoard Board which contains the 4 chosen tiles and tokens
	 */
	public void displayTile(Choice choiceBoard){
		Objects.requireNonNull(choiceBoard,"Erreur : choix de tuiles et jetons inexistant !");
		var tilesToken = choiceBoard.getChoiceBoard();
		StringBuilder topBorder = new StringBuilder();
    StringBuilder placeLine = new StringBuilder();
    StringBuilder animalLine = new StringBuilder();
    StringBuilder bottomBorder = new StringBuilder();
		StringBuilder blankLine = new StringBuilder();
		var separator = "  ";
		for (var entry : tilesToken.entrySet()){
			Tile tile = entry.getKey();
			topBorder.append("----------").append(separator); // Coin supérieur gauche, bord supérieur, coin supérieur droit
			placeLine.append("|   ").append(tile.getPlace()).append("   |").append(separator);// Bord gauche, espace vide, bord droit
			animalLine.append("|").append(tile.getListAnimals()).append("|").append(separator); 
			blankLine.append("|   ").append("  ").append("   |").append(separator);
			bottomBorder.append("----------").append(separator); // Coin inférieur gauche, bord inférieur, coin inférieur droit
		}
		System.out.println(topBorder);
    System.out.println(placeLine);
    System.out.println(animalLine);
		System.out.println(blankLine);
    System.out.println(bottomBorder);
		
	}
	/**
	 * Method which set colors to tiles or tokens
	 * @param color color(Animal,represented with 2 letters
	 * @return Color attributed, default blank
	 */
	public AnimalColor setColor(String color){
		return switch (color) {
			case "Ma" -> AnimalColor.BROWN;
			case "Ta" -> AnimalColor.TAUPE;
			case "Ro" -> AnimalColor.PINK;
			case "Bl" -> AnimalColor.BLUE;
			case "Or" -> AnimalColor.ORANGE;
			default -> AnimalColor.RESET;
		};
	}
	
	/**
	 * Method which displays the token in ASCII.
	 * @param choiceBoard Board which contains the 4 chosen tokens and tokens
	 * 
	 */
	public void displayToken(Choice choiceBoard){
    Objects.requireNonNull(choiceBoard, "Erreur : choix de tuiles et jetons inexistant !");
    var tilesToken = choiceBoard.getChoiceBoard();
    StringBuilder topBorder = new StringBuilder();
    StringBuilder colorLine = new StringBuilder();
    StringBuilder animalLine = new StringBuilder();
    StringBuilder bottomBorder = new StringBuilder();
    var separator = "   ";
    for (var entry : tilesToken.entrySet()) {
      Token token = entry.getValue();
      String color = token.color();
      AnimalColor animalColor = setColor(color);
      topBorder.append(animalColor.get()).append(" --------").append(AnimalColor.RESET.get()).append(separator);
      animalLine.append(animalColor.get()).append(" |  ").append(token.espece()).append("  |").append(AnimalColor.RESET.get()).append(separator);
      colorLine.append(animalColor.get()).append(" |  ").append("  ").append("  |").append(AnimalColor.RESET.get()).append(separator);
      bottomBorder.append(animalColor.get()).append(" --------").append(AnimalColor.RESET.get()).append(separator);
    }
    System.out.println(topBorder.toString());
    System.out.println(animalLine.toString());
    System.out.println(colorLine.toString());
    System.out.println(bottomBorder.toString());
  }
  
	/**
	 * Method which displays the rules of Cascadia
	 */
	public void displayRules() {
		// Affichage des règles
		System.out.println("===== CASCADIA ======\n");
		System.out.println("WELCOME TO THIS WONDERFUL GAME\n");
		System.out.println("HERE ARE SOME RULES :\n");
		System.out.println("YOU START THE GAME WITH 3 RANDOM TILES");
		System.out.println("AT EACH TURN, 4 TILES AND 4 TOKENS WILL BE PLACED ON THE TABLE");
		System.out.println("YOU WILL HAVE TO CHOOSE 1 OF THEM TO PLACE ON YOUR BOARD");
		System.out.println("BE CAREFUL, YOU MUST CHOOSE ACCORDING TO THE TILES ALREADY IN YOUR POSSESSION");
		System.out.println("THE GAME STOPS AFTER 20 ROUNDS HAVE BEEN PLAYED");
		System.out.println("THEN THE PLAYER WITH THE BEST COMBINATION OF TILES AND TOKENS WILL WIN\n\n");
  }
  
	/**
	 * Method which displays the environment of a player contains tiles and tokens
	 * @param gridEnv Player's grid with token and tiles placed.
	 */
	public void displayGridEnvPlayer(DisplayTools gridEnv){
		var maxPos = gridEnv.getMaxDim();
		var grid = gridEnv.getGrid();
		int presentTile = gridEnv.lengthGrid() * 5;
		int dataTiles = 0;
		for (int i = 0; i <= maxPos.getY(); i++) {
			for (int line = 0; line < 5; line++) { // Chaque tuile a 5 lignes
				for (int j = 0; j <= maxPos.getX(); j++) {
					if(!grid[i][j].equals("")){
							dataTiles++;
							String[] tileLines = grid[i][j].split("\n");
							System.out.print(tileLines[line] + " ");
					}		
				}
				if (dataTiles < presentTile)
					System.out.println();
			}
		}	
		System.out.println("\n");
	}

	/**
	 * This method checks if a token is in environment
	 * @param token
	 * @return
	*/
	public boolean checkTokenEnv(Token token){
		return token != null;
	}

	/**
	 * This method displays a tile with no color if no token is placed and a colored tile
	 * in the other case
	 * @param tile  Tile of an environment
	 * @param entry Environment converted in a entry
	 * @param color Color : blank (no token) ; token
	 * @param token Token of an environement, 
	 * @return Stringbuilder which contains the ASCII of a tile
	 */
 	public StringBuilder displayTileEnv(Tile tile, Map.Entry<HashMap<Tile, Token>, Position> entry, AnimalColor color,Token token) {
    StringBuilder tileRepresentation = new StringBuilder();
		if (token == null){ // No token was placed by the player
			tileRepresentation.append(color.get()).append("----------\n").append(AnimalColor.RESET.get()); // Coin supérieur gauche, bord supérieur, coin supérieur droit
			tileRepresentation.append(color.get()).append("|   ").append(tile.getPlace()).append("   |\n").append(AnimalColor.RESET.get()); // Bord gauche, espace vide, bord droit
			tileRepresentation.append(color.get()).append("|").append(tile.getListAnimals()).append("|\n").append(AnimalColor.RESET.get());
			tileRepresentation.append(color.get()).append("| ").append(entry.getValue().toString()).append("  |\n").append(AnimalColor.RESET.get());
			tileRepresentation.append(color.get()).append("----------").append(AnimalColor.RESET.get()); // Coin inférieur gauche, bord inférieur, coin inférieur droit
		} else{
			tileRepresentation.append(color.get()).append("----------\n").append(AnimalColor.RESET.get()); // Coin supérieur gauche, bord supérieur, coin supérieur droit
			tileRepresentation.append(color.get()).append("|  ").append(tile.getPlace()).append("    |\n").append(AnimalColor.RESET.get()); // Bord gauche, espace vide, bord droit
			tileRepresentation.append(color.get()).append("|  ").append(token.espece()).append("    |\n").append(AnimalColor.RESET.get());
			tileRepresentation.append(color.get()).append("| ").append(entry.getValue().toString()).append("  |\n").append(AnimalColor.RESET.get());
			tileRepresentation.append(color.get()).append("----------").append(AnimalColor.RESET.get()); // Coin inférieur gauche, bord inférieur, coin inférieur droit
		}
    return tileRepresentation;
  }

  /**
	 * This method displays the environment (tiles and tokens) of a player
	 * @param env Player's environment
	 * @param grid Player's grid (tiles and tokens)
	 * @param player Player data
	 */
  public void displayEnvPlayer(Environment env,DisplayTools grid, Player player){
		System.out.println("player" + player.name());
		Objects.requireNonNull(env, "Erreur : environnement inexistant");
		var envPlayer = env.getEnvironment();
		AnimalColor animalColor;
		for (var entry : envPlayer.entrySet()) {
			for (var entryTile : entry.getKey().entrySet()) {
				Tile tile = entryTile.getKey();
				Token token = entryTile.getValue();
					if (checkTokenEnv(token)){
						String color = token.color();
						animalColor = setColor(color);
					} else {
						animalColor = setColor("");
					}
					Position pos = entry.getValue();
					StringBuilder tileRepresentation = displayTileEnv(tile, entry,animalColor,token);
					grid.addTileRepresent(pos.getY(), pos.getX(), tileRepresentation.toString());
				}
		}
		displayGridEnvPlayer(grid);
  }

  /**
	 * 
	 * @param scanner
	 * @return
	 */
	public int displayChoiceMod(Scanner scanner){
		System.out.println("SELECTIONNER LE MODE :\n\t(1) -> MODE FAMILIALE\n\t(2) -> MODE INTERMEDIAIRE\n");
		int mode = scanner.nextInt();
    switch (mode) {
    	case 1 -> {
        System.out.println("MODE FAMILIALE \nMarquez des points pour chaque groupe d’animaux de la même espèce, en fonction de sa taille. La forme du groupe n'importe pas. \nEntrez ok pour commencer.");
        scanner.next();
      }
      case 2 -> {
        System.out.println("MODE INTERMEDIAIRE \nLa variante intermédiaire se joue de la même manière que la variante familiale, mais en utilisant la carte Décompte de la faune spécifique à la variante intermédiaire. \nEntrez ok pour commencer.");
        scanner.next();
      }
      default -> {
        System.out.println("ERREUR SUR LA SELECTION -> MODE FAMILIALE PAR DEFAULT.");
        mode = 1;
      }
    }
		return mode;
	}

	public void displayAll(Choice board){
		displayTile(board); 
		displayToken(board);
	}



} 
