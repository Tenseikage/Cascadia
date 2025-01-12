package game.material;

import java.util.Objects;
/***
 * This class enumerates the places present in game
 * @author Christophe TARATIBU
 */
public enum Places implements Biome {
	/**
	 * Mountain 
	 */
	MONTAGNE("Mo"),
	/**
	 * Meadow
	 */
	PRAIRIE("Pr"),

	/**
	 * River
	 */
	RIVIERE("Ri"),
	/**
	 * Forest
	 */
	FORET("Fo"),
	/**
	 * Swamp
	 */
	MARAIS("Ma");

	private final String place;

	/**
	 * This constructor affects the place accepted by the tile
	 * @param place Game place
	 */
	Places(String place) {
		Objects.requireNonNull(place, "Error: null place");
		this.place = place;
	}

	/**
	* This method returns place linked with a tile
	* @return specie
  */
	@Override
	public String get() {
		return place;
	}

	



}
