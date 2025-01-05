package game.graphic;
import java.awt.Color;
import java.util.Objects;

public enum GraphicAnimalColor {
	BLUE(Color.BLUE),
	BROWN(new Color(165, 42, 42)), // Définir la couleur marron avec les valeurs RGB
	PINK(Color.PINK),
	ORANGE(new Color(255, 165, 0)), // Définir la couleur orange avec les valeurs RGB
	TAUPE(new Color(72, 60, 50)); // Définir la couleur taupe avec les valeurs RGB

	private final Color color;

	GraphicAnimalColor(Color color) {
		Objects.requireNonNull(color);
		this.color = color;
	}

	public Color getColor() {
			return color;
	}
}

