package game.color;
import java.awt.Color;
import java.util.Objects;
/**
 * This class enums the colors (ANSI COLOR code) which are used for the graphic game (animals)
 * @author Christophe TARATIBU
 */
public enum GraphicAnimalColor{
	/*
	 * Color blue
	 */
	BLUE(Color.BLUE),
	/*
	 * Color brown
	 */
	BROWN(new Color(165, 42, 42)), 
	/*
	 * Color pink
	 */
	PINK(Color.PINK),
	/**
	 * Color orange
	 */
	ORANGE(new Color(255, 165, 0)),
	/**
	 * Color taupe
	 */
	TAUPE(new Color(72, 60, 50)); 

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
	 * @return Color object
	 */
	public Color getColor() {
			return color;
	}
}

