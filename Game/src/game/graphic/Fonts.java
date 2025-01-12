package game.graphic;
import game.material.Tile;
import game.material.Token;
import game.player.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import com.github.forax.zen.ApplicationContext;
import java.util.Objects;


/**
* This class manages all of Cascadia's fonts
* @author Christophe TARATIBU
*/
public class Fonts {
  /**
	 * Writes the place and possble animals on a drawn tile
	 * @param graphics Graphics engine that will display the data of tile
	 * @param posSquareX Postion x of the square
	 * @param posSquareY Postion y of the square
	 * @param squareSize Size of the square (side's length)
	 * @param tile Tile containing the data
	 * @param size Font size
	 */
	public static void fontManageTiles(Graphics2D graphics,int posSquareX, int posSquareY, int squareSize,Tile tile, int size){
		Objects.requireNonNull(graphics);
		Objects.requireNonNull(tile);
    graphics.setColor(Color.WHITE);
		String place = tile.getPlace();
		String animals = tile.getListAnimals().toString();
    Font font = new Font("Arial", Font.BOLD, size); 
    graphics.setFont(font); 
		FontMetrics metrics = graphics.getFontMetrics();
		int text1X = posSquareX + (squareSize - metrics.stringWidth(place)) / 2;
		int text1Y = posSquareY - 25 + ((squareSize - metrics.getHeight()) / 2);
    int text2X = posSquareX + (squareSize - metrics.stringWidth(animals)) / 2;
		int text2Y = posSquareY + ((squareSize - metrics.getHeight()) / 2) + metrics.getAscent();
		graphics.drawString(place, text1X, text1Y);
		graphics.drawString(animals, text2X, text2Y);

	}

	/***
	 * Writes the animal (2 letters) on drawn token
	 * @param graphics  Graphics engine that will display the data of tile
	 * @param posSquareX Postion x of the square
	 * @param posSquareY Postion y of the square
	 * @param squareSize  Size of the square (side's length)
	 * @param token token containing the data
	 * @param size Font size
	 */
	public static void fontManageToken(Graphics2D graphics,int posSquareX, int posSquareY, int squareSize,Token token, int size){
		Objects.requireNonNull(graphics);
		Objects.requireNonNull(token);
		var animal = token.espece();
		graphics.setColor(Color.WHITE);
    Font font = new Font("Arial", Font.BOLD, size);
    graphics.setFont(font);
		FontMetrics metrics = graphics.getFontMetrics();
		int text1X = posSquareX + (squareSize - metrics.stringWidth(animal)) / 2;
		int text1Y = posSquareY  + ((squareSize - metrics.getHeight()) / 2);
		graphics.drawString(animal, text1X, text1Y);
	}
	
  /**
	 * Draws the player's name at the top-left of the screen
	 * @param context  using an existing {@code ApplicationContext}.
	 * @param player Player's data
	 * @param posX Position x on the screen
	 * @param posY Position y on the screen
	 * @param size Font size
	 */
	public static void drawPlayerName(ApplicationContext context,Player player, int posX, int posY, int size) {
		Objects.requireNonNull(context);
		Objects.requireNonNull(player);
		context.renderFrame(graphics -> {
			graphics.setColor(Color.BLACK);
			Font font = new Font("Arial", Font.BOLD, size);
			graphics.setFont(font);
			String playerName = "Environnement de " + player.name();
			FontMetrics metrics = graphics.getFontMetrics();
			int textX = posX;
			int textY = posY + metrics.getAscent();
			graphics.drawString(playerName, textX, textY);
		});
	}	
}
