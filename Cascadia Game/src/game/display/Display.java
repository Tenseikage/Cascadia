package game.display;
import game.logic.Position;
import game.material.Choice;
import game.material.Environment;
import game.material.Tile;
import game.material.Token;
import java.util.Objects;

public class Display {
	public void displayTile(Choice choiceBoard){
		//affichage de la tuile
		Objects.requireNonNull(choiceBoard,"Erreur : choix de tuiles et jetons inexistant !");
		var tilesToken = choiceBoard.getChoiceBoard();
		StringBuilder topBorder = new StringBuilder();
    StringBuilder placeLine = new StringBuilder();
    StringBuilder animalLine = new StringBuilder();
    StringBuilder bottomBorder = new StringBuilder();
		var separator = "  ";
		for (var entry : tilesToken.entrySet()){
			Tile tile = entry.getKey();
			topBorder.append("----------").append(separator); // Coin supérieur gauche, bord supérieur, coin supérieur droit
			placeLine.append("|  ").append(tile.getPlace()).append("  |").append(separator);// Bord gauche, espace vide, bord droit
			animalLine.append("|").append(tile.getListAnimals()).append("|").append(separator); 
			bottomBorder.append("----------").append(separator); // Coin inférieur gauche, bord inférieur, coin inférieur droit
		}
		System.out.println(topBorder);
    System.out.println(placeLine);
    System.out.println(animalLine);
    System.out.println(bottomBorder);
		
	}
	public AnimalColor setColor(String color){
		return switch (color) {
			case "Ma" -> AnimalColor.BROWN;
			case "Ta" -> AnimalColor.TAUPE;
			case "Ro" -> AnimalColor.PINK;
			case "Bl" -> AnimalColor.BLUE;
			case "Or" -> AnimalColor.ORANGE;
			default -> throw new IllegalArgumentException("Erreur : Couleur invalide ou inconnue");
		};
	}
	
	public void displayToken(Choice choiceBoard) throws IllegalArgumentException {
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
    // Affichage des bordures et des lignes
    System.out.println(topBorder.toString());
    System.out.println(animalLine.toString());
    System.out.println(colorLine.toString());
    System.out.println(bottomBorder.toString());
  }

	public void displayRules() {
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
 public void displayGridEnvPlayer(DisplayTools gridEnv){
	var maxPos = gridEnv.getMaxDim();
	var grid = gridEnv.getGrid();
	for (int i = 0; i <= maxPos.getY(); i++) {
		// Afficher chaque ligne de la grille
		for (int line = 0; line < 5; line++) { // Chaque tuile a 5 lignes
			for (int j = 0; j <= maxPos.getX(); j++) {
				if(!grid[i][j].equals("          ")){
						String[] tileLines = grid[i][j].split("\n");
						System.out.print(tileLines[line] + " ");
				}		
			}
			System.out.println();
		}
	}	

 }

 public void displayEnvPlayer(Environment env,DisplayTools grid){
	Objects.requireNonNull(env, "Erreur : environnement inexistant");
		var envPlayer = env.getEnvironment();
		// Placer les tuiles dans la grille
		for (var entry : envPlayer.entrySet()) {
				for (var entryTile : entry.getKey().entrySet()) {
					Tile tile = entryTile.getKey();
					Position pos = entry.getValue();
					StringBuilder tileRepresentation = new StringBuilder();
					tileRepresentation.append("----------\n"); // Coin supérieur gauche, bord supérieur, coin supérieur droit
					tileRepresentation.append("| ").append(tile.getPlace()).append("   |\n"); // Bord gauche, espace vide, bord droit
					tileRepresentation.append("|").append(tile.getListAnimals()).append("|\n");
					tileRepresentation.append("| ").append(entry.getValue().toString()).append("  |\n");
					tileRepresentation.append("----------"); // Coin inférieur gauche, bord inférieur, coin inférieur droit
					grid.addTileRepresent(pos.getY(), pos.getX(), tileRepresentation.toString());
				}
		}
		displayGridEnvPlayer(grid);
  }
}
