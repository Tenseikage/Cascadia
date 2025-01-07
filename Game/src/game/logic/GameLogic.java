package game.logic;
import game.display.Display;
import game.display.DisplayTools;
import game.material.Environment;
import game.material.PeerTileToken;
import game.material.Tile;
import game.material.Token;
import game.player.Player;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class represents the game logic
 */
public class GameLogic {

	/**
	 * This method returns the tile by its index
	 * @param map map
	 * @param index index
	 * @return tile
	 */
	public Tile getTileByIndex(Map<Tile,Token> map, int index) {
		Objects.requireNonNull(map, "Error : Null map");
		if (index < 0 || index >= map.size()) {
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		int position = 0;
		for (var entry : map.entrySet()) {
			if (position == index) {
				return entry.getKey();
			}
			position++;
		}
		throw new IllegalAccessError("Index not found");
	}

	
	/**
	 * This method return the player's choice
	 * @param scanner scanner
	 * @param board  choice board	
	 * @return player's choice
	 */
	public int getPlayerChoice(Scanner scanner, Choice board) {
		Objects.requireNonNull(scanner, "Error : Null scanner");
		Objects.requireNonNull(board, "Error : Null board");
		if (board.getChoiceBoard().size() == 4) {
			System.out.println("  ( 01 )      ( 02 )      ( 03 )      ( 04 )\n");
			System.out.println("QUELLE PAIRE CHOISISSEZ VOUS (1 - 2 - 3 - 4) ? : ");
		} else if (board.getChoiceBoard().size() == 3) {
			System.out.println("  ( 01 )      ( 02 )      ( 03 )\n");
			System.out.println("QUELLE PAIRE CHOISISSEZ VOUS (1 - 2 - 3) ? : ");
		}
		int choice = scanner.nextInt();
		return choice;
	}

	/**
	 * This method returns the position of a tile
	 * @param env environment
	 * @param scanner scanner
	 * @return position of the new tile
	 */
	public Position getPosition(Environment env, Scanner scanner) {
		Objects.requireNonNull(env, "Error : Null environment");
		Objects.requireNonNull(scanner, "Error : Null scanner");
		System.out.println("Choisissez la position d'une tuile existante où poser votre tuile (format: (x,y))");
		String answerPos = scanner.next();
		var pos = Position.fromString(answerPos);
		var positions = env.getPositions();
		var bool = env.validPos(positions, pos);
		System.out.println("Booléen : " + bool);
		if (!bool) {
			throw new IllegalAccessError("Position not in environnement");
		} else {
			return pos;
		}
	}

	/**
	 * This method returns the position of a token.
	 * In case the player can't place the token, it returns null
	 * @param scanner scanner
	 * @return position of the new token
	 */
	public Position geTokenPosition(Scanner scanner) {
		Objects.requireNonNull(scanner, "Error : Null scanner");
		System.out.println("Choisissez la position où poser votre jeton, Tapez Non si impossibilité");
		var answerPos = scanner.next();
		if (answerPos.equals("Non")) {
			System.out.println("Jeton remis dans le sac");
			return null;
		}
		var chosenPos = Position.fromString(answerPos);
		return chosenPos;
	}

	/**
	 * This method returns updated environment
	 * @param scanner scanner
	 * @param chosenPos position of the new tile
	 * @param chosenTile tile to place
	 * @param tilesTokens tiles and tokens
	 * @param env environment
	 * @return environment
	 */
	public Environment getDirection(Scanner scanner, Position chosenPos,PeerTileToken tilesTokens, Environment env) {
		Objects.requireNonNull(scanner, "Error : Null scanner");
		Objects.requireNonNull(chosenPos, "Error : Null position");
		//Objects.requireNonNull(chosenTile, "Error : Null tile");
		Objects.requireNonNull(tilesTokens, "Error : Null tilesTokens");
		System.out.println("Choisissez l'endroit où poser la tuile");
		System.out.println("Haut/Bas/Gauche/Droite");
		String answer = scanner.next();
		chosenPos = chosenPos.updatePosition(answer);
		var bool = env.noTileInPos(chosenPos);
		if (!chosenPos.isValid(Position.setMaxPos().getX(), Position.setMaxPos().getY()) || bool) {
			throw new IllegalArgumentException("Erreur tuile déja présente !!!");
		} else {
			env.addTilePlayer(tilesTokens.getTile(), chosenPos);
		}
		return env;
	}

	
	/**
	 * This method adds a token to the environment
	 * @param env environment
	 * @param chosenToken token to add
	 * @param chosenPos position of the token
	 */
	public void addTokenToEnv(Environment env, Token chosenToken, Position chosenPos) {
		Objects.requireNonNull(env, "Environment cannot be null");
		Objects.requireNonNull(chosenToken, "Chosen token cannot be null");
		Objects.requireNonNull(chosenPos, "Chosen position cannot be null");
		var peerTilePos = env.getKeyByPos(chosenPos);
		env.addTokenPlayer(peerTilePos, chosenToken, chosenPos,0);
	}

	/**
	 * This method returns the final choice of the player
	 * @param choice player's choice
	 * @param choiceBoard choice board
	 * @return final choice
	 */
	public static PeerTileToken finalChoiceTileToken(int choice, ArrayList<PeerTileToken> choiceBoard) {
		Objects.requireNonNull(choiceBoard, "Choice board cannot be null");
		var chosenTileToken = choiceBoard.get(choice - 1); // Tuile  et jeton choisie
		return chosenTileToken;
	}

	/**
	 * This method places a token in the environment
	 * @param board choice board
	 * @param scanner scanner
	 * @param display display
	 * @param grid player's grid
	 * @param env player's environment
	 * @param chosenTile chosen tile
	 * @param chosenToken chosen token
	 * @param player player
	 * @param tokens tokens
	 * @return true if the token was placed, false otherwise
	 */
	public boolean puTokenToEnv(Choice board, Scanner scanner, Display display, DisplayTools grid, Environment env, Tile chosenTile, Token chosenToken, Player player, ArrayList<Token> tokens) {
		Objects.requireNonNull(board, "Error : Null board");
		Objects.requireNonNull(scanner, "Error : Null scanner");
		Objects.requireNonNull(display, "Error : Null display");
		Objects.requireNonNull(grid, "Error : Null grid");
		Objects.requireNonNull(env, "Error : Null env");
		Objects.requireNonNull(chosenTile, "Error : Null chosen tile");
		Objects.requireNonNull(chosenToken, "Error : Null chosen token");
		Objects.requireNonNull(player, "Error : Null player");
		System.out.println("Environnement " + player.name());
		var chosenPos = geTokenPosition(scanner); 
		if (chosenPos == null) {
			Token.returnToken(chosenToken, tokens);
			return false;
		}
		var peerPos = env.getKeyByPos(chosenPos);
		var bool = env.addTokenPlayer(peerPos, chosenToken, chosenPos,0);
		return bool;
	}

	/**
	 * This method represents a game turn
	 * @param scanner scanner
	 * @param display display
	 * @param player player
	 * @param board choice board
	 * @param env player's environment
	 * @param grid player's grid
	 * @param tokens tokens list
	 */
	public void gameTurn(Scanner scanner, Display display, Player player, Choice board, DisplayTools grid, ArrayList<Token> tokens) {
		Objects.requireNonNull(scanner, "Error : Null scanner");
		Objects.requireNonNull(display, "Error : Null display");
		Objects.requireNonNull(player, "Error : Null player");
		Objects.requireNonNull(board, "Error : Null board");
		//Objects.requireNonNull(env, "Error : Null env");
		Objects.requireNonNull(grid, "Error : Null grid");
		Objects.requireNonNull(tokens, "Error : Null tokens");
		System.out.println("Environnement " + player.name()); 
		var env = player.boardPlayer();
		display.displayEnvPlayer(grid, player);
		display.displayAll(board);
		int choice = getPlayerChoice(scanner, board);
		var choiceBoard = board.getChoiceBoard();
		var peerPlayer = finalChoiceTileToken(choice, choiceBoard);
		var posPlayer = getPosition(env, scanner);
		env = getDirection(scanner, posPlayer, peerPlayer , env);
		display.displayEnvPlayer(grid, player);
		var bool = puTokenToEnv(board, scanner, display, grid, env, peerPlayer.getTile(), peerPlayer.getToken(), player, tokens);
		if (bool) {
			display.displayEnvPlayer(grid, player);
		}
		display.displayEnvPlayer(grid, player);
		choiceBoard.remove(choiceBoard.get(choice - 1)); // real remove
	}
}