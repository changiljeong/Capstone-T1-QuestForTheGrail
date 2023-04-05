package com.questforholygrail.game;

import com.questforholygrail.game.UI.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import javax.imageio.ImageIO;

public class NPC extends Entity {

  private String name;
  private Map<String, String> action;
  private int health;
  private int attack;
  private int initialX;
  private int initialY;
  private List<String> idleFilepaths;
  private double imageScaleX;
  private double imageScaleY;


  public NPC(String name, int health, int attack) {
    this.name = name;
    this.health = health;
    this.attack = attack;
  }

  public void getImages() {
    if (idleFilepaths == null) {
      System.out.println(this.name);
    } else {
      try {
        for (String file : idleFilepaths) {
          if (file.contains("front1")) {
            setDown1(ImageIO.read(Objects.requireNonNull(
                getClass().getResourceAsStream(file))));
          } else if (file.contains("front2")) {
            setDown2(ImageIO.read(Objects.requireNonNull(
                getClass().getResourceAsStream(file))));
          } else if (file.contains("right1")) {
            setRight1(ImageIO.read(Objects.requireNonNull(
                getClass().getResourceAsStream(file))));
          } else if (file.contains("right2")) {
            setRight2(ImageIO.read(Objects.requireNonNull(
                getClass().getResourceAsStream(file))));
          } else if (file.contains("left1")) {
            setLeft1(ImageIO.read(Objects.requireNonNull(
                getClass().getResourceAsStream(file))));
          } else if (file.contains("left2")) {
            setLeft2(ImageIO.read(Objects.requireNonNull(
                getClass().getResourceAsStream(file))));
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }

  public void update() {

    Random random = new Random();
    int randomInt = random.nextInt();

    if(randomInt % 2 == 0){
      setSpriteCounter(getSpriteCounter() + 1);
    }

    if (getSpriteCounter() > 12) {
      if (getSpriteNum() == 1) {
        setSpriteNum(2);
      } else if (getSpriteNum() == 2) {
        setSpriteNum(1);
      }
      setSpriteCounter(0);
    }
  }

  public void draw(Graphics2D g2, GamePanel gp) {
    int screenX = getWorldX() - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
    int screenY = getWorldY() - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

    update();

    BufferedImage image = null;
    String direction = getDirection();
    if (getSpriteNum() == 1 & getDirection().equalsIgnoreCase("left")) {
      image = getLeft1();
    } else if (getSpriteNum() == 2 & getDirection().equalsIgnoreCase("left")) {
      image = getLeft1();
    } else if (getSpriteNum() == 1 & getDirection().equalsIgnoreCase("right")) {
      image = getRight1();
    } else {
      image = getRight2();
    }

    g2.drawImage(image, screenX, screenY, (int)(gp.getTileSize()*imageScaleX), (int)(gp.getTileSize()*imageScaleY), null);

  }

  public int getInitialX() {
    return initialX;
  }

  public int getInitialY() {
    return initialY;
  }

    public String getName() {
        return name;
    }

  public Map<String, String> getAction() {
    return action;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getAttack() {
    return attack;
  }

  public double getImageScaleX() {
    return imageScaleX;
  }

  public void setImageScaleX(double imageScaleX) {
    this.imageScaleX = imageScaleX;
  }

  public double getImageScaleY() {
    return imageScaleY;
  }

  public void setImageScaleY(double imageScaleY) {
    this.imageScaleY = imageScaleY;
  }
}
