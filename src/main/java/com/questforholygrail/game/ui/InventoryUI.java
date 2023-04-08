package com.questforholygrail.game.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class InventoryUI {

  GamePanel gp;
  private double playTime;
  private DecimalFormat dFormat = new DecimalFormat("#0.00");
  Font arial_40;
  Graphics2D g2;
  private int slotCol;
  private int slotRow;

  public InventoryUI(GamePanel gp) {
    this.gp = gp;
    arial_40 = new Font("Arial", Font.PLAIN, 40);
    slotRow = 0;
    slotCol = 0;
  }

  public void draw(Graphics2D g2){
    this.g2 = g2;
    g2.setFont(arial_40);
    g2.setColor(Color.white);

    if(gp.getGameState() == gp.getPlayState()){

    }
    if(gp.getGameState() == gp.getPauseState()){
      drawPauseScreen();
    }

    //Time
    playTime += (double)1/60;
    g2.drawString("Time:" + getdFormat().format(playTime), gp.getTileSize()* 10, gp.getTileSize()*1);
  }

  public void drawPauseScreen(){
    String text = "Pause";
    int x = getXforCenteredText(text);
    int y = gp.getScreenHeight()/2;
    g2.drawString(text, x, y);
  }

  public void drawInventory(Graphics2D g2){
    this.g2 = g2;
//    int frameX = gp.getTileSize()*9;
//    int frameY = gp.getTileSize()* 2;
//    int frameWidth = gp.getTileSize() * 3;
//    int frameHeight = gp.getTileSize() * 3;
    int frameX = 0;
    int frameY = 0;
    int frameWidth = 280;
    int frameHeight = 600;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

    //slot
    final int slotXstart = frameX + 20;
    final int slotYstart = frameY + 20;
    int slotX = slotXstart;
    int slotY = slotXstart;


    //Draw player's items
    for(int i = 0; i< gp.getPlayer().getInventory().size(); i++){
      gp.getPlayer().getInventory().get(i).setImage(gp.getPlayer().getInventory().get(i).getFilePath());
      g2.drawImage(gp.getPlayer().getInventory().get(i).getImage(), slotX, slotY, gp.getTileSize(), gp.getTileSize(), null);
      slotX += gp.getTileSize();
      if(i == 4 || i ==9 || i == 14){
        slotX = slotXstart;
        slotY += gp.getTileSize();
      }
    }

    //Cursor
    int cursorX = slotXstart + gp.getTileSize() * slotCol;
    int cursorY = slotYstart + gp.getTileSize() * slotRow;
    int cursorWidth = gp.getTileSize();
    int corsorHeight = gp.getTileSize();

    //Draw Cursor
    g2.setColor(Color.white);
    g2.setStroke(new BasicStroke(3));
    g2.drawRoundRect(cursorX, cursorY, cursorWidth, corsorHeight, 10, 10);

  }

  public void drawSubWindow(int x, int y, int width, int height){
    Color c = new Color(0, 0, 0, 210);
    g2.setColor(c);
    g2.fillRoundRect(x, y, width, height, 35, 35);

    c = new Color(255, 255, 255);
    g2.setColor(c);
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
  }


  public int getXforCenteredText(String text){
    int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
    int x = gp.getScreenWidth()/2 - length/2;
    return x;
  }

  public DecimalFormat getdFormat() {
    return dFormat;
  }

  public void setdFormat(DecimalFormat dFormat) {
    this.dFormat = dFormat;
  }

  public int getSlotCol() {
    return slotCol;
  }

  public void setSlotCol(int slotCol) {
    this.slotCol = slotCol;
  }

  public int getSlotRow() {
    return slotRow;
  }

  public void setSlotRow(int slotRow) {
    this.slotRow = slotRow;
  }
}
