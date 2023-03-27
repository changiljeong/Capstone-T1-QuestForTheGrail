package com.questforholygrail.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int health;
    private int attack;
    private final List<Item> inventory;
    private Location location;
    private boolean win;

    public Player(int health, int attack, Location location, boolean win) {
        this.health = health;
        this.inventory = new ArrayList<>();
        this.location = location;
        this.attack = attack;
        this.win = win;
    }

    public int getAttack() {
        for (Item item : inventory) {
            if (item.getName().equals("sword")) {
                attack = 30; // add 30 points to the base attack
                break;
            }
        }
        return attack;
    }

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

    @Override
    public String toString() {
        return "Player{" +
            ", health=" + health +
            ", inventory=" + inventory +
            ", location=" + location +
            '}';
    }
}


