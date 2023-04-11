package com.questforholygrail.game.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Furniture {
    private BufferedImage image;

    private boolean collision = true;

    private int initialX;

    private int initialY;

    private int xScaleFactor;

    private int yScaleFactor;

    private int xOffset;
    private int yOffset;

    private Rectangle solidArea;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getInitialX() {
        return initialX;
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public void setInitialY(int initialY) {
        this.initialY = initialY;
    }

    public int getxScaleFactor() {
        return xScaleFactor;
    }

    public void setxScaleFactor(int xScaleFactor) {
        this.xScaleFactor = xScaleFactor;
    }

    public int getyScaleFactor() {
        return yScaleFactor;
    }

    public void setyScaleFactor(int yScaleFactor) {
        this.yScaleFactor = yScaleFactor;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }
}
