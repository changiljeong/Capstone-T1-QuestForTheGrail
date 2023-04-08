package com.questforholygrail.game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

  private transient int worldX;
  private transient int worldY;
  private transient int speed;
  private transient String direction;

  private transient BufferedImage up1;
  private transient BufferedImage up2;
  private transient BufferedImage down1;
  private transient BufferedImage down2;
  private transient BufferedImage left1;
  private transient BufferedImage left2;
  private transient BufferedImage right1;
  private transient BufferedImage right2;

  private transient int spriteCounter;
  private transient int spriteNum;

  private transient Rectangle solidArea;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  private String description;

  private transient int solidAreaDefaultX, getSolidAreaDefaultY;
  private boolean collisionOn = false;

  public int getWorldX() {
    return worldX;
  }

  public void setWorldX(int worldX) {
    this.worldX = worldX;
  }

  public int getWorldY() {
    return worldY;
  }

  public void setWorldY(int worldY) {
    this.worldY = worldY;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public BufferedImage getUp1() {
    return up1;
  }

  public void setUp1(BufferedImage up1) {
    this.up1 = up1;
  }

  public BufferedImage getUp2() {
    return up2;
  }

  public void setUp2(BufferedImage up2) {
    this.up2 = up2;
  }

  public BufferedImage getDown1() {
    return down1;
  }

  public void setDown1(BufferedImage down1) {
    this.down1 = down1;
  }

  public BufferedImage getDown2() {
    return down2;
  }

  public void setDown2(BufferedImage down2) {
    this.down2 = down2;
  }

  public BufferedImage getLeft1() {
    return left1;
  }

  public void setLeft1(BufferedImage left1) {
    this.left1 = left1;
  }

  public BufferedImage getLeft2() {
    return left2;
  }

  public void setLeft2(BufferedImage left2) {
    this.left2 = left2;
  }

  public BufferedImage getRight1() {
    return right1;
  }

  public void setRight1(BufferedImage right1) {
    this.right1 = right1;
  }

  public BufferedImage getRight2() {
    return right2;
  }

  public void setRight2(BufferedImage right2) {
    this.right2 = right2;
  }

  public int getSpriteCounter() {
    return spriteCounter;
  }

  public void setSpriteCounter(int spriteCounter) {
    this.spriteCounter = spriteCounter;
  }

  public int getSpriteNum() {
    return spriteNum;
  }

  public void setSpriteNum(int spriteNum) {
    this.spriteNum = spriteNum;
  }

  public Rectangle getSolidArea() {
    return solidArea;
  }

  public void setSolidArea(Rectangle solidArea) {
    this.solidArea = solidArea;
  }

  public boolean isCollisionOn() {
    return collisionOn;
  }

  public void setCollisionOn(boolean collisionOn) {
    this.collisionOn = collisionOn;
  }

  public int getSolidAreaDefaultX() {
    return solidAreaDefaultX;
  }

  public void setSolidAreaDefaultX(int solidAreaDefaultX) {
    this.solidAreaDefaultX = solidAreaDefaultX;
  }

  public int getSolidAreaDefaultY() {
    return getSolidAreaDefaultY;
  }

  public void setSolidAreaDefaultY(int getSolidAreaDefaultY) {
    this.getSolidAreaDefaultY = getSolidAreaDefaultY;
  }
}
