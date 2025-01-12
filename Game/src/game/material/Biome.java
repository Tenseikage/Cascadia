package game.material;

/**
 * Interface for places and Animals of the game
 * @author Christophe TARATIBU
 */
public sealed interface Biome permits Places, Animal{
  String get();
	static boolean contains(Class<? extends Biome> enumString, String data){
		for(var elem: enumString.getEnumConstants()){
			if(elem.get().equals(data)){
				return true;
			}
		}
		return false;
	}
}
