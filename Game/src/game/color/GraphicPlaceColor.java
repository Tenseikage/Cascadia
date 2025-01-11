package game.color;
import java.awt.Color;
/**
 * @author Christophe TARATIBU
 * This class enums the colors which are used for the game (places)
 */
public enum GraphicPlaceColor {
	GREY(new Color(128,128,18)),
	DARKGREEN(new Color(0,100,0)),
	YELLOW(new Color(255,255,0)),
	LIGHTGREEN(new Color(144,238,144)),
	LIGHTBLUE(new Color(173,216,230));

	//instance color field
	private final Color color;

	/**
	 * This constructor affects the color
	 * @param color
	 */
  GraphicPlaceColor(Color color) {
		this.color = color;
  }
 
	/**
	 * This method returns the color
	 * @return
	 */
	public Color getColor(){
		return color;
	}



	


}
