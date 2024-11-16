package game.material;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import game.logic.Position;

public class Score {
//	public static int calcul(Map<Map<Tile,Token> ,Position> environement) {
//		int count = 0;
//		Map<String, Integer> grp = new HashMap<>();
//		for (var entry: environement.entrySet()) {
//			Map<Tile,Token> duo = entry.getKey();
//			for (var elem: duo.entrySet()) {
//				Token token = elem.getValue();
//				if (token != null) {
//					String espece = token.espece();
//					grp.put(espece, grp.getOrDefault(espece, 0)+1);
//				}
//			}
//		}
//		
//		for (var elem: grp.entrySet()) {
//			count+= calculPoints(elem.getValue());
//		}
//		
//		return count;
//		
//	}
	
	public int calculModeFamille(Environment environement) {
		int scount = 0;
		Set<String> especeTraitee = new HashSet<String>();
		
		for (Position pos: environement.getPositions()) {
			for (var entry: environement.getEnvironment().entrySet()) {
				var tiles  = entry.getKey();
				for (Token token: tiles.values()) {
					if (token != null && !especeTraitee.contains(token.espece())) {
						int taille = tailleGroup(environement, pos, token.espece(), new HashSet<>());
						scount+= calculPoints(taille);
						especeTraitee.add(token.espece());
					}
				}
			}
		}
		return scount;
	}
	
	public int tailleGroup(Environment environement, Position p, String espece, Set<Position> connu) {
		if (connu.contains(p)) {
			return 0;
		}
		int count = 0;
		
		connu.add(p);
		for (Position pos: p.voisin()) {
			if (pos.isValid(25, 25) & sameEspece(environement,pos,espece)) {
				count+= tailleGroup(environement,pos,espece,connu); 
			}
		}
		return count;
	}
	
	public boolean sameEspece(Environment environement, Position pos, String espece) {
		for (var entry: environement.getEnvironment().entrySet()) {
			HashMap<Tile,Token> m = entry.getKey();
			for (Token token: m.values()) {
				if (token != null & token.espece().equals(espece)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static int calculPoints(int taille) {
		return switch (taille) {
			case 2 -> 3;
			case 0 -> 0;
			case 1 -> 0;
			case 3 -> 6;
			case 4 -> 10;
			default -> 15;
		};
	}
}
