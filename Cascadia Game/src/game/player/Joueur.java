package game.player;

import java.util.Objects;

public record Joueur(String name, int age, int numberOfTiles, int points) {
	public Joueur {
		Objects.requireNonNull(name,"Nom de joueur inconnu !!!");
		if (age < 0){
			throw new IllegalArgumentException("Age Incorrect !!!");
		}
	}
	
	@Override
	public String toString() {
		return "Name : "+name+ " / " + "Age : " +age + " / " + "Number of tiles : " +numberOfTiles+ " / " + "Points : " + points;
		
		  
	}
}
