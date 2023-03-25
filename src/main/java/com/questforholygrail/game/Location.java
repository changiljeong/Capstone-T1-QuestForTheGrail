package com.questforholygrail.game;

import java.util.List;
import java.util.Map;

public class Location {
    private String name;
    private String description;
    private Map<String, String> directions;
    private List<Item> items;
    private List<NPC> npc;
    private Map<String, String> riddles;
    private boolean puzzle;
    boolean locked;
    boolean battle;

    public boolean isBattle() {
        return battle;
    }

    public void setBattle(boolean battle) {
        this.battle = battle;
    }

    public List<NPC> getNpc() {
        return npc;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Map<String, String> getRiddles() {
        return riddles;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isPuzzle() {
        return puzzle;
    }

    public void setPuzzle(boolean puzzle) {
        this.puzzle = puzzle;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

}
