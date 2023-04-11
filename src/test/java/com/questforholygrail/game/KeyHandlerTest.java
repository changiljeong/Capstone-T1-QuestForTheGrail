package com.questforholygrail.game;

import com.questforholygrail.game.ui.GamePanel;
import com.questforholygrail.game.ui.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeyHandlerTest {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    @BeforeEach
    void setUp() {
        gamePanel = new GamePanel();
        keyHandler = new KeyHandler(gamePanel);
    }

    @Test
    void testUpKeyPressed() {
        KeyEvent event = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.isUpPressed());
    }

    @Test
    void testDownKeyPressed() {
        KeyEvent event = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.isDownPressed());
    }

    @Test
    void testLeftKeyPressed() {
        KeyEvent event = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.isLeftPressed());
    }

    @Test
    void testRightKeyPressed() {
        KeyEvent event = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.isRightPressed());
    }

    @Test
    void testAttackKeyPressed() {
        KeyEvent event = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_A, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.isAttack());
    }

    @Test
    void testHealKeyPressed() {
        KeyEvent event = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_H, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.isHeal());
    }

    @Test
    void testTalkKeyPressed() {
        KeyEvent event = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_T, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.isTalk());
    }

    @Test
    void testExamineKeyPressed() {
        KeyEvent event = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_E, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.isExamine());
    }


    @Test
    void testIPressed() {
        KeyEvent event = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_I, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertEquals(0, gamePanel.getUi().getSlotRow());

        gamePanel.getUi().setSlotRow(2);
        keyHandler.keyPressed(event);
        assertEquals(1, gamePanel.getUi().getSlotRow());
    }
}