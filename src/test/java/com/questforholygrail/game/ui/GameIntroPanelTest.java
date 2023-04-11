package com.questforholygrail.game.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GameIntroPanelTest {

    GameIntroPanel gip;
    JLabel instructionArtImageLabel;
    ImageIcon instructionArtImageIcon;

    public final ImageIcon TEST_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/splash-Art/Instructions-SplashArt.png")));
    public final ImageIcon NEW_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/splash-Art/Grail-SplashArt.png")));
    public final JLabel TEST_LABEL = new JLabel(TEST_ICON);
    public final JLabel NEW_LABEL = new JLabel(NEW_ICON);


    @BeforeEach
    public void beforeEach() {
        gip = new GameIntroPanel();
        instructionArtImageIcon = TEST_ICON;
        instructionArtImageLabel = TEST_LABEL;
        instructionArtImageLabel.setSize(968, 567);
        gip.add(instructionArtImageLabel);
    }

    @Test
    void testGetImgLabel_notNewLabel() {
        assertNotEquals(gip.getImgLabel(), NEW_LABEL);
    }

    @Test
    void testSetImgLabel_newLabel() {
        gip.setImgLabel(NEW_LABEL);
        assertEquals(gip.getImgLabel(), NEW_LABEL);
    }

    @Test
    void testGetImgIcon_notNewIcon() {
        assertNotEquals(gip.getImgIcon(), NEW_ICON);
    }

    @Test
    void testSetImgIcon_newIcon() {
        gip.setImgIcon(NEW_ICON);
        assertEquals(gip.getImgIcon(), NEW_ICON);
    }
}