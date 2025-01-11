package game.graphic;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.forax.zen.Application;
import com.github.forax.zen.ApplicationContext;
import com.github.forax.zen.Event;
import com.github.forax.zen.KeyboardEvent;
import com.github.forax.zen.PointerEvent;

import game.logic.Scores;
import game.material.PeerTileToken;
import game.player.Player;

public class GraphicMain {
	/**
	 * This method lets the players set their name and age
	 * @param nbPlayers
	 * @return players 	 List containing the players*/
	private static List<Player> listPlayers(int nbPlayers){
		if(nbPlayers <= 0 || nbPlayers > 4){
			System.err.println("Nombre incorrect");
			return null;
		} 
		List<Player> players = new ArrayList<>();
		for(int i = 0; i < nbPlayers; i++){
			var player = WindowInfo.createPlayer();
			players.add(player);
		}
		return players;
	} 

	private static void displayScore(List<Player> players, int mode){
		var score  = new Scores();
		var player1 = players.get(0);
		var player2 = players.get(1);
		var env1 = players.get(0).boardPlayer();
		var env2 = players.get(1).boardPlayer();
		if (mode == 1) {
			var posAnimals1 = score.checkTokens(env1);
			var posAnimals2 = score.checkTokens(env2);
			int score1 = score.scoreMode(posAnimals1, mode);
			int score2 = score.scoreMode(posAnimals2, mode);
			Player.displayWinner(player1, score1, player2, score2,1);
			} else {
				var posAnimals1 = score.checkTokens(env1);
				var posAnimals2 = score.checkTokens(env2);
				int score1 = score.scoreMode(posAnimals1, mode);
				int score2 = score.scoreMode(posAnimals2, mode);
				Player.displayWinner(player1, score1, player2, score2,1);
		}
	}

	/**
   * Tries sleeping for a given duration.
   * 
   * @param duration Sleep duration, in milliseconds.
   * @return True if you managed to sleep that long, False otherwise.
   */
  private static boolean sleep(int duration) {
    try {
      Thread.sleep(duration);
    } catch (InterruptedException ex) {
      return false;
    }
    return true;
  }

	/**
	 * This method lets the player place his/her tile
	 * @param context
	 * @param player
	 * @param graphicGame
	 * @param peer
	 * @return
	 */
	private static boolean manageEventTile(ApplicationContext context,Player player,GraphicGame graphicGame, PeerTileToken peer){
		for(;;){
			Event event = context.pollOrWaitEvent(100);
			boolean setTile;
			if(event != null){
				switch(event){
					case PointerEvent ev -> {
						if(ev.action() ==  PointerEvent.Action.POINTER_UP ){
							var location = ev.location();
							//System.out.println("Position X: " + location.x() + " Position Y: " + location.y()); // Coord pointeur souris
							setTile = graphicGame.setTileAndDraw(context, player, graphicGame.columnFromX(location.x()), graphicGame.lineFromY(location.y()),peer);
						return setTile;
						}
					} case KeyboardEvent ev -> {
						// Gestion touches clavier 
						if(ev.key() ==  KeyboardEvent.Key.ESCAPE){
							//Fermeture écran
							context.dispose();
							System.exit(0);
						} 
					}
					default -> {}
				}
			}

		}
	}

	/**
	 * This method lets player place his/her token on a tile already put
	 * @param context
	 * @param player
	 * @param graphicGame
	 * @param peer
	 * @return
	 */
	private static boolean manageEventToken(ApplicationContext context,Player player,GraphicGame graphicGame, PeerTileToken peer){
		for(;;){
			Event event = context.pollOrWaitEvent(1000);
			boolean setToken;
			if (event != null){
				switch(event){
					case PointerEvent ev -> {
						if(ev.action() ==  PointerEvent.Action.POINTER_UP ){
							var location = ev.location();
							setToken = graphicGame.setTokenAndDraw(context,player, graphicGame.columnFromX(location.x()), graphicGame.lineFromY(location.y()),peer);
							//System.out.println(setTile);
						  return setToken;
						}
						
					} case KeyboardEvent ev -> {
						// Gestion touches clavier 
						if(ev.key() ==  KeyboardEvent.Key.ESCAPE){
							//Fermeture écran
							context.dispose();
							System.exit(0);
						} 
					}
					default -> {}
				}
			}
		}
	}
	
	/**
	 * This method  simulate the game turn between two players
	 * @param context
	 * @param dataGame
	 * @param player
	 * @param graphicGame
	 * @return
	 */
	private static boolean gameTurn(ApplicationContext context,DataGame dataGame ,Player player,GraphicGame graphicGame){
		WindowInfo.messageInfoError("Votre tour : Choisissez une tuile et un jeton", "Information");
		var peer = WindowInfo.choice(dataGame);
		boolean setTile = false;
		boolean setToken = false;
    while(!setTile){
			setTile = manageEventTile(context,player,graphicGame,peer);
		}
		while(!setToken){
			setToken = manageEventToken(context, player, graphicGame, peer);
		}
		graphicGame.updateScreen(context,player);
		return true;
	}
	private static void gameLoop(ApplicationContext context,DataGame dataGame, List<Player> players,GraphicGame graphicGame, int mode){
		for(int i = 0; i < 20; i++){
			if(i < 2){
				Fonts.drawPlayerName(context, players.get(i % 2), 0, 0, 20);
				GraphicGame.drawBegin(context, players.get(i % 2), graphicGame);
				gameTurn(context,dataGame,players.get(i % 2), graphicGame);
				graphicGame.updateScreen(context, players.get(i % 2));
				sleep(3000);
				graphicGame.cleanScreen(context);
			} else {
				  graphicGame.updateScreen(context, players.get(i % 2));
					gameTurn(context,dataGame,players.get(i % 2), graphicGame);
					sleep(3000);
			  	graphicGame.cleanScreen(context);
			}
		}
		displayScore(players, mode); //End of game
		context.dispose();
	}
	public static void beginGame(ApplicationContext context) throws IOException {
		try {
			var graphicGame = new GraphicGame(50 , 50, 150,new DataGame(),6,4);
			var dataGame = graphicGame.dataGame();
			var listPlayers = listPlayers(2);
			dataGame.createBoard();
			var mode  = WindowInfo.modScore();
			gameLoop(context,dataGame, listPlayers, graphicGame,mode);
				
		} catch (IOException e) {
			throw new IOException("Erreur lors de l'exploitation du fichier CSV", e);
		}
	}

	
	public static void main(String[] args) throws IOException {
		Application.run(Color.WHITE, context -> {
			try {
				beginGame(context);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}


