package com.questforholygrail.game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

    Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 10, null, false);
    }

    @Test
    void isWin_true() {
        Item item = new Item();
        item.setName("holy-grail");
        player.getInventory().add(item);
        assertTrue(player.isWin());
    }

    @Test
    void isWin_false() {
        assertFalse(player.isWin());
    }

    @Test
    void getAttack_sword() {
        Item item = new Item();
        item.setName("sword");
        player.getInventory().add(item);
        assertEquals(30, player.getAttack());
    }


    @Test
    void getAttack_noSword() {
        assertEquals(10, player.getAttack());
    }
}