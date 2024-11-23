package game.player;
import game.material.Environment;
import java.util.Objects;
import java.util.Scanner;


/**
 * This class represents the player of the game
 */
public record Player(String name, int age,int points,Environment boardPlayer) {
	public Player {
		Objects.requireNonNull(name,"Unknow player name");
		Objects.requireNonNull(boardPlayer,"Unknow player board");
		if (age < 0){
			throw new IllegalArgumentException("Error : Negative age");
		}
		points = 0;
	}

	/**
	 * This method returns the name of the player
	 * @param scanner the scanner to read the input
	 * @return the name of the player
	 */
	public static boolean choiceKeepOrPass(Scanner scanner){
		System.out.println("Souhaitez vous garder les jetons choisis ?");
		var answer = scanner.next();
		boolean output;
		switch (answer) {
			case "Oui" -> output = false;
			case "Non" -> output = true;
			default -> output = true;
		}
		if (!output){
			System.out.println("Jetons inchangés");
		} else {
			System.out.println("Jetons remis dans le sac");
		}
		return output;
		
	}

	/**
	 * This method displays the winner of the game
	 * @param player1 first player
	 * @param score1 score of the first player
	 * @param player2 second player
	 * @param score2 	score of the second player
	 */
	public static void displayWinner(Player player1,int score1,Player player2,int score2){
		Objects.requireNonNull(player1, "Error : Null player");
		Objects.requireNonNull(player2, "Error : Null player");
		if(score1 > score2){
			System.out.println(player1.name() + " a gagné");
		} else if(score1 < score2){
			System.out.println(player2.name() + " a gagné");
		} else {
			System.out.println("Egalité");
		}

	}
	
	
	
}