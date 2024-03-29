package com.questforholygrail.game.ui;

import com.questforholygrail.game.Main;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private GamePanel gp;
    private boolean onUtilWidget;
    KeyHandler keyHandler;


    public InventoryPanel(boolean onUtilWidget) {
        this.onUtilWidget = onUtilWidget;
        gp = Main.getGameWindow().getGame();
        this.keyHandler = gp.getKeyHandler();
    }

    public void updateUtilInventory() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setPreferredSize(new Dimension(300, 260));
        setSize(300, 260);
        gp.getUi().drawInventory(g2);
        g2.dispose();
    }

}
