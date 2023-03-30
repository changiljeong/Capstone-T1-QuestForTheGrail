package com.questforholygrail.game.UI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HelpPopUp extends JOptionPane {


  public static void displayHelpScreen(MainGameWindow frame){
    showMessageDialog(frame, frame.getGameText().getHelpMessage(), "Help", JOptionPane.PLAIN_MESSAGE);
  }

}
