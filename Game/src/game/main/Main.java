package game.main;

import java.awt.Color;
import java.io.IOException;

import com.github.forax.zen.Application;

import game.graphic.Menu;

/**
 * Main class of project
 * @author Loïc BERGEOT
 */
public class Main {

    /**
     * Main method of the project
     * 
     */
    public static void main(String[] args) throws IOException,IllegalAccessError {
        Application.run(Color.WHITE, context -> {
        	var menu = new Menu();
        	try {
                menu.start(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
