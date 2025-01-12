package game.logic;
import game.material.Environment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Class to calculate the scores
 * @author Christophe TARATIBU / Loïc BERGEOT
 */
public class Scores {

	/**
	 * Checks the tokens on the grid
	 * @param env the environment
	 * @return Animal and their positions
	 */
	public Map<String, List<Position>> checkTokens(Environment env) {
		Objects.requireNonNull(env);
		Map<String, List<Position>> posAnimals = new HashMap<>();
		var envPlayer = env.getEnvironment();

		for (var entry : envPlayer.entrySet()) {
		  var entryTileToken = entry.getKey();
		  var position = entry.getValue();
			var token = entryTileToken.getToken();
			if (token != null) {
				String animal = token.espece();
				posAnimals.computeIfAbsent(animal, _ -> new ArrayList<>()).add(position);
			}
		}
		return posAnimals;
  }

	/**
	 * Search the groups of animals
	 * @param posAnimals Animal and positions on the environment
	 * @param visitedPos Visited positions
	 * @param currentAnimal Current animal in the environment
	 * @param currentPos Current position
	 * @param groupAnimals List of the animal's positions
	 */
	private void searchGroupHelper(Map<String, List<Position>> posAnimals, Set<Position> visitedPos,
    String currentAnimal, Position currentPos, List<Position> groupAnimals) {
    visitedPos.add(currentPos);
    groupAnimals.add(currentPos);
    var posNeighbors = currentPos.voisinAdjacent();
      for (var pos : posNeighbors) {
        if (!visitedPos.contains(pos) && posAnimals.getOrDefault(currentAnimal, Collections.emptyList()).contains(pos)) {
          searchGroupHelper(posAnimals, visitedPos, currentAnimal, pos, groupAnimals);
        }
      }
    }

	/**
	 * Method to search the group of animals by deep search
	 * @param posAnimals the animals with their positions
	 * @return Map containing the Animal and list of differents group sizes
	 */
	public Map<String, List<Integer>> SearchGroup(Map<String, List<Position>> posAnimals) {
		Objects.requireNonNull(posAnimals);
		Set<Position> visitedPos = new HashSet<>();
		Map<String, List<Integer>> animalGroupSizes = new HashMap<>();
		for (var entry : posAnimals.entrySet()) {
			String animal = entry.getKey();
			for (Position position : entry.getValue()) {
				if (!visitedPos.contains(position)) {
					List<Position> groupAnimals = new ArrayList<>();
					searchGroupHelper(posAnimals, visitedPos, animal, position, groupAnimals);
					int groupSize = groupAnimals.size();
					animalGroupSizes.computeIfAbsent(animal, _ -> new ArrayList<>()).add(groupSize);
				}
			}
		}
		return animalGroupSizes;
  }

	/**
	 * Computes the score with the family variant
	 * @param animalGroupSizes the group sizes
	 * @return the score
	 */
	public int familyVariant(Map<String,List<Integer>> animalGroupSizes){
		Objects.requireNonNull(animalGroupSizes);
		int total = 0;
		for (var entry : animalGroupSizes.entrySet()){
			var groups = entry.getValue();
			for(var number : groups){
				switch (number){
					case 1 -> total += 2;
					case 2 -> total += 5;
					default -> total += 9;
				}
			}
		}
		return total;
	}

	/**
	 * Computes the score with the intermediate variant
	 * @param animalGroupSizes the group sizes
	 * @return the score
	 */
	public int IntermediateVariant(Map<String,List<Integer>> animalGroupSizes){
		Objects.requireNonNull(animalGroupSizes);
		int total = 0;
		for (var entry : animalGroupSizes.entrySet()){
			var groups = entry.getValue();
			for(var number : groups){
				if (number > 1){
					switch (number){
						case 2 -> total += 5;
						case 3 -> total += 8;
						default -> total += 12;
					}
				}
			}
		}
		return total;
	}

	/**
	 * Computes the score
	 * @param animal the animals with their positions
	 * @param mode the mode (family or intermediate)
	 * @return the score
	 */
	public int scoreMode(Map<String,List<Position>> animal, int mode){
		Objects.requireNonNull(animal);
		var animalGroupSizes = SearchGroup(animal);
    if(mode == 1){
			return familyVariant(animalGroupSizes);
		}
		return IntermediateVariant(animalGroupSizes);
	}
}
	

