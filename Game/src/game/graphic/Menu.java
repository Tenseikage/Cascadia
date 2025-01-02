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
        
        
        // COORDONNEES DU BOUTON
        
        int rectLarge = width/10;
        int rectHaut = height/14;
        
        int rectX = (width - rectLarge) / 2;
        int rectY = height * 4/ 6; 

        int boutonX1 = rectX;
        int boutonX2 = rectX + rectLarge;
        int boutonY1 = rectY;
        int boutonY2 = rectY + rectHaut;

        for (;;) {
            Event event = context.pollOrWaitEvent(100);

            if (event != null) {
                System.out.println(event);

                switch (event) {
                    case PointerEvent pointerEvent -> {
                        if (pointerEvent.action() == PointerEvent.Action.POINTER_UP) {
                            var x = pointerEvent.location().x();
                            var y = pointerEvent.location().y();
                            if ((x > boutonX1 && x < boutonX2) && (y > boutonY1 && y < boutonY2)) {
                            	modes(context);
                            	context.dispose();
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
                String welcom = "Welcome to";
                String cascadia = "CASCADIA";
                String boutton = "JOUER";
                
                FontMetrics metricsWelcom = graphics2D.getFontMetrics();
                int textWidthWelcom = metricsWelcom.stringWidth(welcom);
                int textHeightWelcom = metricsWelcom.getAscent();

                FontMetrics metricsC = graphics2D.getFontMetrics();
                int textWidthCascadia = metricsC.stringWidth(cascadia);
                
                int welcomY = (height / 2) - (textHeightWelcom / 2);
                int welcomX = (width - textWidthWelcom) / 2;
                
                int xStringCascadia = (width - textWidthCascadia) / 2;
                int yStringCascadia = (height) / 2 + height/40;
                
                
                graphics2D.drawString(welcom, welcomX, welcomY);
                graphics2D.drawString(cascadia, xStringCascadia, yStringCascadia);
                
                
                
                // RECTANGLE BOUTON
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(rectX, rectY, rectLarge, rectHaut); 
                
                // CONTOUR DU BOUTON
                
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(rectX, rectY, rectX+rectLarge, rectY);
                graphics2D.drawLine(rectX, rectY, rectX, rectY+rectHaut);
                graphics2D.drawLine(rectX+rectLarge, rectY, rectX+rectLarge, rectY+rectHaut);
                graphics2D.drawLine(rectX, rectY+rectHaut, rectX+rectLarge, rectY+rectHaut);

                
              
                graphics2D.setColor(Color.BLACK);
                graphics2D.setFont(new Font("Arial", Font.BOLD, 30));

                FontMetrics metricsButton = graphics2D.getFontMetrics();
                int buttonTextWidth = metricsButton.stringWidth(boutton);
                int buttonTextHeight = metricsButton.getAscent();

                // Centrer le texte "Jouer" dans le rectangle
                int buttonX = rectX + (rectLarge - buttonTextWidth) / 2;
                int buttonY = rectY + (rectHaut + buttonTextHeight) / 2;

                graphics2D.drawString(boutton, buttonX, buttonY);
            });
        }
	}
	
	public void modes(ApplicationContext context) {
		var screenInfo = context.getScreenInfo();
        var width = screenInfo.width();
        var height = screenInfo.height();
        var string = "Choisissez votre mode de jeu";
        
        int largeurBoutton = width/4;
        int hauteurBoutton = height/14;
        
        int x = (width)/2 - (largeurBoutton/2) ;
        
        int y1 = (height - hauteurBoutton)* 2 / 7;
        String boutton1 = "TERMINAL";
        
        int y2 = (height - hauteurBoutton)* 3 / 7;
        String boutton2 = "TUILES HÉXAGONALES";
        
        int y3 = (height - hauteurBoutton)* 4 / 7;
        String boutton3 = "TUILES CARRÉES";
        
        int y4 = (height - hauteurBoutton)* 5 / 7;
        String boutton4 = "2 JOUEURS";

        int y5 = (height - hauteurBoutton)* 6 / 7;
        String boutton5 = "SOLO";

        

        for (;;) {
            Event event = context.pollOrWaitEvent(100);

            if (event != null) {
                System.out.println(event);

                switch (event) {
                    case PointerEvent pointerEvent -> {
                        if (pointerEvent.action() == PointerEvent.Action.POINTER_UP) {
                            var xclic = pointerEvent.location().x();
                            var yclic = pointerEvent.location().y();
                            if (xclic < x + largeurBoutton && xclic > x) {
                            	if (yclic < y1 + hauteurBoutton && yclic > y1) {
                            		System.out.println("mode choisi -> "+ boutton1);
                            		context.dispose();
                                    return;
                            	} else if (yclic < y2 + hauteurBoutton && yclic > y2) {
                            		System.out.println("mode choisi -> "+boutton2);
                            		context.dispose();
                                    return;
                            	} else if (yclic < y3 + hauteurBoutton && yclic > y3) {
                            		System.out.println("mode choisi -> "+boutton3);
                            		context.dispose();
                                    return;
                            	} else if (yclic < y4 + hauteurBoutton && yclic > y4) {
                            		System.out.println("mode choisi -> "+boutton4);
                            		context.dispose();
                                    return;
                            	} else if (yclic < y5 + hauteurBoutton && yclic > y5) {
                            		System.out.println("mode choisi -> "+boutton5);
                            		context.dispose();
                                    return;
                            	}
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

                
                graphics2D.drawString(string, (width - stringLarge)/2, (height - stringHaut)/10);
                
                
                
                
                
                // BOUTON 1
                


                

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
                
                graphics2D.drawString(boutton1, x+(largeurBoutton / 2) - (boutton1TailleTxtX / 2), y1+(hauteurBoutton +boutton1TailleTxtY)/2); // changer le Y pour centrer 
                
                
                
                
                
                // BOUTON 2

                int boutton2TailleTxtX = metricsButton.stringWidth(boutton2);
                int boutton2TailleTxtY = metricsButton.getAscent();
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(x, y2, largeurBoutton, hauteurBoutton); 
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(x, y2, x+largeurBoutton, y2);
                graphics2D.drawLine(x, y2, x, y2+hauteurBoutton);
                graphics2D.drawLine(x+largeurBoutton, y2, x+largeurBoutton, y2+hauteurBoutton);
                graphics2D.drawLine(x, y2+hauteurBoutton, x+largeurBoutton, y2+hauteurBoutton);
                
                graphics2D.drawString(boutton2, x+(largeurBoutton / 2) - (boutton2TailleTxtX / 2), y2+(hauteurBoutton +boutton2TailleTxtY)/2); // changer le Y pour centrer 
                
                
                
                
                
                
                // BOUTON 3
                

                

                int boutton3TailleTxtX = metricsButton.stringWidth(boutton3);
                int boutton3TailleTxtY = metricsButton.getAscent();
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(x, y3, largeurBoutton, hauteurBoutton);
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(x, y3, x+largeurBoutton, y3);
                graphics2D.drawLine(x, y3, x, y3+hauteurBoutton);
                graphics2D.drawLine(x+largeurBoutton, y3, x+largeurBoutton, y3+hauteurBoutton);
                graphics2D.drawLine(x, y3+hauteurBoutton, x+largeurBoutton, y3+hauteurBoutton);
                
                graphics2D.drawString(boutton3, x+(largeurBoutton / 2) - (boutton3TailleTxtX / 2), y3+(hauteurBoutton +boutton3TailleTxtY)/2); // changer le Y pour centrer 
                
                
                
                
                // BOUTON 4
                

                

                int boutton4TailleTxtX = metricsButton.stringWidth(boutton4);
                int boutton4TailleTxtY = metricsButton.getAscent();
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(x, y4, largeurBoutton, hauteurBoutton); 
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(x, y4, x+largeurBoutton, y4);
                graphics2D.drawLine(x, y4, x, y4+hauteurBoutton);
                graphics2D.drawLine(x+largeurBoutton, y4, x+largeurBoutton, y4+hauteurBoutton);
                graphics2D.drawLine(x, y4+hauteurBoutton, x+largeurBoutton, y4+hauteurBoutton);
                
                graphics2D.drawString(boutton4, x+(largeurBoutton / 2) - (boutton4TailleTxtX / 2), y4+(hauteurBoutton +boutton4TailleTxtY)/2); // changer le Y pour centrer 

                
                
                
                
                // BOUTON 5
                              

                int boutton5TailleTxtX = metricsButton.stringWidth(boutton5);
                int boutton5TailleTxtY = metricsButton.getAscent();
                
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillRect(x, y5, largeurBoutton, hauteurBoutton); 
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(x, y5, x+largeurBoutton, y5);
                graphics2D.drawLine(x, y5, x, y5+hauteurBoutton);
                graphics2D.drawLine(x+largeurBoutton, y5, x+largeurBoutton, y5+hauteurBoutton);
                graphics2D.drawLine(x, y5+hauteurBoutton, x+largeurBoutton, y5+hauteurBoutton);
                
                graphics2D.drawString(boutton5, x+(largeurBoutton / 2) - (boutton5TailleTxtX / 2), y5+(hauteurBoutton +boutton5TailleTxtY)/2); // changer le Y pour centrer 
            });
        }
	}

    public static void main(String[] args) {
        //RandomGenerator random = RandomGenerator.getDefault();
        // Start the application, create a drawing area, full screen
        Application.run(Color.WHITE, context -> {
        	var menu = new Menu();
        	menu.start(context);
        	//menu.modes(context);
        });
    }
}