package game.graphic;
import com.github.forax.zen.*;

import game.material.Environment;
import game.player.Player;

import java.awt.Color;
import java.io.IOException;
public class GraphicMain {


	private static void manageEventTile(ApplicationContext context,Event event,DataPlayer dataPlayer,GraphicGame graphicGame){
		//var screenInfo = context.getScreenInfo();
		//var width = screenInfo.width();
		//var height = screenInfo.height();
		switch(event){
      case null -> {
      context.dispose();
      return;
      }
			case PointerEvent ev -> {
				var location = ev.location();
				//System.out.println("Position X: " + location.x() + " Position Y: " + location.y()); // Coord pointeur souris
				context.renderFrame(graphics-> {
					graphicGame.drawTile(graphics, dataPlayer, graphicGame.columnFromX(location.x()), graphicGame.lineFromY(location.y()));
					//drawLittleSquare2(graphics, location.x(), location.y());
				});
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
	private static void gameTurn(ApplicationContext context,DataPlayer dataPlayer,GraphicGame graphicGame){
      //GraphicGame.drawAll(context, dataGame, dataPlayer, graphicGame);
			while (true) { 
				var event = context.pollOrWaitEvent(10);
				if(event == null){
					continue;
				}
				manageEventTile(context, event,dataPlayer,graphicGame);
			}
	}

	public static void beginGame(ApplicationContext context) throws IOException {
		try {
			var dataGame = new DataGame();
			var player = new Player("Toto",18,0,null);
			var dataPlayer = new DataPlayer(player,new Environment());
			var graphicGame = new GraphicGame(50 , 50, 150,dataGame,6,4);
			GraphicGame.drawAll(context, dataGame, dataPlayer, graphicGame);
			gameTurn(context, dataPlayer, graphicGame);
				
		} catch (IOException e) {
			throw new IOException("Erreur lors de l'exploitation du fichier CSV", e);
		}
		var dataGame = new DataGame();
		var player = new Player("Toto",18,0,null);
		var dataPlayer = new DataPlayer(player,new Environment());
		var graphicGame = new GraphicGame(50 , 50, 150,dataGame,6,4);
		GraphicGame.drawAll(context, dataGame, dataPlayer, graphicGame);
		gameTurn(context, dataPlayer, graphicGame);


	}
	public static void main(String[] args) throws IOException {
		Application.run(Color.WHITE, t -> {
			try {
				beginGame(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
