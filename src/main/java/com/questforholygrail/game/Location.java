package com.questforholygrail.game;

import java.util.List;
import java.util.Map;

public class Location {
    private String name;
    private String description;
    private Map<String, String> directions;
    private List<Item> items;
    private List<NPC> npc;
    boolean locked;

    public List<NPC> getNpc() {
        return npc;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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

    public Map<String, String> getDirections() {
        return directions;
    }

}

