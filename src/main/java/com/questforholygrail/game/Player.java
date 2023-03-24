package com.questforholygrail.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name;
    private int health;
    private final List<Item> inventory;
    private Location location;
    private int attackPower;
    private boolean isDead;

    public Player(String name, int health, Location location) {
        this.name = name;
        this.health = health;
        this.inventory = new ArrayList<>();
        this.location = location;
        this.attackPower = 25;
        this.isDead = false;
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

    public void takeDamage(int npcAttack) {
        health -= npcAttack;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}

