//package com.questforholygrail.game;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//public class NPC {
//
//    private String name;
//    private String dialogue;
//    private List<NPC> npc;
//    private Map<String, String> action;
//    private int health;
//    private int attackPower;
//    private boolean isDead;
//
//    public NPC(String name, String dialogue, List<NPC> npc, Map<String, String> action, int health, int attackPower) {
//        this.name = name;
//        this.dialogue = dialogue;
//        this.npc = npc;
//        this.action = action;
//        this.health = 100;
//        this.attackPower = 10;
//        this.isDead = false;
//    }
//
//    public Map<String, String> getAction() {
//        return action;
//    }
//
//    public void setAction(Map<String, String> action) {
//        this.action = action;
//    }
//
//    public List<NPC> getNpc() {
//        return npc;
//    }
//
//    public void setNpc(List<NPC> npc) {
//        this.npc = npc;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDialogue() {
//        return dialogue;
//    }
//
//    public void setDialogue(String dialogue) {
//        this.dialogue = dialogue;
//    }
//
//    public int attack() {
//        // generate a random damage value between 1 and 25
//        int damage = new Random().nextInt(25) + 1;
//        return damage;
//    }
//
//    public void takeDamage(int PlayerAttack) {
//        if (PlayerAttack < 0) {
//            return;
//        }
//        health -= PlayerAttack;
//        if (health <= 0) {
//            isDead = true;
//        }
//    }
//
//    // Getters & Setters
//
//    public int getHealth() {
//        return health;
//    }
//
//    public void setHealth(int health) {
//        this.health = health;
//    }
//
//    public int getAttackPower() {
//        return attackPower;
//    }
//
//    public void setAttackPower(int attackPower) {
//        this.attackPower = attackPower;
//    }
//
//    public boolean isDead() {
//        return isDead;
//    }
//
//    public void setDead(boolean dead) {
//        isDead = dead;
//    }
//}
