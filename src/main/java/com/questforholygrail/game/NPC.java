package com.questforholygrail.game;

import java.util.List;
import java.util.Map;

public class NPC {

    private String name;
    private Map<String, String> action;
    private int health;
    private int attack;

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
