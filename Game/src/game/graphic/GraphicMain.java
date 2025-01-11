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

import game.material.PeerTileToken;
import game.player.Player;

public class GraphicMain {
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
	private static void gameLoop(ApplicationContext context,DataGame dataGame, List<Player> players,GraphicGame graphicGame){
		//boolean turnFinished = false;
		for(int i = 0; i < 4; i++){
			if(i < 2){
				GraphicGame.drawBegin(context, players.get(i % 2), graphicGame);
				gameTurn(context,dataGame,players.get(i % 2), graphicGame);
				graphicGame.updateScreen(context, players.get(i % 2));
				graphicGame.cleanScreen(context);
			} else {
				  graphicGame.updateScreen(context, players.get(i % 2));
					gameTurn(context,dataGame,players.get(i % 2), graphicGame);
			  	graphicGame.cleanScreen(context);
			}
			//gameTurn(context,dataGame,players.get(i % 2), graphicGame);
			//graphicGame.updateScreen(context, players.get(i % 2));
			//graphicGame.cleanScreen(context);
		}
	}

	public static void beginGame(ApplicationContext context) throws IOException {
		try {
			var dataGame = new DataGame();
			//var player = new Player("Toto",18,0,new Environment());
			dataGame.createBoard();
			var listPlayers = listPlayers(2);
			var graphicGame = new GraphicGame(50 , 50, 150,dataGame,6,4);
			gameLoop(context,dataGame, listPlayers, graphicGame);
				
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
