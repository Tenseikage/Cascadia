package game.color;
import java.awt.Color;
import java.util.Objects;
/**
 * @author Christophe TARATIBU
 * This class enums the colors which are used for the graphic game (animals)
 */
public enum GraphicAnimalColor{
	BLUE(Color.BLUE),
	BROWN(new Color(165, 42, 42)), // Définir la couleur marron avec les valeurs RGB
	PINK(Color.PINK),
	ORANGE(new Color(255, 165, 0)), // Définir la couleur orange avec les valeurs RGB
	TAUPE(new Color(72, 60, 50)); // Définir la couleur taupe avec les valeurs RGB

	//Color instance field
	private final Color color;

	/**
	 * Constructor which sets the color
	 * @param color
	 */
	GraphicAnimalColor(Color color) {
		Objects.requireNonNull(color);
		this.color = color;
	}
	/**
	 * Returns the color
	 * @return
	 */
	public Color getColor() {
			return color;
	}
}

