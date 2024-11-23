package game.display;

/**
 * This class enums the colors which are used for the game
 */
public enum AnimalColor {
	RESET("\033[0m"),
  BLUE("\033[0;34m"),    // BLUE
	BROWN("\033[0;33m"),   // BROWN
	PINK("\033[0;35m"),    // PINK 
	ORANGE("\033[38;5;208m"), // ORANGE
	TAUPE("\033[38;5;95m"); // TAUPE	
	/**
	 * ANSI code linked with color
	 */
 	private final String ansiCode;

 /**
	* This method returns the ansi code linked with a color
	* @return ansiCode
  */
	public String get() {
		return ansiCode;
	}
	/**
	 * This constructor affects the ANSI code
	 * @param ansiCode
	 */
	AnimalColor(String ansiCode) {
		this.ansiCode = ansiCode;
	}

}
