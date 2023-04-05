package com.questforholygrail.game.UI;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UtilityWidgetPanel extends JPanel {

  MainGameWindow frame;
  SettingsPopUp settings;

    public UtilityWidgetPanel(MainGameWindow frame){
        this.frame = frame;
        settings = new SettingsPopUp(frame);
        createButtons();
        setBounds(0, 0, 100, 100);
    }

    private void createButtons(){
        JButton helpButton = new JButton("HELP");
        JButton settingsButton = new JButton("SETTINGS");
        helpButton.addActionListener(e -> helpButtonPressed());
        settingsButton.addActionListener(e -> settingsButtonPressed());
        this.add(helpButton);
        this.add(settingsButton);
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

}