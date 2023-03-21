package com.questforholygrail.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;



    private List<Item> inventory;
    private Location location;
    private List<String> inventory1;

    public Player(String name, int health, Location location) {
        this.name = name;
        this.health = health;
        this.inventory = new ArrayList<>();
        this.location = location;
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
            ", inventory1=" + inventory1 +
            '}';
    }
}


