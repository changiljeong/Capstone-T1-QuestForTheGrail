package com.questforholygrail.game.ui;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

public class GameIntroPanel extends JPanel {
  private JLabel imgLabel;
  private ImageIcon imgIcon;

  public GameIntroPanel() {
    this.imgIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/splash-Art/Instructions-SplashArt.png")));
    imgLabel = new JLabel(imgIcon);
    imgLabel.setSize(968,576);
    add(imgLabel);
    this.setBackground(Color.GRAY);
  }

  public JLabel getImgLabel() {
    return imgLabel;
  }

  public void setImgLabel(JLabel imgLabel) {
    this.imgLabel = imgLabel;
  }

  public ImageIcon getImgIcon() {
    return imgIcon;
  }

  public void setImgIcon(ImageIcon imgIcon) {
    this.imgIcon = imgIcon;
  }
}
