package game.graphic;
import game.material.PeerTileToken;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Fonts {
	/**
	 * Management of Cascadia's fonts
	 */
	public static void fontManage(Graphics2D graphics,int posSquareX, int posSquareY, int squareSize){
		String word = "Tuile";
		String word2 = "Animaux";
		int fontSize = 20; // Définir la taille de la police
    Font font = new Font("Arial", Font.BOLD, fontSize); // Créer une nouvelle instance de Font avec la taille souhaitée
    graphics.setFont(font); // Appliquer la nouvelle police à l'objet Graphics2D
		FontMetrics metrics = graphics.getFontMetrics();
		int text1X = posSquareX + (squareSize - metrics.stringWidth(word)) / 2;
		int text1Y = posSquareY - 25 + ((squareSize - metrics.getHeight()) / 2);
    int text2X = posSquareX + (squareSize - metrics.stringWidth(word2)) / 2;
		int text2Y = posSquareY + ((squareSize - metrics.getHeight()) / 2) + metrics.getAscent();
		graphics.drawString(word, text1X, text1Y);
		graphics.drawString(word2, text2X, text2Y);
	}

	public static void fontManageTiles(Graphics2D graphics,int posSquareX, int posSquareY, int squareSize,PeerTileToken peer, int size){
		/*Gestion affichage données tuile */
		//var board = new Choice();
		var tile = peer.getTile();
		String place = tile.getPlace();
		String animals = tile.getListAnimals().toString();
    Font font = new Font("Arial", Font.BOLD, size); // Créer une nouvelle instance de Font avec la taille souhaitée
    graphics.setFont(font); // Appliquer la nouvelle police à l'objet Graphics2D
		FontMetrics metrics = graphics.getFontMetrics();
		int text1X = posSquareX + (squareSize - metrics.stringWidth(place)) / 2;
		int text1Y = posSquareY - 25 + ((squareSize - metrics.getHeight()) / 2);
    int text2X = posSquareX + (squareSize - metrics.stringWidth(animals)) / 2;
		int text2Y = posSquareY + ((squareSize - metrics.getHeight()) / 2) + metrics.getAscent();
		graphics.drawString(place, text1X, text1Y);
		graphics.drawString(animals, text2X, text2Y);

	}

	public static void fontManageToken(Graphics2D graphics,int posSquareX, int posSquareY, int squareSize,PeerTileToken peer, int size){
		var token = peer.getToken();
		var animal = token.espece();
    Font font = new Font("Arial", Font.BOLD, size); // Créer une nouvelle instance de Font avec la taille souhaitée
    graphics.setFont(font);
		FontMetrics metrics = graphics.getFontMetrics();
		int text1X = posSquareX + (squareSize - metrics.stringWidth(animal)) / 2;
		int text1Y = posSquareY  + ((squareSize - metrics.getHeight()) / 2);
		graphics.drawString(animal, text1X, text1Y);
	}
}
