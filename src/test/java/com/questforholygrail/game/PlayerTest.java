package com.questforholygrail.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

    Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Player", 100, 10, null, false);
    }

    @Test
    void isWin_true() {
        Item item = new Item();
        item.setName("grotto-key");
        player.getInventory().add(item);
        assertTrue(player.isWin());
    }

    @Test
    void isWin_false() {
        assertFalse(player.isWin());
    }

}