package com.questforholygrail.game;

import java.util.List;
import java.util.Map;

public class NPC {

    private String name;
    private String dialogue;
    private List<NPC> npc;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public List<NPC> getNpc() {
        return npc;
    }

    public void setNpc(List<NPC> npc) {
        this.npc = npc;
    }

    public Map<String, String> getAction() {
        return action;
    }

    public void setAction(Map<String, String> action) {
        this.action = action;
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

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
