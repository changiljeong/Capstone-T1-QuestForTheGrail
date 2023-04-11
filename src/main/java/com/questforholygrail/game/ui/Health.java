package com.questforholygrail.game.ui;

import com.questforholygrail.game.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Health {

    private static BufferedImage emptyHeart;
    private static BufferedImage halfHeart;
    private static BufferedImage fullHeart;
    private int displayHealingCounter;

    public Health() {
        loadHearts();
    }

    private void loadHearts() {
        try {
            emptyHeart = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            "/tiles/health/emptyHeart.png")));
            halfHeart = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            "/tiles/health/halfHeart.png")));
            fullHeart = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            "/tiles/health/fullHeart.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage[] generateHealthDisplay(Player player) {
        BufferedImage[] health = new BufferedImage[10];
        int playerHealth = player.getHealth();

        for (int i = 0; i < health.length; i++) {
            if (playerHealth - 10 >= 0) {
                health[i] = fullHeart;
            } else if (playerHealth - 10 > -10) {
                health[i] = halfHeart;
            } else {
                health[i] = emptyHeart;
            }
            playerHealth -= 10;
        }
        return health;
    }

    public int getDisplayHealingCounter() {
        return displayHealingCounter;
    }

    public void setDisplayHealingCounter(int displayHealingCounter) {
        this.displayHealingCounter = displayHealingCounter;
    }

    public static BufferedImage getEmptyHeart() {
        return emptyHeart;
    }

    public static void setEmptyHeart(BufferedImage emptyHeart) {
        Health.emptyHeart = emptyHeart;
    }

    public static BufferedImage getHalfHeart() {
        return halfHeart;
    }

    public static void setHalfHeart(BufferedImage halfHeart) {
        Health.halfHeart = halfHeart;
    }

    public static BufferedImage getFullHeart() {
        return fullHeart;
    }

    public static void setFullHeart(BufferedImage fullHeart) {
        Health.fullHeart = fullHeart;
    }
}