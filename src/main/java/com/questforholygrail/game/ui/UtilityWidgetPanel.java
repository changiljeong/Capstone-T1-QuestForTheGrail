package com.questforholygrail.game.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UtilityWidgetPanel extends JPanel {

  MainGameWindow frame;
  SettingsPopUp settings;
  MapPopUp map;

  MiniMapPanel mmp;
  GridBagConstraints gbc = new GridBagConstraints();

  InventoryPanel iPanel;

  public UtilityWidgetPanel(MainGameWindow frame){
    this.frame = frame;
    settings = new SettingsPopUp(frame);
    map = new MapPopUp(frame);
    setLayout(new GridBagLayout());
    createButtons();
  }

  public void generateMiniMap(){
    this.mmp = new MiniMapPanel(true);
//    mmp.setPreferredSize(new Dimension(100, 300));
    gbc.gridy = 1;
    gbc.gridx = 0;
    gbc.gridwidth =3;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    this.add(mmp, gbc);

  }

  public void updateMiniMap(){
    if(mmp != null){
      mmp.updateUtilMap();
      mmp.setOpaque(false);
    }
  }

  public void generateInventory(){
    this.iPanel = new InventoryPanel(true);
//    iPanel.setPreferredSize(new Dimension(100, 300));
    gbc.gridy = 2;
    gbc.gridx = 0;
    gbc.gridwidth =3;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    this.add(iPanel, gbc);
  }

  public void updateInventory(){
    if(iPanel != null){
      iPanel.updateUtilInventory();
      iPanel.setOpaque( false );
      revalidate();
    }
  }

  private void createButtons(){
    JButton helpButton = new JButton("HELP");
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    JButton settingsButton = new JButton("SETTINGS");
    JButton mapButton = new JButton("MAP");
    helpButton.addActionListener(e -> helpButtonPressed());
    settingsButton.addActionListener(e -> settingsButtonPressed());
    mapButton.addActionListener(e -> mapButtonPressed());
    this.add(helpButton, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    this.add(settingsButton, gbc);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    this.add(mapButton, gbc);
    this.setBackground(Color.black);
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