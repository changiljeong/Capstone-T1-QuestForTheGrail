package com.questforholygrail.game.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameTitlePanel extends JPanel {
    private JLabel imgLabel;
    private ImageIcon imgIcon;


    public GameTitlePanel() {
        this.imgIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/splash-Art/Grail-SplashArt.png")));
        imgLabel = new JLabel(imgIcon);
        imgLabel.setSize(968, 590);
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