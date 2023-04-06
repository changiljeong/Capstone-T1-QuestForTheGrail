package com.questforholygrail.game.ui;

import javax.swing.*;
import java.awt.*;

public class GameTitlePanel extends JPanel {

    private JLabel containerLabel;
    private JLabel gameTitleLabel;
    private JLabel pressEnterLabel;

    public GameTitlePanel(){

        this.setLayout(new GridBagLayout());
        this.containerLabel = new JLabel("");
        this.gameTitleLabel = new JLabel("   Quest for the Holy Grail");
        this.pressEnterLabel = new JLabel("        Press Enter to Continue");

        Font f1 = new Font(Font.MONOSPACED, Font.BOLD, 40);
        Font f2 = new Font(Font.MONOSPACED, Font.PLAIN, 30);
        gameTitleLabel.setFont(f1);
        pressEnterLabel.setFont(f2);

//        containerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        containerLabel.setPreferredSize(new Dimension(700, 200));
        containerLabel.setLayout(new BorderLayout());
        containerLabel.add(pressEnterLabel, BorderLayout.PAGE_END);
        containerLabel.add(gameTitleLabel, BorderLayout.CENTER);

        add(containerLabel);

        this.setBackground(Color.BLUE);

    }

    public JLabel getGameTitleLabel() {
        return gameTitleLabel;
    }

    public void setGameTitleLabel(JLabel gameTitleLabel) {
        this.gameTitleLabel = gameTitleLabel;
    }

    public JLabel getPressEnterLabel() {
        return pressEnterLabel;
    }

    public void setPressEnterLabel(JLabel pressEnterLabel) {
        this.pressEnterLabel = pressEnterLabel;
    }

    public JLabel getContainerLabel() {
        return containerLabel;
    }

    public void setContainerLabel(JLabel containerLabel) {
        this.containerLabel = containerLabel;
    }
}