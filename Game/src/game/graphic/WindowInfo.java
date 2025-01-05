package game.graphic;

import javax.swing.*;



public class WindowInfo {
    public static void messageInfo(String message, String tag){
      JOptionPane.showMessageDialog(null, message, tag, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int keepOrPass(){
      String message = "<html>Souhaitez-vous garder les jetons ?<br>Oui(Yes) / Non(No)</html>";
      int option = JOptionPane.showConfirmDialog(null, message, "Choix", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      return option;
    }

    
    public static void main(String[] args) {
        // Créer la fenêtre principale
        

			/*String longMessage = "<html>Ceci est un long message d'information.<br>" +
                             "Il peut contenir plusieurs lignes de texte pour fournir<br>" +
                             "des informations détaillées à l'utilisateur.<br>" +
                             "Vous pouvez ajouter autant de texte que nécessaire<br>" +
                             "pour expliquer les détails importants.</html>";
        JOptionPane.showMessageDialog(null, longMessage, "Information", JOptionPane.INFORMATION_MESSAGE);*/
      //overcrowding();
      System.out.println("Result : " + keepOrPass());
    }
}
