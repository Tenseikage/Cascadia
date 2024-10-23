package game.player;
import java.util.Objects;
// Organisation en packages
public record Joueur(String name, int age) {
	public Joueur {
		Objects.requireNonNull(name,"Nom de joueur inconnu !!!");
		if (age < 0){
			throw new IllegalArgumentException("Age Incorrect !!!");
		}
	}
	
	@Override
	public String toString() {
		return name + " " + age;
		
		  
	}
}
