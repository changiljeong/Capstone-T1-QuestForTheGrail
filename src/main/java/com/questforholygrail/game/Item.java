package com.questforholygrail.game;
import com.questforholygrail.game.ui.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;

public class Item {

  private String name;
  private Map<String, String> action;
  private String filePath;
  private transient BufferedImage image;
  private transient boolean collision = false;
  private transient int worldX, worldY;
  private transient int solidAreaDefaultX = 0;
  private transient int solidAreaDefaultY = 0;

  private transient Rectangle solidArea = new Rectangle(0, 0, 48, 48);

  public Item() {
  }

  public Item(String name, String filePath) {
    this.name = name;
    setImage(filePath);
  }

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

  public void setImage(String filePath) {
    try {
      this.image = ImageIO.read(getClass().getResourceAsStream(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  public Map<String, String> getAction() {
    return action;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  //formats item string
  @Override
  public String toString() {
    return "Item{" +
        "name='" + name + '\'' +
        ", action=" + action +
        '}';
  }
}
