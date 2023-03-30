package com.questforholygrail.game.UI;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UtilityWidgetPanel extends JPanel {

  MainGameWindow frame;

  public UtilityWidgetPanel(MainGameWindow frame){
    this.frame = frame;
    createButtons();
    setBounds(0, 0, 100, 100);
  }

  private void createButtons(){
    JButton helpButton = new JButton("HELP");
    JButton settingsButton = new JButton("SETTINGS");
    JButton soundButton = new JButton("SOUND");
    helpButton.addActionListener(e -> helpButtonPressed());
    settingsButton.addActionListener(e -> settingsButtonPressed());
    soundButton.addActionListener(e -> soundButtonPressed());
    this.add(helpButton);
    this.add(settingsButton);
    this.add(soundButton);
    this.setBackground(Color.blue);
  }

  private void helpButtonPressed(){
    HelpPopUp.displayHelpScreen(frame);
  }

  private void settingsButtonPressed(){

  }
  private void soundButtonPressed(){

  }

}
