package game.logic;
import game.material.Environment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Scores {
	public HashMap<String,Position> checkTokens(Environment env){
		HashMap<String,Position> posAnimals = new HashMap<>();
		var envPlayer = env.getEnvironment();
		for (var entry: envPlayer.entrySet()){
			var entryTileToken = entry.getKey();
			var position = entry.getValue();
			var token = new ArrayList<>(entryTileToken.values()).get(0);
			if(token != null){
				posAnimals.put(token.espece(), position);
			}
		}
		return posAnimals;	
	}

	public void SearchGroup(HashMap<String,Position> posAnimals,Set<Position> visited, 
	String currentAnimal,Position currentPos, ArrayList<Position> groupAnimals){
		visited.add(currentPos);
		groupAnimals.add(currentPos);
		var posNeighbors = currentPos.voisinAdjacent();
		for(var pos : posNeighbors){
			if(!visited.contains(pos) && posAnimals.containsValue(pos)){
				for(var entry : posAnimals.entrySet()){
					if(entry.getValue().equals(pos) && entry.getKey().equals(currentAnimal)){
						SearchGroup(posAnimals, visited, currentAnimal, pos, groupAnimals);
						break;
					}
				}
			}
		}
	}

	public Map<String,List<Integer>> SearchGroupSizes(HashMap<String,Position> posAnimals){
		Set<Position> visitedPos = new HashSet<>();
		HashMap<String,List<Integer>> animalGroupSizes = new HashMap<>();
		for(var entry : posAnimals.entrySet()){
			String animal = entry.getKey();
			Position position = entry.getValue();
			if(!visitedPos.contains(position)){
				ArrayList<Position> animalPositions = new ArrayList<>();
				SearchGroup(posAnimals, visitedPos, animal, position, animalPositions);
				int groupSize = animalPositions.size();
				animalGroupSizes.computeIfAbsent(animal, elem ->new ArrayList<>()).add(groupSize);
			}
		}
		return animalGroupSizes;
		

	}

	public int familyVariant(Map<String,List<Integer>> animalGroupSizes){
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

	public int IntermediateVariant(Map<String,List<Integer>> animalGroupSizes){
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

	public int scoreMode(HashMap<String,Position> animal, int mode){
		var animalGroupSizes = SearchGroupSizes(animal);
    if(mode == 1){
			return familyVariant(animalGroupSizes);
		}
		return IntermediateVariant(animalGroupSizes);
	}


}
	

