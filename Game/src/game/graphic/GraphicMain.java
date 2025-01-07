package game.graphic;
import com.github.forax.zen.*;
import game.material.Environment;
import game.material.PeerTileToken;
import game.player.Player;
import java.awt.Color;
import java.io.IOException;
public class GraphicMain {


	private static void manageEventTile(ApplicationContext context,DataPlayer dataPlayer,GraphicGame graphicGame, PeerTileToken peer){
		for(;;){
			Event event = context.pollOrWaitEvent(100);
			if(event != null){
				switch(event){
					case PointerEvent ev -> {
						if(ev.action() ==  PointerEvent.Action.POINTER_UP ){
							var location = ev.location();
							//System.out.println("Position X: " + location.x() + " Position Y: " + location.y()); // Coord pointeur souris
							context.renderFrame(graphics-> {
							graphicGame.setTileAndDraw(graphics, dataPlayer, graphicGame.columnFromX(location.x()), graphicGame.lineFromY(location.y()),peer);
							
						});
						return;
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

	private static void manageEventToken(ApplicationContext context,DataPlayer dataPlayer,GraphicGame graphicGame, PeerTileToken peer){
		for(;;){
			Event event = context.pollOrWaitEvent(1000);
			if (event != null){
				switch(event){
					case PointerEvent ev -> {
						if(ev.action() ==  PointerEvent.Action.POINTER_UP ){
							var location = ev.location();
							context.renderFrame(graphics-> {
							graphicGame.setTokenAndDraw(graphics,dataPlayer, graphicGame.columnFromX(location.x()), graphicGame.lineFromY(location.y()),peer);
						});
						return;
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
	
	private static boolean gameTurn(ApplicationContext context,DataGame dataGame ,DataPlayer dataPlayer,GraphicGame graphicGame){
		WindowInfo.messageInfo("Votre tour : Choisissez une tuile et un jeton", "Information");
		var peer = WindowInfo.choice(dataGame);
		manageEventTile(context,dataPlayer,graphicGame,peer);
		manageEventToken(context, dataPlayer, graphicGame, peer);
		graphicGame.updateScreen(context, dataPlayer);
		return true;
		//Manage token
		//Update
		/*while (true) { 
		  var event = context.pollOrWaitEvent(1000);
			if(event == null){
				continue;
			}
			var peer = WindowInfo.choice(dataGame);
			/
		}*/
	}
	private static void gameLoop(ApplicationContext context,DataGame dataGame, DataPlayer dataPlayer,GraphicGame graphicGame){
		//boolean turnFinished = false;
		gameTurn(context,dataGame,dataPlayer, graphicGame);
	}

	public static void beginGame(ApplicationContext context) throws IOException {
		try {
			var dataGame = new DataGame();
			var player = new Player("Toto",18,0,null);
			dataGame.createBoard();
			var dataPlayer = new DataPlayer(player,new Environment());
			var graphicGame = new GraphicGame(50 , 50, 150,dataGame,6,4);
			GraphicGame.drawBegin(context, dataPlayer, graphicGame);
			gameLoop(context,dataGame, dataPlayer, graphicGame);
				
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
