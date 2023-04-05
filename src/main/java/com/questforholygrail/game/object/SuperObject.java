package com.questforholygrail.game.object;

import com.questforholygrail.game.UI.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SuperObject {
  private BufferedImage image;
  private String name;
  private boolean collision = false;
  private int worldX, worldY;

  private int solidAreaDefaultX = 0;
  private int solidAreaDefaultY = 0;

  private Rectangle solidArea = new Rectangle(0, 0, 48, 48);

  public void draw(Graphics2D g2, GamePanel gp){

    int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
    int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

    if(worldX + gp.getTileSize()> gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
        worldX - gp.getTileSize()< gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
        worldY + gp.getTileSize()> gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
        worldY - gp.getTileSize()< gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()){
      g2.drawImage(getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
    }
  }


  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCollision(boolean collision) {
    this.collision = collision;
  }

  public void setWorldX(int worldX) {
    this.worldX = worldX;
  }

  public void setWorldY(int worldY) {
    this.worldY = worldY;
  }

  public BufferedImage getImage() {
    return image;
  }

  public String getName() {
    return name;
  }

  public boolean isCollision() {
    return collision;
  }

  public int getWorldX() {
    return worldX;
  }

  public int getWorldY() {
    return worldY;
  }

  public Rectangle getSolidArea() {
    return solidArea;
  }

  public int getSolidAreaDefaultX() {
    return solidAreaDefaultX;
  }

  public void setSolidAreaDefaultX(int solidAreaDefaultX) {
    this.solidAreaDefaultX = solidAreaDefaultX;
  }

  public int getSolidAreaDefaultY() {
    return solidAreaDefaultY;
  }

  public void setSolidAreaDefaultY(int solidAreaDefaultY) {
    this.solidAreaDefaultY = solidAreaDefaultY;
  }

  public void setSolidArea(Rectangle solidArea) {
    this.solidArea = solidArea;
  }
}
