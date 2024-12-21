package game.material;

import java.util.Objects;

public enum Places implements Biome {
	MONTAGNE("Mo"),
	PRAIRIE("Pr"),
	RIVIERE("Ri"),
	FORET("Fo"),
	MARAIS("Ma");

	private final String place;

	/**
	 * This constructor affects the place accepted by the tile
	 * @param place
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
