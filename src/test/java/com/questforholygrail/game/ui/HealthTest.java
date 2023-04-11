package com.questforholygrail.game.ui;

import com.questforholygrail.game.Player;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthTest {

    Player player;
    Health healthInstance;
    BufferedImage[] fullHealthHearts;
    BufferedImage[] halfHealthHearts;
    BufferedImage[] noHealthHearts;

    @BeforeEach
    public void beforeEach() {
        healthInstance = new Health();
        player = new Player();
        fullHealthHearts = new BufferedImage[]{Health.getFullHeart(), Health.getFullHeart(), Health.getFullHeart(),
                Health.getFullHeart(), Health.getFullHeart(), Health.getFullHeart(), Health.getFullHeart(),
                Health.getFullHeart(), Health.getFullHeart(), Health.getFullHeart()};
        halfHealthHearts = new BufferedImage[]{Health.getFullHeart(), Health.getFullHeart(), Health.getFullHeart(),
                Health.getFullHeart(), Health.getFullHeart(), Health.getHalfHeart(), Health.getEmptyHeart(),
                Health.getEmptyHeart(), Health.getEmptyHeart(), Health.getEmptyHeart()};
        noHealthHearts = new BufferedImage[]{Health.getEmptyHeart(), Health.getEmptyHeart(), Health.getEmptyHeart(),
                Health.getEmptyHeart(), Health.getEmptyHeart(), Health.getEmptyHeart(), Health.getEmptyHeart(),
                Health.getEmptyHeart(), Health.getEmptyHeart(), Health.getEmptyHeart()};

    }

    //test full health
    @Test
    public void testGenerateHealthDisplay_fullHealth() {
        player.setHealth(100);
        BufferedImage[] health = healthInstance.generateHealthDisplay(player);
        Assertions.assertArrayEquals(health, fullHealthHearts);
    }

    //test not full health when less than 100 health
    @Test
    public void testGenerateHealthDisplay_lowHealth() {
        player.setHealth(50);
        BufferedImage[] health = healthInstance.generateHealthDisplay(player);
        Assertions.assertFalse(Arrays.equals(health, fullHealthHearts));
    }


    //test that half hearts are applied when necessary
    @Test
    public void testGenerateHealthDisplay_halfHealth() {
        player.setHealth(55);
        BufferedImage[] health = healthInstance.generateHealthDisplay(player);
        Assertions.assertArrayEquals(health, halfHealthHearts);
    }

    //test health = 0 is displayed correctly
    @Test
    public void testGenerateHealthDisplay_zeroHealth() {
        player.setHealth(0);
        BufferedImage[] health = healthInstance.generateHealthDisplay(player);
        Assertions.assertArrayEquals(health, noHealthHearts);
    }

    //test health > 100 is displayed correctly
    @Test
    public void testGenerateHealthDisplay_extraHealth() {
        player.setHealth(150);
        BufferedImage[] health = healthInstance.generateHealthDisplay(player);
        Assertions.assertArrayEquals(health, fullHealthHearts);
    }

    //test health < 0 is displayed correctly
    @Test
    public void testGenerateHealthDisplay_negativeHealth() {
        player.setHealth(-100);
        BufferedImage[] health = healthInstance.generateHealthDisplay(player);
        Assertions.assertArrayEquals(health, noHealthHearts);
    }

}
