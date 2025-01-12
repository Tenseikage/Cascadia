package game.material;

import java.util.Objects;
/**
 * This class enums the animals of the game
 * @author Christophe TARATIBU
 */
public enum Animal implements Biome {
	/**
	 * Animal buse
	 */
	BUSE("Bu"),
	/**
	 * Animal wapiti
	 */
	WAPITI("Wa"),
	/**
	 * Animal renard
	 */
	RENARD("Re"),
	/**
	 * Animal saumon
	 */
	SAUMON("Sa"),
	/**
	 * Animal ours
	 */
	OURS("Ou");
	/**
	 * Specie of the animal
	 */
	private final String specie;

	/**
	 * Affects the animal's specie
	 * @param specie Name of species
	 */
	Animal(String specie) {
		Objects.requireNonNull(specie, "Error: null specie");
		this.specie = specie;
	}

	
 /**
	* Returns the animal specie linked with a string
	* @return specie
  */
	@Override
	public String get() {
		return specie;
	}


}
