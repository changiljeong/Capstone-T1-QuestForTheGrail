package com.questforholygrail.game.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UtilityWidgetPanel extends JPanel {

  MainGameWindow frame;
  SettingsPopUp settings;
  MapPopUp map;

  MiniMapPanel mmp;
  GridBagConstraints gbc = new GridBagConstraints();

  public UtilityWidgetPanel(MainGameWindow frame){
    this.frame = frame;
    settings = new SettingsPopUp(frame);
    map = new MapPopUp(frame);
    setLayout(new GridBagLayout());
    createButtons();
  }

  public void generateMiniMap(){
    this.mmp = new MiniMapPanel(true);
    mmp.setPreferredSize(new Dimension(100, 500));
    gbc.gridy = 1;
    gbc.gridx = 0;
    gbc.gridwidth =3;
    gbc.fill = GridBagConstraints.BOTH;
    this.add(mmp, gbc);

  }

  public void updateMiniMap(){
    if(mmp != null){
      mmp.updateUtilMap();
      revalidate();
    }
  }
  private void createButtons(){
    JButton helpButton = new JButton("HELP");
    gbc.gridx = 0;
    gbc.gridy = 0;
    JButton settingsButton = new JButton("SETTINGS");
    JButton mapButton = new JButton("MAP");
    helpButton.addActionListener(e -> helpButtonPressed());
    settingsButton.addActionListener(e -> settingsButtonPressed());
    mapButton.addActionListener(e -> mapButtonPressed());
    this.add(helpButton, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    this.add(settingsButton, gbc);
    gbc.gridx = 2;
    gbc.gridy = 0;
    this.add(mapButton, gbc);
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