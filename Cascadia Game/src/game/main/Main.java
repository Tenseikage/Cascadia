package game.main;

import java.io.IOException;
import java.util.Scanner;

import game.display.Display;
import game.material.Choice;
import game.material.Tile;
import game.material.Token;

public class Main {

	public static void main(String[] args) throws IOException {
		int numberTurns = 0;
		var display = new Display();
		display.displayRules();
		var tiles = Tile.ExploitCsv();
		var tokens = Token.tokenList();
		
		while (numberTurns < 20) {
			//RECAP : a chaque tour il faut afficher 1 le choix, le board du jour 1, géréer les entrées du j1, faire la meme pour le joueur 2;
			numberTurns++;
			
			
			//tour joueur 1;
			System.out.println("PLAYER 1\n");
			Scanner scannerJ1 = new Scanner(System.in);
			var boardj1 = new Choice();
			boardj1.createChoiceBoard(tiles, tokens);
			display.displayTile(boardj1); 
			display.displayToken(boardj1);
			System.out.println("  ( 01 )      ( 02 )      ( 03 )      ( 04 )\n");
			System.out.println("WHICH PAIR YOU CHOOSE (1 - 2 - 3 - 3) ? : ");
			String choiceJ1 = scannerJ1.nextLine();
			System.out.println("Vous avez choisis : \n"+choiceJ1);
//			switch (Integer.parseInt(choiceJ1)) {
//				case 1 -> ;
//				case 2 -> ;
//				case 3 -> ;
//				case 4 -> ;
//			}
			
			//tour joueur 2;
			System.out.println("PLAYER 2\n");
			var boardj2 = new Choice();
			boardj2.createChoiceBoard(tiles, tokens);
			display.displayTile(boardj2); 
			display.displayToken(boardj2);	
			System.out.println("  ( 01 )      ( 02 )      ( 03 )      ( 04 )\n");
			System.out.println("WHICH PAIR YOU CHOOSE (1 - 2 - 3 - 3) ? : ");
			String choiceJ2 = scannerJ1.nextLine();
			System.out.println("Vous avez choisis : \n"+choiceJ2);
//			switch (Integer.parseInt(choiceJ2)) {
//				case 1 -> ;
//				case 2 -> ;
//				case 3 -> ;
//				case 4 -> ;
//			}
		}
	}

}
