package game.graphic;

import game.logic.GameLogic;
import game.material.Environment;
import game.material.PeerTileToken;
import game.player.Player;
import javax.swing.*;



public class WindowInfo {
    public static void messageInfoError(String message, String tag){
      switch (tag) {
            case "Information" -> {
              JOptionPane.showMessageDialog(null, message, tag, JOptionPane.INFORMATION_MESSAGE);
            }   
            case "Erreur" -> {
              JOptionPane.showMessageDialog(null, message, tag, JOptionPane.ERROR_MESSAGE);
            }
            case "Surpopulation" -> {
              JOptionPane.showMessageDialog(null, message, tag, JOptionPane.WARNING_MESSAGE);
            }
            default -> {}    
        }
      
    }

    public static int keepOrPass(){
      String message = "<html>Souhaitez-vous garder les jetons ?<br>Oui(Yes) / Non(No)</html>";
      int option = JOptionPane.showConfirmDialog(null, message, "Choix", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      return option;
    }

    public static PeerTileToken choice(DataGame dataGame){
      String message = "<html>Choisissez une paire tuile/jeton<br> (Veuillez entrer un numéro entre 1 et 4)</html>";
      String input = JOptionPane.showInputDialog(null, message);
      int choice = -1; // Valeur par défaut en cas d'erreur de conversion
      try {
          choice = Integer.parseInt(input);
          if (choice > 4){
            System.err.println("Nombre trop grand !");
          }
      } catch (NumberFormatException e) {
          System.err.println("Entrée invalide : " + input);
      }
      var peer = GameLogic.finalChoiceTileToken(choice, dataGame.choiceboard().getChoiceBoard());
      dataGame.choiceboard().getChoiceBoard().remove(choice - 1);
      return peer;
    }

    public static Player createPlayer() {
      String name = null;
      while (name == null || name.trim().isEmpty()) {
        String msgName = "Choisissez un nom";
        name = JOptionPane.showInputDialog(null, msgName);
        if (name == null || name.trim().isEmpty()) {
          JOptionPane.showMessageDialog(null, "Nom invalide. Veuillez entrer un nom.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
      }
      int age = -1;
      while (age < 10) {
        String msgAge = "Mettez votre age";
        String ageInput = JOptionPane.showInputDialog(null, msgAge);
        try {
          age = Integer.parseInt(ageInput);
          if (age < 10) {
              JOptionPane.showMessageDialog(null, "Age trop petit : impossible de jouer au jeu", "Erreur", JOptionPane.ERROR_MESSAGE);
              age = -1;
          }
          } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(null, "Entrée invalide : " + ageInput, "Erreur", JOptionPane.ERROR_MESSAGE);
          }
      }
      return new Player(name, age, 0, new Environment());
    }
    public static void main(String[] args) {
      var player = createPlayer();
      System.out.println(player.name());
      System.out.println(player.age());
    }


  

    
    
}
