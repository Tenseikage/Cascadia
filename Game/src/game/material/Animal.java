package game.material;

import java.util.Objects;
/**
 * @author Christophe TARATIBU
 */
public enum Animal implements Biome {
	BUSE("Bu"),
	WAPITI("Wa"),
	RENARD("Re"),
	SAUMON("Sa"),
	OURS("Ou");
	/**
	 * Specie of the animal
	 */
	private final String specie;

	/**
	 * This constructor affects the animal's specie
	 * @param specie
	 */
	Animal(String specie) {
		Objects.requireNonNull(specie, "Error: null specie");
		this.specie = specie;
	}

	
 /**
	* This method returns the animal specie linked with a string
	* @return specie
  */
	@Override
	public String get() {
		return specie;
	}


}
