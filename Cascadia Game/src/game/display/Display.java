package game.display;
import game.material.Choice;
import game.material.Environment;
import game.material.Tile;
import game.material.Token;
import java.util.Objects;

public class Display {
	public void displayEnvPlayer(Environment environment){
		Objects.requireNonNull(environment, "Erreur : environnement inexistant");
		var board = environment.getEnvironment();
		var entry = board.entrySet().iterator().next();
		Tile tile = entry.getKey();
		System.out.println("---"); // Coin supérieur gauche, bord supérieur, coin supérieur droit
		System.out.println("|" +  tile.getPlace() + "|");// Bord gauche, espace vide, bord droit
		System.out.println("|" +  tile.getListAnimals() + "|"); 
		System.out.println("---"); // Coin inférieur gauche, bord inférieur, coin inférieur droit
	
		
	}
	

	public void displayTile(Choice choiceBoard){
		//affichage de la tuile
		Objects.requireNonNull(choiceBoard,"Erreur : choix de tuiles et jetons inexistant !");
		var tilesToken = choiceBoard.getChoiceBoard();
		StringBuilder topBorder = new StringBuilder();
    StringBuilder placeLine = new StringBuilder();
    StringBuilder animalLine = new StringBuilder();
    StringBuilder bottomBorder = new StringBuilder();
		var separator = " ";
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
      AnimalColor animalColor;
      switch (color) {
        case "Ma" -> animalColor = AnimalColor.BROWN;
        case "Ta" -> animalColor = AnimalColor.TAUPE;
        case "Ro" -> animalColor = AnimalColor.PINK;
        case "Bl" -> animalColor = AnimalColor.BLUE;
        case "Or" -> animalColor = AnimalColor.ORANGE;
        default -> throw new IllegalArgumentException("Erreur : Couleur invalide ou inconnue");
        }
      topBorder.append(animalColor.get()).append("--------").append(AnimalColor.RESET.get()).append(separator);
      animalLine.append(animalColor.get()).append("|  ").append(token.espece()).append("  |").append(AnimalColor.RESET.get()).append(separator);
      colorLine.append(animalColor.get()).append("|  ").append(token.color()).append("  |").append(AnimalColor.RESET.get()).append(separator);
      bottomBorder.append(animalColor.get()).append("--------").append(AnimalColor.RESET.get()).append(separator);
    }
    // Affichage des bordures et des lignes
    System.out.println(topBorder.toString());
    System.out.println(animalLine.toString());
    System.out.println(colorLine.toString());
    System.out.println(bottomBorder.toString());
  }
}
