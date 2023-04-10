package com.questforholygrail.game;

import com.questforholygrail.game.ui.GamePanel;
import com.questforholygrail.game.ui.Health;
import com.questforholygrail.game.ui.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Player extends Entity{

    private int health;
    private int attack;
    private List<Item> inventory = new ArrayList<>();
    private Location location;
    private boolean win;
    private Item equippedItem;
    private boolean fighting;
    private List<BufferedImage> fightSpritesRight;
    private List<BufferedImage> fightSpritesLeft;
    private int fightSpriteNum;
    private Item pickedUpItem;
    private int pickedUpItemDisplayCounter;
    GamePanel gp;
    KeyHandler keyHandler;
    private final Health healthObject = new Health();
    private int screenX;
    private int screenY;

    private int hasPotion = 0;
    private int hasKey = 0;
    private int hasSword = 0;
    private int hasGrail = 0;


    public Player(){

    }
    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.screenX = gp.getScreenWidth()/2 -(gp.getTileSize()/2);
        this.screenY = gp.getScreenHeight()/2 - (gp.getTileSize()/2);

        Rectangle solidArea = new Rectangle();
        solidArea.setRect(8, 16, 32, 32);
        setSolidArea(solidArea);
        setSolidAreaDefaultX((int)solidArea.getX());
        setSolidAreaDefaultY((int)solidArea.getY());

        setDefaultValues();
        getPlayerImage();
//        setItems();
    }

    public Player(int health, int attack, Location location, boolean win) {
        this.health = health;
        this.inventory = new ArrayList<>();
        this.location = location;
        this.attack = attack;
        this.win = win;
    }

    public void getPlayerImage(){
        fightSpritesRight = new ArrayList<>();
        fightSpritesLeft = new ArrayList<>();
        try {
            setUp1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/back1.png"))));
            setUp2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/back2.png"))));
            setDown1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/front1.png"))));
            setDown2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/front2.png"))));
            setLeft1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/left1.png"))));
            setLeft2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/left2.png"))));
            setRight1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/right1.png"))));
            setRight2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/right2.png"))));
            fightSpritesRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/fightright1.png"))));
            fightSpritesRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/fightright3.png"))));
            fightSpritesRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/fightright6.png"))));
            fightSpritesRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/fightright7.png"))));
            fightSpritesLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/fightleft1.png"))));
            fightSpritesLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/fightleft3.png"))));
            fightSpritesLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/fightleft6.png"))));
            fightSpritesLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/nyckolle/fightleft7.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues(){
        setWorldX(gp.getTileSize()*  60);
        setWorldY(gp.getTileSize() * 45);
        setSpeed(3);
        setDirection("down");
        setSpriteNum(1);
    }

    public void update(){

        if(keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()){
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

            //check for tile collision
            gp.getcChecker().checkTile(this);

            //pick up object if object collision
            int objIndex = gp.getcChecker().checkObject(this, true);
            pickUpObject(objIndex);

            //check NPC collision
            gp.getcChecker().checkNPC(this);


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

            //Move to different items in inventory


            setSpriteCounter(getSpriteCounter()+1);

            if(getSpriteCounter() > 12) {

                if(fighting){
                    if(fightSpriteNum < fightSpritesLeft.size() - 1){
                        fightSpriteNum++;
                    } else {
                        fightSpriteNum = 0;
                    }
                }
                if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }


    }

    public void pickUpObject(int i){
        if( i != 999){

            String objectName = gp.getObj()[i].getName();
            switch(objectName){
                case "Key":
                    if(Commands.getCurrentLocation().getItems().size() > 0) {
                        inventory.add(Commands.getCurrentLocation().getItems().get(0));
                        pickedUpItem = Commands.getCurrentLocation().getItems().get(0);
                        Commands.getCurrentLocation().getItems().remove(0);
                        hasKey++;
                    }
                    gp.getObj()[i] = null;
                    break;
                case "Sword":
                    if(Commands.getCurrentLocation().getItems().size() > 0) {
                        inventory.add(Commands.getCurrentLocation().getItems().get(0));
                        pickedUpItem = Commands.getCurrentLocation().getItems().get(0);
                        Commands.getCurrentLocation().getItems().remove(0);
                    }
                    hasSword++;
                    break;
                case "Holy Grail":
                    if(Commands.getCurrentLocation().getItems().size() > 0) {
                        inventory.add(Commands.getCurrentLocation().getItems().get(0));
                        pickedUpItem = Commands.getCurrentLocation().getItems().get(0);
                        Commands.getCurrentLocation().getItems().remove(0);
                    }
                    hasGrail++;
                    break;
                case "Potion":
                    if(Commands.getCurrentLocation().getItems().size() > 0) {
                        inventory.add(Commands.getCurrentLocation().getItems().get(0));
                        pickedUpItem = Commands.getCurrentLocation().getItems().get(0);
                        Commands.getCurrentLocation().getItems().remove(0);
                        hasPotion++;
                    }
                    break;
            }
            gp.getObj()[i] = null;
        }
    }
    public void draw(Graphics2D g2, boolean onMiniMap, boolean onUtilWidget){
        BufferedImage image = null;


        if (onUtilWidget) {
            image = getDown1();
            g2.drawImage(image, (getWorldX() - 800)/(gp.getOriginalTileSize()), (getWorldY()- 500)/(gp.getOriginalTileSize()), gp.getTileSize()/2, gp.getTileSize()/2, null);
        }
        else if (onMiniMap) {
            image = getDown1();
            g2.drawImage(image, (getWorldX()+850)/(gp.getOriginalTileSize()/2), (getWorldY()+600)/(gp.getOriginalTileSize()/2), gp.getTileSize()/2, gp.getTileSize()/2, null);
        } else {
            drawHealth(g2);
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
            if(!fighting) {
                g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            } else if (direction.equalsIgnoreCase("right") || direction.equalsIgnoreCase("down")) {
                g2.drawImage(fightSpritesRight.get(fightSpriteNum), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            } else {
                g2.drawImage(fightSpritesLeft.get(fightSpriteNum), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }
        }

    }

    //draw Health
    private void drawHealth(Graphics2D g2){
        BufferedImage[] healthDisplay = healthObject.generateHealthDisplay(this);
        if(healthDisplay[9] != null) {
            for(int i = 0; i <healthDisplay.length; i++){
                g2.drawImage(healthDisplay[i], 510 + (20 * i), 10, null);
            }
        }
    }


    //sets attack based on whether player has sword in inventory
    public int getAttack() {
        if (equippedItem != null && equippedItem.getName().equalsIgnoreCase("sword")){
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

//    public void setItems(){
//
//    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
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

    public boolean isFighting() {
        return fighting;
    }

    public void setFighting(boolean fighting) {
        this.fighting = fighting;
    }

    public Item getPickedUpItem() {
        return pickedUpItem;
    }

    public void setPickedUpItem(Item pickedUpItem) {
        this.pickedUpItem = pickedUpItem;
    }

    public int getPickedUpItemDisplayCounter() {
        return pickedUpItemDisplayCounter;
    }

    public void setPickedUpItemDisplayCounter(int pickedUpItemDisplayCounter) {
        this.pickedUpItemDisplayCounter = pickedUpItemDisplayCounter;
    }

    public Health getHealthObject() {
        return healthObject;
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


