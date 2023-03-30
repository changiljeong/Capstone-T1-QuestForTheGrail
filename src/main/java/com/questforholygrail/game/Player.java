package com.questforholygrail.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int health;
    private int attack;
    private final List<Item> inventory;
    private Location location;
    private boolean win;
    private Item equippedItem;

    public Player(int health, int attack, Location location, boolean win) {
        this.health = health;
        this.inventory = new ArrayList<>();
        this.location = location;
        this.attack = attack;
        this.win = win;
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


