package game.graphic;
import game.material.Tile;
import game.material.Token;
import game.player.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.github.forax.zen.ApplicationContext;

public class Fonts {
	/**
	 * @author Christophe TARATIBU
	 * Management of Cascadia's game fonts
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

	public static void fontManageTiles(Graphics2D graphics,int posSquareX, int posSquareY, int squareSize,Tile tile, int size){
		/*Gestion affichage données tuile */
		//var board = new Choice();
    graphics.setColor(Color.WHITE);
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

	public static void fontManageToken(Graphics2D graphics,int posSquareX, int posSquareY, int squareSize,Token token, int size){
		var animal = token.espece();
		graphics.setColor(Color.WHITE);
    Font font = new Font("Arial", Font.BOLD, size); // Créer une nouvelle instance de Font avec la taille souhaitée
    graphics.setFont(font);
		FontMetrics metrics = graphics.getFontMetrics();
		int text1X = posSquareX + (squareSize - metrics.stringWidth(animal)) / 2;
		int text1Y = posSquareY  + ((squareSize - metrics.getHeight()) / 2);
		graphics.drawString(animal, text1X, text1Y);
	}

	public static void drawPlayerName(ApplicationContext context,Player player, int posX, int posY, int size) {
		context.renderFrame(graphics -> {
			graphics.setColor(Color.BLACK); // Définir la couleur du texte
			Font font = new Font("Arial", Font.BOLD, size); // Créer une nouvelle instance de Font avec la taille souhaitée
			graphics.setFont(font);
			String playerName = "Environnement de " + player.name();
			FontMetrics metrics = graphics.getFontMetrics();
			int textX = posX;
			int textY = posY + metrics.getAscent(); // Ajouter l'ascent pour positionner correctement le texte
			graphics.drawString(playerName, textX, textY);
		});
  }

	public static void drawCenterPlayerText(ApplicationContext context, int size){
		context.renderFrame(graphics -> {
			String text = "Création des joueurs ";
			var screenInfo = context.getScreenInfo();
      var width = screenInfo.width();
      var height = screenInfo.height();
			graphics.setColor(Color.BLACK); // Définir la couleur du texte
      Font font = new Font("Arial", Font.BOLD, size); // Créer une nouvelle instance de Font avec la taille souhaitée
      graphics.setFont(font);
      FontMetrics metrics = graphics.getFontMetrics();
      int textWidth = metrics.stringWidth(text);
      int textHeight = metrics.getHeight();
      int textX = (width - textWidth) / 2;
      int textY = (height - textHeight) / 2 + metrics.getAscent();
      graphics.drawString(text, textX, textY - 200);
		});
	}


}
