package game.logic;
import game.display.Display;
import game.display.DisplayTools;
import game.material.Environment;
import game.material.PeerTileToken;
import game.material.Tile;
import game.material.Token;
import game.player.Player;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class represents the game logic
 * @author Christophe TARATIBU
 */
public class GameLogic {

	/**
	 * Returns the tile by its index
	 * @param map map
	 * @param index index
	 * @return tile
	 */
	public Tile getTileByIndex(Map<Tile,Token> map, int index) {
		Objects.requireNonNull(map);
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
	 * Return the player's choice
	 * @param scanner scanner
	 * @param board  choice board	
	 * @return player's choice
	 */
	public int getPlayerChoice(Scanner scanner, Choice board) {
		Objects.requireNonNull(scanner);
		Objects.requireNonNull(board);
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
	 * Returns the position of a tile
	 * @param env environment
	 * @param scanner scanner
	 * @return position of the new tile
	 */
	public Position getPosition(Environment env, Scanner scanner) {
		Objects.requireNonNull(env);
		Objects.requireNonNull(scanner);
		System.out.println("Choisissez la position d'une tuile existante où poser votre tuile (format: (x,y))");
		String answerPos = scanner.next();
		var pos = Position.fromString(answerPos);
		var positions = env.getPositions();
		var bool = env.validPos(positions, pos);
		if (!bool) {
			throw new IllegalAccessError("Position not in environnement");
		} else {
			return pos;
		}
	}

	/**
	 * Returns the position of a token.
	 * In case the player can't place the token, it returns null
	 * @param scanner scanner
	 * @return position of the new token
	 */
	public Position geTokenPosition(Scanner scanner) {
		Objects.requireNonNull(scanner);
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
	 * Returns updated environment
	 * @param scanner scanner
	 * @param chosenPos position of the new tile
	 * @param tilesTokens Peer of tile and token to place (Only tile)
	 * @param env environment
	 * @return environment
	 */
	public Environment getDirection(Scanner scanner, Position chosenPos,PeerTileToken tilesTokens, Environment env) {
		Objects.requireNonNull(scanner);
		Objects.requireNonNull(chosenPos);
		Objects.requireNonNull(tilesTokens);
		Objects.requireNonNull(env);
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
	 * Adds a token to the environment
	 * @param env environment
	 * @param chosenToken token to add
	 * @param chosenPos position of the token
	 */
	public void addTokenToEnv(Environment env, Token chosenToken, Position chosenPos) {
		Objects.requireNonNull(env);
		Objects.requireNonNull(chosenToken);
		Objects.requireNonNull(chosenPos);
		var peerTilePos = env.getKeyByPos(chosenPos);
		env.addTokenPlayer(peerTilePos, chosenToken, chosenPos,0);
	}

	/**
	 * Returns the final choice of the player
	 * @param choice player's choice
	 * @param choiceBoard choice board
	 * @return final choice
	 */
	public static PeerTileToken finalChoiceTileToken(int choice, List<PeerTileToken> choiceBoard) {
		Objects.requireNonNull(choiceBoard);
		var chosenTileToken = choiceBoard.get(choice - 1); // Tuile  et jeton choisie
		return chosenTileToken;
	}

	/**
	 * Places a token in the environment
	 * @param board choice board
	 * @param scanner scanner
	 * @param display display
	 * @param grid player's grid
	 * @param env player's environment
	 * @param chosenTile chosen tile
	 * @param chosenToken chosen token
	 * @param player player
	 * @param tokens List of tokens
	 * @return True if the token was placed, false otherwise
	 */
	public boolean puTokenToEnv(Choice board, Scanner scanner, Display display, DisplayTools grid, Environment env, Tile chosenTile, Token chosenToken, Player player, List<Token> tokens) {
		Objects.requireNonNull(board);
		Objects.requireNonNull(scanner);
		Objects.requireNonNull(display);
		Objects.requireNonNull(grid);
		Objects.requireNonNull(env);
		Objects.requireNonNull(chosenTile);
		Objects.requireNonNull(chosenToken);
		Objects.requireNonNull(player);
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
	 * Represents a game turn
	 * @param scanner scanner
	 * @param display display
	 * @param player Player's data
	 * @param board choice board
	 * @param grid player's grid
	 * @param tokens tokens list
	 */
	public void gameTurn(Scanner scanner, Display display, Player player, Choice board, DisplayTools grid, List<Token> tokens) {
		Objects.requireNonNull(scanner);
		Objects.requireNonNull(display);
		Objects.requireNonNull(player);
		Objects.requireNonNull(board);
		Objects.requireNonNull(grid);
		Objects.requireNonNull(tokens);
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