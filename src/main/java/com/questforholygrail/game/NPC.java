package com.questforholygrail.game;
import java.util.Map;
import java.util.Random;
public class NPC {
    private String name;
    private String description;
    private String tip;
    Map<String, String> action;
    private int health;
    private boolean isDead;
    public NPC() {
        this.health = 75;
        isDead = false;
    }
    public Map<String, String> getAction() {
        return action;
    }
    public void setAction(Map<String, String> action) {
        this.action = action;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int attack() {
        // generate a random damage value between 1 and 25
        int damage = new Random().nextInt(25) + 1;
        return damage;
    }
    public void takeDamage(int PlayerAttack) {
       if (health < 0) {           System.out.println("reached the if health is negative 0 statement of NPC");           return;        }        health -= PlayerAttack;
        System.out.println("Remaining NPC health: " + health);
    }
    // Getters & Setters
    public int getHealth() {
        return health;
}
    public void setHealth(int health) {
        this.health = health;
    }
    public boolean isDead() {
        return isDead;
    }
    public void setDead(boolean dead) {
        isDead = dead;
    }
}
