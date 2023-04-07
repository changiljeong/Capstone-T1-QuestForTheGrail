package com.questforholygrail.game.ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UtilityWidgetPanel extends JPanel {

  MainGameWindow frame;
  SettingsPopUp settings;

  MapPopUp map;

  public UtilityWidgetPanel(MainGameWindow frame){
    this.frame = frame;
    settings = new SettingsPopUp(frame);
    map = new MapPopUp(frame);
    createButtons();
    setBounds(0, 0, 100, 100);
  }

  private void createButtons(){
    JButton helpButton = new JButton("HELP");
    JButton settingsButton = new JButton("SETTINGS");
    JButton mapButton = new JButton("MAP");
    helpButton.addActionListener(e -> helpButtonPressed());
    settingsButton.addActionListener(e -> settingsButtonPressed());
    mapButton.addActionListener(e -> mapButtonPressed());
    this.add(helpButton);
    this.add(settingsButton);
    this.add(mapButton);
    this.setBackground(Color.blue);
  }

  private void helpButtonPressed(){

    HelpPopUp.displayHelpScreen(frame);
    frame.getGame().requestFocus();

  }

  private void settingsButtonPressed(){
    settings.displaySettingsOptions();
    frame.getGame().requestFocus();
  }

  private void mapButtonPressed(){
    map.showMiniMap();
    frame.getGame().requestFocus();
  }

}