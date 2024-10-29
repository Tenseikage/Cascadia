package game.player;

import java.util.Objects;

import game.material.Board;

public record Player(String name, int age, int numberOfTiles, int points, Board board) {
	public Player {
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