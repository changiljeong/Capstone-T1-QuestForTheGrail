package com.questforholygrail.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int health;
    private int attack;
    private List<Item> inventory;
    private Location location;

    public Player(String name, int health, int attack, Location location) {
        this.name = name;
        this.health = health;
        this.inventory = new ArrayList<>();
        this.location = location;
        this.attack = attack;
    }

    public int getAttack() {
        for (Item item : inventory) {
            if (item.getName().equals("sword")) {
                attack = 30;
                break;
            }
        }
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getName() {
        return name;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Player{" +
            "name='" + name + '\'' +
            ", health=" + health +
            ", inventory=" + inventory +
            ", location=" + location +
            '}';
    }
}


