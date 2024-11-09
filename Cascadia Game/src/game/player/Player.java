package game.player;
import game.material.Environment;
import java.util.Objects;

public record Player(String name, int age,int points,Environment boardPlayer) {
	public Player {
		Objects.requireNonNull(name,"Nom de joueur inconnu !!!");
		Objects.requireNonNull(boardPlayer,"Erreur : Environnement inexistant");
		if (age < 0){
			throw new IllegalArgumentException("Age Incorrect !!!");
		}
		points = 0;
	}
	
	
	
}