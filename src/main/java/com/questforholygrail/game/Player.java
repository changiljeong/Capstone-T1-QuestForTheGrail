package com.questforholygrail.game;

import com.questforholygrail.game.UI.GamePanel;
import com.questforholygrail.game.UI.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Player extends Entity{

    private int health;
    private int attack;
    private List<Item> inventory;
    private Location location;
    private boolean win;
    private Item equippedItem;
    GamePanel gp;
    KeyHandler keyHandler;

    private int screenX;
    private int screenY;


    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.screenX = gp.getScreenWidth()/2 -(gp.getTileSize()/2);
        this.screenY = gp.getScreenHeight()/2 - (gp.getTileSize()/2);

        Rectangle solidArea = new Rectangle();
        solidArea.setRect(8, 16, 32, 32);
        setSolidArea(solidArea);

        setDefaultValues();
        getPlayerImage();
    }

    public Player(int health, int attack, Location location, boolean win) {
        this.health = health;
        this.inventory = new ArrayList<>();
        this.location = location;
        this.attack = attack;
        this.win = win;
    }

    public void getPlayerImage(){
        try {
            setUp1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pink-soldier/pink-soldier-back1.png"))));
            setUp2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pink-soldier/pink-soldier-back2.png"))));
            setDown1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pink-soldier/pink-soldier-front1.png"))));
            setDown2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pink-soldier/pink-soldier-front2.png"))));
            setLeft1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pink-soldier/pink-soldier-left1.png"))));
            setLeft2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pink-soldier/pink-soldier-left2.png"))));
            setRight1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pink-soldier/pink-soldier-right1.png"))));
            setRight2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pink-soldier/pink-soldier-right2.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues(){
        setWorldX(gp.getTileSize()*8);
        setWorldY(gp.getTileSize()*26);
        setSpeed(4);
        setDirection("down");
        setSpriteNum(1);
    }

    public void update(){

        if(keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()) {

            if(keyHandler.isUpPressed()){
                setDirection("up");
            } else if(keyHandler.isDownPressed()){
                setDirection("down");
            } else if (keyHandler.isLeftPressed()) {
                setDirection("left");
            } else if (keyHandler.isRightPressed()){
                setDirection("right");
            }

            setCollisionOn(false);
            gp.getcChecker().checkTile(this);

            //If collision is false; player can move
            if(isCollisionOn() == false){
                switch (getDirection()){
                    case "up":
                        setWorldY(getWorldY()-getSpeed());
                        break;
                    case "down":
                        setWorldY(getWorldY()+getSpeed());
                        break;
                    case "left":
                        setWorldX(getWorldX()-getSpeed());
                        break;
                    case "right":
                        setWorldX(getWorldX()+getSpeed());
                        break;
                }
            }

            setCollisionOn(false);
            gp.getcChecker().checkTile(this);

            //If collision is false; player can move
            if(isCollisionOn() == false){
                switch (getDirection()){
                    case "up":
                        setWorldY(getWorldY()-getSpeed());
                        break;
                    case "down":
                        setWorldY(getWorldY()+getSpeed());
                        break;
                    case "left":
                        setWorldX(getWorldX()-getSpeed());
                        break;
                    case "right":
                        setWorldX(getWorldX()+getSpeed());
                        break;
                }
            }

            setSpriteCounter(getSpriteCounter()+1);

            if(getSpriteCounter() > 12) {
                if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }


    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        String direction = getDirection();

        switch (direction){
            case "up":
                if(getSpriteNum() == 1) {
                    image = getUp1();
                }
                if(getSpriteNum() == 2) {
                    image = getUp2();
                }
                break;
            case "down":
                if(getSpriteNum() == 1) {
                    image = getDown1();
                }
                if(getSpriteNum() == 2) {
                    image = getDown2();
                }
                break;
            case "left":
                if(getSpriteNum() == 1) {
                    image = getLeft1();
                }
                if(getSpriteNum() == 2) {
                    image = getLeft2();
                }
                break;
            case "right":
                if(getSpriteNum() == 1) {
                    image = getRight1();
                }
                if(getSpriteNum() == 2) {
                    image = getRight2();
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);

    }

    //sets attack based on whether player has sword in inventory
    public int getAttack() {
        if (equippedItem.getName().equalsIgnoreCase("sword")){
            attack = 30;
        }
        return attack;
    }

    //defines requirements to win
    public boolean isWin() {
        for (Item item : inventory) {
            if (item.getName().equals("holy-grail")) {
                win = true;
                break;
            }
        }
        return win;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        //stores currently equipped item in inventory, if applicable
        if(this.equippedItem != null) {
            inventory.add(this.equippedItem);
        }
        //equips item
        this.equippedItem = equippedItem;
        //removes equipped item from inventory
        inventory.remove(equippedItem);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
    //formats player info string
    @Override
    public String toString() {
        return "Player{" +
            ", health=" + health +
            ", inventory=" + inventory +
            ", location=" + location +
            '}';
    }
}


