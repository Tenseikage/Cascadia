package game.display;

public enum AnimalColor {
	RESET("\033[0m"),
    BLUE("\033[0;34m"),    // BLUE
	BROWN("\033[0;33m"),   // BROWN
	PINK("\033[0;35m"),    // PINK 
	ORANGE("\033[38;5;208m"), // ORANGE
	TAUPE("\033[38;5;95m"); // TAUPE	
 	private final String ansiCode;

	public String get() {
		return ansiCode;
	}

	AnimalColor(String ansiCode) {
		this.ansiCode = ansiCode;
	}

}
