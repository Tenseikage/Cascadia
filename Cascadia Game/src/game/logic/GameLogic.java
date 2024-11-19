package game.logic;
import game.display.Display;
import game.display.DisplayTools;
import game.material.Environment;
import game.material.Tile;
import game.material.Token;
import game.player.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class GameLogic {
	// Mise place des méthodes du Main du jeu
	public <Key,Value> Key getKeyByIndex(LinkedHashMap<Key, Value> map, int index){
		if(index < 0 || index >= map.size()){
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		int position = 0;
		for (var entry : map.entrySet()) {
			if (position == index){
				return entry.getKey();
			}
			position++;
		}
		throw new IllegalAccessError("Index introuvable");
	}

	public <Key,Value> Key getKeyByIndex(HashMap<Key, Value> map, int index){
		if(index < 0 || index >= map.size()){
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		int position = 0;
		for (var entry : map.entrySet()) {
			if (position == index){
				return entry.getKey();
			}
			position++;
		}
		throw new IllegalAccessError("Index introuvable");
	}
	
	//Choix du joueur
	public int getPlayerChoice(Scanner scanner, Choice board){
		if(board.getChoiceBoard().size() == 4){
			System.out.println("  ( 01 )      ( 02 )      ( 03 )      ( 04 )\n");
            System.out.println("WHICH PAIR YOU CHOOSE (1 - 2 - 3 - 4) ? : ");
		}else if (board.getChoiceBoard().size() == 3) {
			System.out.println("  ( 01 )      ( 02 )      ( 03 )\n");
			System.out.println("WHICH PAIR YOU CHOOSE (1 - 2 - 3) ? : ");
		}
        int choice = scanner.nextInt();
		System.out.println("Vous avez choisi : \n"+choice);
		return choice;
	}
	

	//Choix de la position du joueur
	public Position getPosition(Scanner scanner){
		System.out.println("Choisissez la position où poser votre tuile (format: (x,y))");
		String answerPos = scanner.next();
		return Position.fromString(answerPos);
	}

	public Position geTokenPosition(Scanner scanner){
		System.out.println("Choisissez la position où poser votre jeton, Tapez non si impossibilité");
		var answerPos = scanner.next();
		if(answerPos.equals("Non")){
			System.out.println("Jeton remis dans le sac");
			return null;
		}
		var chosenPos = Position.fromString(answerPos);
		return chosenPos;
	}

	//Choix de la direction adjacente de la tuile
	public Environment getDirection(Scanner scanner,Position chosenPos,Tile chosenTile,HashMap<Tile,Token> tilesTokens,Environment env){
		System.out.println("Choisissez l'endroit où poser la tuile");
		System.out.println("Haut/Bas/Gauche/Droite");
		String answer = scanner.next();
		chosenPos = chosenPos.updatePosition(answer);
		if(!chosenPos.isValid(Position.setMaxPos().getX(), Position.setMaxPos().getY())){
			System.err.println("Erreur : position impossible ! ");
		} else {
			tilesTokens.put(chosenTile,null);
			env.addTilePlayer(tilesTokens, chosenPos);
		}
		return env;
	}
	// Ajout de la tuile à l'environnement
	public void addTileToEnv(Environment env, Tile chosenTile, Position chosenPos){
		HashMap<Tile, Token> tilesTokens1 = new HashMap<>();
    tilesTokens1.put(chosenTile, null);
    env.addTilePlayer(tilesTokens1, chosenPos);
	}

	public void addTokenToEnv(Environment env, Token chosenToken, Position chosenPos) {
		var mapTilePos = Environment.getKeyByValue(env.getEnvironment(), chosenPos);
		var tilePos = Environment.getKeyByValue(mapTilePos, null);
		env.addTokenPlayer(tilePos, chosenToken, chosenPos);
  }
	public HashMap<Tile,Token> finalChoiceTile(int choice,HashMap<Tile,Token> choiceBoard){
		System.out.println("Choice :" + choice);
		HashMap<Tile,Token> choicePlayer = new HashMap<>();
		var chosenTile = getKeyByIndex(choiceBoard, 0); // Tuile choisie
	  choicePlayer.put(chosenTile, null);
		return choicePlayer;
	}
	public Token geTokenPlayer(Choice board,Tile chosenTile){
		return board.getChoiceBoard().get(chosenTile);

	}

	public boolean puTokenToEnv(Choice board, Scanner scanner,Display display, DisplayTools grid,Environment env,
		Tile chosenTile, Token chosenToken,Player player, ArrayList<Token> tokens){
		//Mets le jeton sur une tuile
		System.out.println("Environnement " + player.name());
		var chosenPos = geTokenPosition(scanner); // Position bonne
		if (chosenPos == null){
			Token.returnToken(chosenToken, tokens);
			return false;
		}
		var maptilePos = Environment.getKeyByValue(env.getEnvironment(), chosenPos);
		var tilePos = Environment.getKeyByValue(maptilePos, null);
		var bool = env.addTokenPlayer(tilePos, chosenToken, chosenPos); // Ajout du jeton sur une tuile
		System.out.println(bool);
		return bool;
	}

	//Tour de jeu
  public void gameTurn(Scanner scanner,Display display,Player player, Choice board, 
	Environment env, DisplayTools grid, ArrayList<Token> tokens){
		//int mode = display.displayChoiceMod(scanner);
		System.out.println(player.name()); // Nom joueur
		display.displayAll(board); //Affichage jetons/tuiles
		int choice = getPlayerChoice(scanner,board);
		var choiceBoard = board.getChoiceBoard();
		var maPlayer = finalChoiceTile(choice - 1, choiceBoard);
		var chosenToken = geTokenPlayer(board, getKeyByIndex(choiceBoard, choice - 1));
		var posPlayer = getPosition(scanner);
		env = getDirection(scanner, posPlayer, getKeyByIndex(choiceBoard, choice - 1), maPlayer, env);
		display.displayEnvPlayer(env,grid);
		var bool = puTokenToEnv(board, scanner, display, grid, env, getKeyByIndex(choiceBoard, choice - 1)
		, chosenToken, player,tokens);
		if (bool) {
			display.displayEnvPlayer(env,grid); // Affichage environnement
		}
		display.displayEnvPlayer(env,grid);
		choiceBoard.remove(getKeyByIndex(choiceBoard, choice - 1));








		




		}
}

