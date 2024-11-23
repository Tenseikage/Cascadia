package game.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game.material.Environment;
import game.material.Tile;
import game.material.Token;

public class Score {
	public int calculModeFamille(Environment environement) {
		int scount = 0;
		Set<String> especeTraitee = new HashSet<>();
		
		for (Position pos: environement.getPositions()) {
			for (var entry: environement.getEnvironment().entrySet()) {
				var tiles  = entry.getKey();
				for (Token token: tiles.values()) {
					if (token != null && !especeTraitee.contains(token.espece())) {
						int taille = tailleGroup(environement, pos, token.espece(), new HashSet<>());
						scount+= calculPoints(taille);
						especeTraitee.add(token.espece());
					} else{
						scount += 0;
					}
				}
			}
		}
		return scount;
	}
	
	public int calculModeInter(Environment environement) {
		int nbGroupPointsOurs = nbGroupOursPoints(environement, "Ou");
		int nbGroupPointsAigle = tailleGroupAigle(nbAigle(environement, "Bu"));
		int nbGroupPointsWap = tailleGroupWap(maxLine(environement, "Wa"));
		int nbGroupPointsSau = 0;
		int nbGroupPointsRen = 0;
		
		return nbGroupPointsOurs + nbGroupPointsAigle + nbGroupPointsWap + nbGroupPointsSau + nbGroupPointsRen;
	}
	
	public static int tailleGroup(Environment environement, Position p, String espece, Set<Position> connu) {
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
	
	public static boolean sameEspece(Environment environement, Position pos, String espece) {
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
	
	public static int tailleGroupOurs(int taille) {
		return switch (taille) {
		case 0 -> 0;
		case 1 -> 4;
		case 2 -> 11;
		case 3 -> 19;
		default -> 27;
		};
	}
	
	public static int nbGroupOursPoints(Environment environement, String espece) {
		int countGroupOurs = 0;
		for (Position pos: environement.getPositions()) {
			for (var entry: environement.getEnvironment().entrySet()) {
				var tiles  = entry.getKey();
				for (Token token: tiles.values()) {
					if (token.espece().equals(espece)) {
						int nbOurs = tailleGroup(environement,pos,espece,new HashSet<>());
						if (nbOurs == 2) {
							countGroupOurs++;
						}
					}
				}
			}
		}
		return tailleGroupOurs(countGroupOurs);
	}
	
	public static int tailleGroupAigle(int taille) {
		return switch (taille) {
		case 0 -> 0;
		case 1 -> 2;
		case 2 -> 5;
		case 3 -> 8;
		case 4 -> 11;
		case 5 -> 14;
		case 6 -> 18;
		case 7 -> 22;
		default -> 28;
		};
	}
	
	public static int nbAigle(Environment env, String espece) {
		int count = 0;
		
		for (Position pos: env.getPositions()) {
			for (var entry: env.getEnvironment().entrySet()) {
				var tiles  = entry.getKey();
				for (Token token: tiles.values()) {
					if (token.espece().equals(espece) && tailleGroup(env,pos,espece,new HashSet<>()) == 1) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
//	public static int tailleGroupWap(int taille) {
//		return switch (taille) {
//		case 0 -> 0;
//		case 1 -> 2;
//		case 2 -> 5;
//		case 3 -> 9;
//		default -> 13;
//		};
//	}
//	
//	public static int lineWapDroit(Environment env, String espece) {
//		int count = 0;
//		
//		for (Position pos: env.getPositions()) {
//			for (var entry: env.getEnvironment().entrySet()) {
//				var tiles  = entry.getKey();
//				for (Token token: tiles.values()) {
//					if (token.espece().equals(espece)) {
//						if (pos.droit().isValid(25, 25) && env.checkEspece(Environment.getKeyByValue(tiles, token), token, pos, espece)) {
//							count = count + 1 + lineWapDroit(env, espece);
//						}
//					}
//				}
//			}
//		}	
//		return count;
//	}
//	
//	public static int lineWapGauche(Environment env, String espece) {
//		int count = 0;
//		
//		for (Position pos: env.getPositions()) {
//			for (var entry: env.getEnvironment().entrySet()) {
//				var tiles  = entry.getKey();
//				for (Token token: tiles.values()) {
//					if (token.espece().equals(espece)) {
//						if (pos.gauche().isValid(25, 25) && env.checkEspece(Environment.getKeyByValue(tiles, token), token, pos, espece)) {
//							count = count + 1 + lineWapGauche(env, espece);
//						}
//					}
//				}
//			}
//		}	
//		return count;
//	}
//	
//	public static int lineWapHaut(Environment env, String espece) {
//		int count = 0;
//		
//		for (Position pos: env.getPositions()) {
//			for (var entry: env.getEnvironment().entrySet()) {
//				var tiles  = entry.getKey();
//				for (Token token: tiles.values()) {
//					if (token.espece().equals(espece)) {
//						if (pos.haut().isValid(25, 25) && env.checkEspece(Environment.getKeyByValue(tiles, token), token, pos, espece)) {
//							count = count + 1 + lineWapHaut(env, espece);
//						}
//					}
//				}
//			}
//		}	
//		return count;
//	}
//	
//	public static int lineWapBas(Environment env, String espece) {
//		int count = 0;
//		
//		for (Position pos: env.getPositions()) {
//			for (var entry: env.getEnvironment().entrySet()) {
//				var tiles  = entry.getKey();
//				for (Token token: tiles.values()) {
//					if (token.espece().equals(espece)) {
//						if (pos.gauche().isValid(25, 25) && env.checkEspece(Environment.getKeyByValue(tiles, token), token, pos, espece)) {
//							count = count + 1 + lineWapBas(env, espece);
//						}
//					}
//				}
//			}
//		}	
//		return count;
//	}
//	
//	public static int maxLine(Environment env, String espece) {
//		int lineBas = lineWapBas(env, espece);
//		int lineDroit = lineWapDroit(env, espece);
//		int lineGauche = lineWapGauche(env, espece);
//		int lineHaut = lineWapHaut(env, espece);
//		List<Integer> list = new ArrayList<>();
//		int line = 0;
//		list.add(lineBas);
//		list.add(lineGauche);
//		list.add(lineDroit);
//		list.add(lineHaut);
//		for (var elem: list) {
//			if (elem > line) {
//				line = elem;
//			}
//		}
//		return line;
//	}
	
	
//	public static int tailleGroupSau(int taille) {
//		return switch (taille) {
//		case 0 -> 0;
//		case 1 -> 2;
//		case 2 -> 5;
//		case 3 -> 8;
//		case 4 -> 12;
//		case 5 -> 18;
//		case 6 -> 20;
//		default -> 25;
//		};
//	}
//	
//	
//	public static int nbGroupSauPoints (Environment env, String espece) {
//		int count = 0;
//		
//		for (Position pos: env.getPositions()) {
//			for (var entry: env.getEnvironment().entrySet()) {
//				var tiles  = entry.getKey();
//				for (Token token: tiles.values()) {
//					if (token.espece().equals(espece)) {
//						if (pos.droit().isValid(25, 25) && env.checkEspece(Environment.getKeyByValue(tiles, token), token, pos, espece)) {
//							
//						}
//					}
//				}
//			}
//		}	
//		return count;
//	}
}

	
