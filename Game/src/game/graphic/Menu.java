package game.graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import com.github.forax.zen.Application;
import com.github.forax.zen.ApplicationContext;
import com.github.forax.zen.Event;
import com.github.forax.zen.KeyboardEvent;
import com.github.forax.zen.PointerEvent;

public class Menu {
	
	public void start(ApplicationContext context) {
		var screenInfo = context.getScreenInfo();
        var width = screenInfo.width();
        var height = screenInfo.height();
        String s = "Welcome to";
        String c = "CASCADIA";
        String boutton = "Jouer";
        

        for (;;) {
            Event event = context.pollOrWaitEvent(100);

            if (event != null) {
                System.out.println(event);

                switch (event) {
                    case PointerEvent pointerEvent -> {
                        if (pointerEvent.action() == PointerEvent.Action.POINTER_UP) {
                            var x = pointerEvent.location().x();
                            var y = pointerEvent.location().y();
                            if ((x > (width - 200) / 2 && x < (width+200) / 2) && (y > ((height / 2) - (50 / 2)+250) && y < ((height / 2) - (50 / 2)+330))) {
                            	context.dispose();
                            	//modes(context);
                            	System.out.println("jouer"); // envoyer sur page selection de mode 
                            	return;
                            }
                        }
                    }
                    case KeyboardEvent keyboardEvent -> {
                        if (keyboardEvent.key() == KeyboardEvent.Key.ESCAPE) {
                            context.dispose();
                            return;
                        }
                    }
                    default -> {
                        // Ignorer d'autres types d'événements
                    }
                }
            }

            context.renderFrame(graphics2D -> {
                graphics2D.clearRect(0, 0, width, height);
                graphics2D.setColor(Color.BLACK);
                graphics2D.setFont(new Font("Arial", Font.BOLD, 50));
                FontMetrics metricsS = graphics2D.getFontMetrics();
                int textWidthS = metricsS.stringWidth(s);
                int textHeightS = metricsS.getAscent();

                FontMetrics metricsC = graphics2D.getFontMetrics();
                int textWidthC = metricsC.stringWidth(c);
                //int textHeightC = metricsC.getAscent();
                int xS = (width - textWidthS) / 2;
                int yS = (height / 2) - (textHeightS / 2);
                
                int xC = (width - textWidthC) / 2;
                int yC = yS + 50;
                graphics2D.drawString(s, xS, yS);
                graphics2D.drawString(c, xC, yC);
                
         
                int rectLarge = 200;
                int rectHaut = 80;
                int rectX = (width - rectLarge) / 2;
                int rectY = yC + 200; 

                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(rectX, rectY, rectLarge, rectHaut); 
                
                
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(rectX, rectY, rectX+200, rectY);
                graphics2D.drawLine(rectX, rectY, rectX, rectY+80);
                graphics2D.drawLine(rectX+200, rectY, rectX+200, rectY+80);
                graphics2D.drawLine(rectX, rectY+80, rectX+200, rectY+80);

                
              
                graphics2D.setColor(Color.BLACK);
                graphics2D.setFont(new Font("Arial", Font.BOLD, 30));

                FontMetrics metricsButton = graphics2D.getFontMetrics();
                int buttonTextWidth = metricsButton.stringWidth(boutton);
                int buttonTextHeight = metricsButton.getAscent();

                // Centrer le texte "Jouer" dans le rectangle
                int buttonX = rectX + (rectLarge - buttonTextWidth) / 2;
                int buttonY = rectY + (rectHaut + buttonTextHeight) / 2 - 5;

                graphics2D.drawString(boutton, buttonX, buttonY);
            });
        }
	}
	
