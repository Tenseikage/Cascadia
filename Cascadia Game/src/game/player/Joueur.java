package game.player;
import java.util.Objects;
// Organisation en packages
public record Joueur(String nom, int age) {
	public Joueur {
			Objects.requireNonNull(nom,"Nom de joueur inconnu !!!");
	}
}
