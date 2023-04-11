package com.questforholygrail.game.ui;

import com.questforholygrail.game.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MiniMapPanel extends JPanel {
    private GamePanel gp = Main.getGameWindow().getGame();
    private boolean onUtilWidget;
    private BufferedImage background;

    public MiniMapPanel(boolean onUtilWidget) {
        this.onUtilWidget = onUtilWidget;
        loadMiniMapBackground();
    }

    public void loadMiniMapBackground() {
        try {
            background = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            "/maps/minimapBackground.png")));
            int w = 260;
            int h = 150;

            BufferedImage img =
                    new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            int x;
            int y;
            int ww = background.getWidth();
            int hh = background.getHeight();
            int[] ys = new int[h];
            for (y = 0; y < h; y++)
                ys[y] = y * hh / h;
            for (x = 0; x < w; x++) {
                int newX = x * ww / w;
                for (y = 0; y < h; y++) {
                    int col = background.getRGB(newX, ys[y]);
                    img.setRGB(x, y, col);
                }
            }
            background = img;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateUtilMap() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (onUtilWidget) {
            setPreferredSize(new Dimension(300, 200));
            setSize(300, 200);
            g2.drawImage(background, 20, 20, null);
        } else {
            setPreferredSize(new Dimension(900, 500));
            setSize(900, 500);
            gp.getTileManager().draw(g2, true, false);
        }

        gp.getPlayer().draw(g2, true, onUtilWidget);
        g2.dispose();
    }

}
