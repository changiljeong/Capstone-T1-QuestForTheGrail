package com.questforholygrail.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NPCTest {



    @Test
    public void testNPC() {
        NPC npc = new NPC("Goblin", 75, 20);

        assertEquals("Goblin", npc.getName());
        assertEquals(75, npc.getHealth());
        assertEquals(20, npc.getAttack());
    }
}