	public void modes(ApplicationContext context) {
		var screenInfo = context.getScreenInfo();
        var width = screenInfo.width();
        var height = screenInfo.height();
        var string = "Choisissez votre mode de jeu";
        System.out.println("largeur : "+ width + " hauteur " + height);

        for (;;) {
            Event event = context.pollOrWaitEvent(100);

            if (event != null) {
                System.out.println(event);

                switch (event) {
                    case PointerEvent pointerEvent -> {
                        if (pointerEvent.action() == PointerEvent.Action.POINTER_UP) {
                            var x = pointerEvent.location().x();
                            var y = pointerEvent.location().y();
                            if ((x > (width -200) / 2 && x < (width+200) / 2) && (y > ((height /2) - (50 /2)+250) && y < ((height /2) - (50 /2)+330))) {
                            	context.dispose();
                            	System.out.println("jouer");
                            	return;
                            }
                        }
                    }
                    case KeyboardEvent keyboardEvent -> {
                        if (keyboardEvent.key() == KeyboardEvent.Key.ESCAPE) {
                            context.dispose();
                            return;
                        }
                    }
                    default -> {
                        // Ignorer d'autres types d'événements
                    }
                }
            }

            context.renderFrame(graphics2D -> {
            	graphics2D.clearRect(0, 0, width, height);
                graphics2D.setColor(Color.BLACK);
                graphics2D.setFont(new Font("Arial", Font.BOLD, 50));
                FontMetrics metricsString = graphics2D.getFontMetrics();
                int stringLarge = metricsString.stringWidth(string);
                int stringHaut = 50;
                int largeurBoutton = width/6;
                int hauteurBoutton = height/14;
                
                
                graphics2D.drawString(string, (width - stringLarge)/2, (height - stringHaut)/10);
                
                
                int x = (width)/2 - (largeurBoutton/2) ;
                
                int y1 = (height - hauteurBoutton)* 2 / 7;
                
                String boutton1 = "Terminal";
                FontMetrics metricsButton = graphics2D.getFontMetrics();
                int boutton1TailleTxtX = metricsButton.stringWidth(boutton1);
                int boutton1TailleTxtY = metricsButton.getAscent();
 
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(x, y1, largeurBoutton, hauteurBoutton); 
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(x, y1, x+largeurBoutton, y1);
                graphics2D.drawLine(x, y1, x, y1+hauteurBoutton);
                graphics2D.drawLine(x+largeurBoutton, y1, x+largeurBoutton, y1+hauteurBoutton);
                graphics2D.drawLine(x, y1+hauteurBoutton, x+largeurBoutton, y1+hauteurBoutton);
                
                graphics2D.drawString(boutton1, x+(largeurBoutton / 2) - (boutton1TailleTxtX / 2), y1+(hauteurBoutton) - (boutton1TailleTxtY/2)); // changer le Y pour centrer 
                
                int y2 = (height - hauteurBoutton)* 3 / 7;
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(x, y2, largeurBoutton, hauteurBoutton); 
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(x, y2, x+largeurBoutton, y2);
                graphics2D.drawLine(x, y2, x, y2+hauteurBoutton);
                graphics2D.drawLine(x+largeurBoutton, y2, x+largeurBoutton, y2+hauteurBoutton);
                graphics2D.drawLine(x, y2+hauteurBoutton, x+largeurBoutton, y2+hauteurBoutton);
                
                
                int y3 = (height - hauteurBoutton)* 4 / 7;
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(x, y3, largeurBoutton, hauteurBoutton);
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(x, y3, x+largeurBoutton, y3);
                graphics2D.drawLine(x, y3, x, y3+hauteurBoutton);
                graphics2D.drawLine(x+largeurBoutton, y3, x+largeurBoutton, y3+hauteurBoutton);
                graphics2D.drawLine(x, y3+hauteurBoutton, x+largeurBoutton, y3+hauteurBoutton);
                
                
                int y4 = (height - hauteurBoutton)* 5 / 7;
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(x, y4, largeurBoutton, hauteurBoutton); 
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(x, y4, x+largeurBoutton, y4);
                graphics2D.drawLine(x, y4, x, y4+hauteurBoutton);
                graphics2D.drawLine(x+largeurBoutton, y4, x+largeurBoutton, y4+hauteurBoutton);
                graphics2D.drawLine(x, y4+hauteurBoutton, x+largeurBoutton, y4+hauteurBoutton);
                
                
                int y5 = (height - hauteurBoutton)* 6 / 7;
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(x, y5, largeurBoutton, hauteurBoutton); 
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(x, y5, x+largeurBoutton, y5);
                graphics2D.drawLine(x, y5, x, y5+hauteurBoutton);
                graphics2D.drawLine(x+largeurBoutton, y5, x+largeurBoutton, y5+hauteurBoutton);
                graphics2D.drawLine(x, y5+hauteurBoutton, x+largeurBoutton, y5+hauteurBoutton);
            });
        }
	}

    public static void main(String[] args) {
        //RandomGenerator random = RandomGenerator.getDefault();
        // Start the application, create a drawing area, full screen
        Application.run(Color.WHITE, context -> {
        	var menu = new Menu();
        	//menu.start(context);
        	menu.modes(context);
        });
    }
}