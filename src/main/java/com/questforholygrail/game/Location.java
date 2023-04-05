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
    private int maxY;
    private int maxX;
    private int minY;
    private int minX;

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

    public void removeNPC(NPC defeatedNPC) {
        npc.remove(defeatedNPC);
    }

    public void setDirections(Map<String, String> expectedDirections) {
        this.directions = expectedDirections;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }
}
