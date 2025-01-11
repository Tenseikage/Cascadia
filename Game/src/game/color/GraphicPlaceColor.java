package game.color;
import java.awt.Color;
/**
 * Color of place's tiles
 */
public enum GraphicPlaceColor {
	GREY(new Color(128,128,18)),
	DARKGREEN(new Color(0,100,0)),
	YELLOW(new Color(255,255,0)),
	LIGHTGREEN(new Color(144,238,144)),
	LIGHTBLUE(new Color(173,216,230));

	private final Color color;

    GraphicPlaceColor(Color color) {
			this.color = color;
    }

		public Color getColor(){
			return color;
		}



	


}
