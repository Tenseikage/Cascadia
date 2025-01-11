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

    public static PeerTileToken choice(DataGame dataGame) {
      int maxChoice  = dataGame.choiceboard().getChoiceBoard().size();
      String message = "<html>Choisissez une paire tuile/jeton<br> (Veuillez entrer un numéro entre 1 et "+ maxChoice+ ")</html>";
      int choice = -1;
      while (choice < 1 || choice > maxChoice) {
        String input = JOptionPane.showInputDialog(null, message);
        try {
          choice = Integer.parseInt(input);
          if (choice < 1 || choice > 4) {
            JOptionPane.showMessageDialog(null, "Choix de paire impossible ! Veuillez entrer un numéro entre 1 et " + maxChoice + ".", "Erreur", JOptionPane.ERROR_MESSAGE);
          }
       } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Entrée invalide : " + input + ". Veuillez entrer un numéro entre 1 et " + maxChoice + ".", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
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


    public static int modScore(){
      int mode = -1;
      while(mode != 1 && mode != 0){
        String msgMode = "<html> Choisissez votre mode <br>(tapez 1 pour le mode familial ou 0 pour le mode intermédiaire)</html>";
        String modeInput = JOptionPane.showInputDialog(null, msgMode);
        try{
          mode = Integer.parseInt(modeInput);
          if(mode != 1 && mode != 0){
            JOptionPane.showMessageDialog(null, "Mode inexistant : " + modeInput, "Erreur", JOptionPane.ERROR_MESSAGE);
          }
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(null, "Entrée invalide : " + modeInput, "Erreur", JOptionPane.ERROR_MESSAGE);
      }
    }
    return mode; 
  }
  public static int choiceReturnToken(){
    String message = "<html>Souhaitez remettre le jeton dans le sac ?<br>Oui(Yes) / Non(No)</html>";
    int option = JOptionPane.showConfirmDialog(null, message, "Choix", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    return option;
  }


    public static void main(String[] args) {
      var player = createPlayer();
      System.out.println(player.name());
      System.out.println(player.age());
    }


  

    
    
}
