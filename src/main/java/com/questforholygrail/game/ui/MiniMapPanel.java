package com.questforholygrail.game.ui;

import com.questforholygrail.game.Main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MiniMapPanel extends JPanel {
    private GamePanel gp = Main.getGameWindow().getGame();
    private boolean onUtilWidget;
    private BufferedImage background;

    public MiniMapPanel(boolean onUtilWidget) {
        this.onUtilWidget = onUtilWidget;
        loadMiniMapBackground();
//        repaint();
    }

    public void loadMiniMapBackground(){
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

    public void updateUtilMap(){
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setPreferredSize(new Dimension(300, 200));
        setSize(300, 200);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, null);
        gp.getPlayer().draw(g2, true, onUtilWidget);
        g2.dispose();
    }

}
