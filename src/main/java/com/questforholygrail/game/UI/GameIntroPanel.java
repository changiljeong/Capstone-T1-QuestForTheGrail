package com.questforholygrail.game.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameIntroPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  public GameIntroPanel() {
    setPreferredSize(new Dimension(400, 300));
    setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weighty = 0.5;

    JLabel titleLabel = new JLabel("Quest for the Holy Grail");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
    add(titleLabel, gbc);

    gbc.gridy++;

    JLabel introLabel = new JLabel("<html>Welcome to Quest for the Holy Grail!<br>"
        + "You are a brave knight on a quest to find the treasure.<br>"
        + "To succeed, you must navigate through treacherous lands, battle fierce foes,<br>"
        + "and solve challenging puzzles. Good luck!</html>");
    introLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    add(introLabel, gbc);

    gbc.gridy++;

    JLabel instructionsLabel = new JLabel("<html>Instructions:<br>"
        + "Use the arrow keys to move your knight.<br>"
        + "Press 'space' to attack enemies and interact with objects.<br>"
        + "Collect keys and other items to unlock doors and solve puzzles.<br>"
        + "Defeat bosses to progress to new areas.<br>"
        + "Have fun!</html>");
    instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    add(instructionsLabel, gbc);
  }
}
