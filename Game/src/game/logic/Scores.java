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
 */
public class Scores {

	/**
	 * Method to check the tokens on the grid
	 * @param env the environment
	 * @return the tokens on the grid with their positions
	 */
	public HashMap<String, List<Position>> checkTokens(Environment env) {
		Objects.requireNonNull(env, "Error : Null environment");
		HashMap<String, List<Position>> posAnimals = new HashMap<>();
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
		System.out.println(posAnimals);
		return posAnimals;
  }

	private void searchGroupHelper(HashMap<String, List<Position>> posAnimals, Set<Position> visited,
    String currentAnimal, Position currentPos, List<Position> groupAnimals) {
    visited.add(currentPos);
    groupAnimals.add(currentPos);
    var posNeighbors = currentPos.voisinAdjacent();
      for (var pos : posNeighbors) {
        if (!visited.contains(pos) && posAnimals.getOrDefault(currentAnimal, Collections.emptyList()).contains(pos)) {
          searchGroupHelper(posAnimals, visited, currentAnimal, pos, groupAnimals);
        }
      }
    }

	/**
	 * Method to search the group of animals by deep search
	 * @param posAnimals the animals with their positions
	 * @param visited the visited positions
	 * @param currentAnimal the current animal
	 * @param currentPos the current position
	 * @param groupAnimals the group of animals
	 */
	public Map<String, List<Integer>> SearchGroup(HashMap<String, List<Position>> posAnimals) {
		Objects.requireNonNull(posAnimals, "Error : Null posAnimals");
		Set<Position> visited = new HashSet<>();
		Map<String, List<Integer>> animalGroupSizes = new HashMap<>();

		for (var entry : posAnimals.entrySet()) {
			String animal = entry.getKey();
			for (Position position : entry.getValue()) {
				if (!visited.contains(position)) {
					List<Position> groupAnimals = new ArrayList<>();
					searchGroupHelper(posAnimals, visited, animal, position, groupAnimals);
					int groupSize = groupAnimals.size();
					animalGroupSizes.computeIfAbsent(animal, _ -> new ArrayList<>()).add(groupSize);
				}
			}
		}
		return animalGroupSizes;
  }

	/**
	 * Method to calculate the score with the family variant
	 * @param animalGroupSizes the group sizes
	 * @return the score
	 */
	public int familyVariant(Map<String,List<Integer>> animalGroupSizes){
		Objects.requireNonNull(animalGroupSizes, "Error : Inexistent group sizes");
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
	 * Method to calculate the score with the intermediate variant
	 * @param animalGroupSizes the group sizes
	 * @return the score
	 */
	public int IntermediateVariant(Map<String,List<Integer>> animalGroupSizes){
		Objects.requireNonNull(animalGroupSizes, "Error : Inexistent group sizes");
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
	 * Method to calculate the score
	 * @param animal the animals with their positions
	 * @param mode the mode (family or intermediate)
	 * @return the score
	 */
	public int scoreMode(HashMap<String,List<Position>> animal, int mode){
		Objects.requireNonNull(animal, "Error : Null map of animals");
		var animalGroupSizes = SearchGroup(animal);
    if(mode == 1){
			return familyVariant(animalGroupSizes);
		}
		return IntermediateVariant(animalGroupSizes);
	}

	


}
	

