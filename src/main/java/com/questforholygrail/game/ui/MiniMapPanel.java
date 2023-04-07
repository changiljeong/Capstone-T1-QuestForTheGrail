package com.questforholygrail.game.ui;

import com.questforholygrail.game.Main;

import javax.swing.*;
import java.awt.*;

public class MiniMapPanel extends JPanel {
    private GamePanel gp = Main.getGameWindow().getGame();

    public MiniMapPanel() {
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        gp.getTileManager().draw(g2, true);
        gp.getPlayer().draw(g2, true);
        g2.dispose();
    }
}
