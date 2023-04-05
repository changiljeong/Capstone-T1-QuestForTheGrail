package com.questforholygrail.game;

import java.util.List;
import java.util.Map;

public class NPC extends Entity{

    private String name;
    private Map<String, String> action;
    private int health;
    private int attack;

    public NPC(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAction() {
        return action;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

}
