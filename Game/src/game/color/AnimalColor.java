package game.color;
import java.util.Objects;

/**
 * This class enums the colors which are used for the game (animals)
 * @author Christophe TARATIBU
 */
public enum AnimalColor{
	RESET("\033[0m"),
  BLUE("\033[0;34m"),    // BLUE
	BROWN("\033[38;5;94m"),   // BROWN
	PINK("\033[0;35m"),    // PINK 
	ORANGE("\033[38;5;208m"), // ORANGE
	TAUPE("\033[38;5;95m"); // TAUPE	
	/**
	 * ANSI code linked with color
	 */
 	private final String ansiCode;
	/**
	 * This constructor affects the ANSI code
	 * @param ansiCode
	 */
	AnimalColor(String ansiCode) {
		Objects.requireNonNull(ansiCode);
		this.ansiCode = ansiCode;
	}

	
 /**
	* Returns the ansi code linked with a color
	* @return ansiCode
  */
	public String get() {
		return ansiCode;
	}

}
