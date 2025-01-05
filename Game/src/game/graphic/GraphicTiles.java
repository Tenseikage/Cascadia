package game.graphic;
import com.github.forax.zen.*;

import game.logic.Choice;
import game.material.Tile;
import game.material.Token;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

//class Square


public class GraphicTiles {

	private static void checkRange(double min, double value, double max) {
		if (value < min || value > max) {
			throw new IllegalArgumentException("Invalid coordinate: " + value);
			}
		}

	/**
	 * Draws a square on the screen after a mouse click
	 * @param graphics
	 * @param posMouseX
	 * @param posMouseY
	 * @param startX
	 * @param startY
	 * @param squareSize
	 * @param rows
	 * @param cols
	 */
	private static void drawSquareOnClick(Graphics2D graphics, int posMouseX, int posMouseY, int startX, int startY, int squareSize, int rows, int cols) {
	  Objects.requireNonNull(graphics);
		graphics.setColor(Color.BLACK); // Définir la couleur du contour en noir
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (col == 0 && (row == 0 || row == 1 || row == 2)) {
					continue;
			  } 
				int posSquareX = startX + col * squareSize;
				int posSquareY = startY + row * squareSize;
				if ((posMouseX >= posSquareX) && (posMouseX < posSquareX + squareSize) && (posMouseY >= posSquareY) && (posMouseY < posSquareY + squareSize)) {
					graphics.drawRect(posSquareX, posSquareY, squareSize, squareSize); // Dessiner un carré
					Fonts.fontManage(graphics, posSquareX, posSquareY, squareSize);
					return; // Dessiner seulement un carré
			  }
		  }
		}
	}
	/**
	 * Display the start Tile in the screen
	 * @param graphics
	 * @param startX
	 * @param startY
	 */
	private static void displayStartTiles(Graphics2D graphics,int startX, int startY){
		var tile = new Tile();
		Tile.startTiles();
		var startChoice = tile.getStartiles();
		Objects.requireNonNull(graphics);
		int squareSize = 150;
		graphics.setColor(Color.RED);
		for(int i = 0; i < 3; i ++){
			graphics.drawRect(startX, startY + squareSize * i, squareSize, squareSize);
			Fonts.fontManageTiles(graphics, startX,startY + squareSize * i , squareSize, startChoice.get(i),20);
		}
	}


	private static void displayChoices(Graphics2D graphics,int startX, int startY, ArrayList<Tile> tiles){
		// Affichage des choix de tuiles
		//Tests tuiles
		var board = new Choice();
		var tokens = Token.tokenList();
	  board.createChoiceBoard(tiles, tokens, null,0);
		var choice = board.getChoiceBoard();
		for(int j = 0; j < 4; j ++){
			graphics.setColor(Color.BLUE);
			int squareSize = 100;
			graphics.drawRect(startX, startY + (squareSize + 25) * j, squareSize, squareSize);
			Fonts.fontManageToken(graphics, startX,startY + (squareSize + 25) * j, squareSize, choice.get(j),20);
			graphics.drawRect(startX + 150, startY + (squareSize + 25) * j, squareSize, squareSize);
			Fonts.fontManageTiles(graphics, startX + 150,startY + (squareSize + 25) * j, squareSize, choice.get(j),15);
		}
	}
	/*private static void drawLittleSquare(Graphics2D graphics,int posX, int posY){
		Objects.requireNonNull(graphics);
		graphics.setColor(Color.GREEN); // Définir la couleur du contour en vert
		int newPosX = posX/50 * 50;
		int newPosY = posY/50 * 50;
		System.out.println("Position square (" + (newPosX) + "," +  newPosY +  ")");
    graphics.drawRect(newPosX,newPosY, 200, 200);
	}*/

	//MAIN DU JEU (BOUCLE PRINCIPALE GRAPHIQUE)
	// Ajout de l'environnement du joueur
	public static void manageEventTile(ApplicationContext context,Event event){
		if(event == null){
			context.dispose();
			return;
		}
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.width();
		var height = screenInfo.height();
		switch(event){
			//Pointeur souris
			case PointerEvent ev -> {
				var location = ev.location();
        checkRange(0, location.x(), width);
        checkRange(0, location.y(), height);
				//System.out.println("Position X: " + location.x() + " Position Y: " + location.y()); // Coord pointeur souris
				context.renderFrame(graphics-> {
					//drawLittleSquare2(graphics, location.x(), location.y());
					drawSquareOnClick(graphics, location.x(), location.y(), 35, 35, 150, 4, 6);
				});
			}
			case KeyboardEvent ev -> {
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

	public static void displayTiles(ApplicationContext context, ArrayList<Tile> tiles){
	
		context.renderFrame(graphics -> {
		displayStartTiles(graphics,35,35);
    displayChoices(graphics,1000, 35,tiles);
		while (true) { 
			var event = context.pollOrWaitEvent(10);
			if(event == null){
				continue;
			}
			manageEventTile(context, event);
		}
		});
	}
		
	public static void main(String[] args) throws IOException  {
		/*var tile = new Tile();
		tile.startTiles();
		var startChoice = tile.getStartiles();*/
		var tiles = Tile.ExploitCsv();
		Application.run(Color.WHITE, context -> {
			displayTiles(context, tiles);
			//menu.modes(context);
		});
		
	}
}
