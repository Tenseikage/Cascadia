package game.material;
import java.util.Objects;
public class Animal {
	private final String espece;
	public Animal(String espece){
		this.espece = espece;
		Objects.requireNonNull(espece,"Erreur espèce obligatoire");
		
	}
	@Override
	// Affichage de l'animal
	public String toString(){
		return espece;
	}

}
