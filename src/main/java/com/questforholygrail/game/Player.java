package com.questforholygrail.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name;
    private int health;
    private final List<Item> inventory;
    private Location location;
    private List<String> inventory1;
    private int attackPower;


    public Player(String name, int health, Location location) {
        this.name = name;
        this.health = 100;
        this.inventory = new ArrayList<>();
        this.location = location;
        this.attackPower = 25;

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

    public int attack() {
        // generate a random damage value between 1 and 25
        int damage = new Random().nextInt(25) + 1;
        return damage;
    }

    Object getWeapon() {
        return null;
    }

    public void takeDamage(int NpcAttack) {
        if (NpcAttack < 0) {
            return;
        }
        health -= NpcAttack;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }


}

