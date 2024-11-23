package game.player;
import game.material.Environment;
import java.util.Objects;
import java.util.Scanner;

public record Player(String name, int age,int points,Environment boardPlayer) {
	public Player {
		Objects.requireNonNull(name,"Nom de joueur inconnu !!!");
		Objects.requireNonNull(boardPlayer,"Erreur : Environnement inexistant");
		if (age < 0){
			throw new IllegalArgumentException("Age Incorrect !!!");
		}
		points = 0;
	}

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
	
	
	
}