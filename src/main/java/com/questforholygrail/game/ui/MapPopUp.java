package com.questforholygrail.game.ui;

import javax.swing.*;
import java.awt.*;

public class MapPopUp extends JOptionPane {

    private MainGameWindow frame;

    public MapPopUp(MainGameWindow frame) {
        this.frame = frame;
    }

    public void showMiniMap() {
        MiniMapPanel mmp = new MiniMapPanel(false);
        mmp.setPreferredSize(new Dimension(900, 500));
        mmp.setSize(900, 500);
        showMessageDialog(frame, mmp, "Minimap", JOptionPane.PLAIN_MESSAGE);
    }

    public MainGameWindow getFrame() {
        return frame;
    }

    public void setFrame(MainGameWindow frame) {
        this.frame = frame;
    }
}
