package game.color;
import java.awt.Color;
import java.util.Objects;
/**
 * This class enums the colors which are used for the game (places)
 * @author Christophe TARATIBU
 */
public enum GraphicPlaceColor {
	/**
	 * Color grey
	 */
	GREY(new Color(128,128,18)),
	/**
	 * Color dark green
	 */
	DARKGREEN(new Color(0,100,0)),
	/**
	 * Color yellow
	 */
	YELLOW(new Color(255,255,0)),
	/**
	 * Color light green
	 */
	LIGHTGREEN(new Color(144,238,144)),
	/**
	 * Color light blue
	 */
	LIGHTBLUE(new Color(173,216,230));

	//instance color field
	private final Color color;

	/**
	 * This constructor affects the color
	 * @param color Color 
	 */
  GraphicPlaceColor(Color color) {
		Objects.requireNonNull(color);
		this.color = color;
  }
 
	/**
	 * This method returns the color
	 * @return Color object
	 */
	public Color getColor(){
		return color;
	}



	


}
