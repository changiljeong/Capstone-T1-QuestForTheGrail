package com.questforholygrail.game.ui;

import javax.swing.JOptionPane;

public class HelpPopUp extends JOptionPane {


  public static void displayHelpScreen(MainGameWindow frame){
    showMessageDialog(frame, frame.getGameText().getGuiHelpMessage(), "Help", JOptionPane.PLAIN_MESSAGE);
  }

}
