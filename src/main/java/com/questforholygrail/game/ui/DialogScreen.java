package com.questforholygrail.game.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

//this was created with the help of a YouTube tutorial series by RyiSnow

public class DialogScreen {

  private GamePanel gp;
  private Graphics2D g2;
  private String currentDialog = "";


  public DialogScreen(GamePanel gp, Graphics2D g2) {
    this.gp = gp;
    this.g2 = g2;
  }


  public void drawDialogBox(boolean longDialog) {

    float textSize = (longDialog ? 22F : 24F);
    int spaceSize = (longDialog ? 30 : 40);
    //Window
    int x = gp.getTileSize() * 2;
    int y = gp.getTileSize() / 2;
    int width = gp.getScreenWidth() - (gp.getTileSize() * 4);
    int height = gp.getTileSize() * 4;
    if (currentDialog != null) {
      drawSubWindow(x, y, width, height);

      g2.setFont(g2.getFont().deriveFont(Font.PLAIN, textSize));
      x += gp.getTileSize();
      y += gp.getTileSize();

      for (String line : currentDialog.split("\n")) {
        g2.drawString(line, x, y);
        y += spaceSize;
      }
    }


  }

  public void drawSubWindow(int x, int y, int width, int height) {
    Color c = new Color(0, 0, 0, 200);
    g2.setColor(c);
    g2.fillRoundRect(x, y, width, height, 35, 35);

    c = new Color(255, 255, 255);
    g2.setColor(c);
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
  }

  public GamePanel getGp() {
    return gp;
  }

  public void setGp(GamePanel gp) {
    this.gp = gp;
  }

  public Graphics2D getG2() {
    return g2;
  }

  public void setG2(Graphics2D g2) {
    this.g2 = g2;
  }

  public String getCurrentDialog() {
    return currentDialog;
  }

  public void setCurrentDialog(String currentDialog) {
    this.currentDialog = currentDialog;
  }
}
