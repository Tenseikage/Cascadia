package game.display;
import game.color.AnimalColor;
import game.logic.Choice;
import game.logic.Position;
import game.material.PeerTileToken;
import game.material.Tile;
import game.material.Token;
import game.player.Player;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class which implements token and tile displaying on terminal
 * @author Christophe TARATIBU
 */
public class Display {
	/**
	 * Height of the ascii square
	 */
	public static final int HEIGHT_SQUARE = 5;
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
		for (var elem : tilesToken){
			Tile tile = elem.getTile();
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
	 * Sets colors to tiles or tokens
	 * @param color color(Animal,represented with 2 letters)
	 * @return Color attributed, default blank
	 */
	public AnimalColor setColor(String color){
		Objects.requireNonNull(color, "Erreur : couleur inexistante !");
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
	 * Displays the token in ASCII.
	 * @param choiceBoard Board which contains the 4 chosen tokens and tokens
	 */
	public void displayToken(Choice choiceBoard){
		Objects.requireNonNull(choiceBoard, "Error : inexistent tile and token choice !");
		var tilesToken = choiceBoard.getChoiceBoard();
		StringBuilder topBorder = new StringBuilder();
		StringBuilder colorLine = new StringBuilder();
		StringBuilder animalLine = new StringBuilder();
		StringBuilder bottomBorder = new StringBuilder();
		var separator = "   ";
		for (var entry : tilesToken) {
			Token token = entry.getToken();
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
	 * Displays the rules of Cascadia
	 */
	public void displayRules() {
		System.out.println("===== CASCADIA ======\n");
		System.out.println("BIENVENUE DANS CE JEU DE STRATEGIE\n");
		System.out.println("VOICI LES REGLES:\n");
		System.out.println("VOUS COMMENCEZ LE JEU AVEC UNE TUILE DE DEPART DANS VOTRE ENVIRONNEMENT");
		System.out.println("A CHAQUE TOUR, 4 TUILES ET 4 JETONS SONT TIREES ALEATOIREMENT");
		System.out.println("VOUS DEVEZ CHOISIR UN DE CHAQUE ET LES PLACER DANS VOTRE ENVIRONNEMENT");
		System.out.println("ATTENTION, VOUS DEVEZ CHOISIR EN FONCTION DES TUILES DEJA EN VOTRE POSSESSION");
		System.out.println("LE JEU S'ARRETE APRES 20 TOURS JOUES");
		System.out.println("A LA FIN DU JEU, LES POINTS SONT CALCULES\n");
		System.out.println("BON JEU !\n");
	}
	
	/**
	 * Displays the environment of a player contains tiles and tokens
	 * @param gridEnv Player's grid with token and tiles placed.
	 */
	public void displayGridEnvPlayer(DisplayTools gridEnv){
		Objects.requireNonNull(gridEnv);
		var maxPos = gridEnv.getMaxDim();
		var grid = gridEnv.getGrid();
		int presentTile = gridEnv.lengthGrid() * HEIGHT_SQUARE;
		int dataTiles = 0;
		for (int i = 0; i <= maxPos.getY(); i++) {
			for (int line = 0; line < HEIGHT_SQUARE; line++) { // Chaque tuile a 5 lignes
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
	 * Checks if a token is in environment
	 * @param token
	 * @return boolean
	*/
	public boolean checkTokenEnv(Token token){
		return token != null;
	}

	/**
	 * Displays a tile with no color if no token is placed and a colored tile
	 * in the other case
	 * @param tile  Tile of an environment
	 * @param entry Environment converted in a entry
	 * @param color Color : blank (no token) ; token
	 * @param token Token of an environement, 
	 * @return Stringbuilder which contains the ASCII of a tile
	 */
	public StringBuilder displayTileEnv(Tile tile, Map.Entry<PeerTileToken, Position> entry, AnimalColor color, Token token) {
		Objects.requireNonNull(tile);
		Objects.requireNonNull(entry);
		Objects.requireNonNull(color);
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
	 * Displays the environment (tiles and tokens) of a player
	 * @param grid Player's grid (tiles and tokens)
	 * @param player Player data
	 */
	public void displayEnvPlayer(DisplayTools grid, Player player){
		Objects.requireNonNull(grid);
		Objects.requireNonNull(player);
		System.out.println("Environnement " + player.name());
		var envPlayer = player.boardPlayer().getEnvironment();
		AnimalColor animalColor;
		for (var entry : envPlayer.entrySet()) {
			Tile tile = entry.getKey().getTile();
			Token token = entry.getKey().getToken();
			if (checkTokenEnv(token)){
				String color = token.color();
				animalColor = setColor(color);
			} else {
				animalColor = setColor("");
			}
			Position pos = entry.getValue();
			StringBuilder tileRepresentation = displayTileEnv(tile, entry, animalColor, token);
			grid.addTileRepresent(pos.getY(), pos.getX(), tileRepresentation.toString());
		}
		displayGridEnvPlayer(grid);
	}

			
				

	/**
	 * Displays the mode for the final score count in 
	 * this game
	 * @param scanner choice of 1st player
	 * @return int: family mode 1 or Intermediate mode 2, default 1
	 */
	public int displayChoiceMod(Scanner scanner){
		Objects.requireNonNull(scanner);
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
  /**
	 * Displays tiles and tokens for to choose
	 * @param board Board which contains the 4 chosen tiles and tokens
	 */
	public void displayAll(Choice board){
		Objects.requireNonNull(board, "Erreur : tableau de choix inexistant !");
		displayTile(board); 
		displayToken(board);
	}

}
