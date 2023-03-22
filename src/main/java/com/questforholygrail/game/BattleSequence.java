package com.questforholygrail.game;

public class BattleSequence {
    private final Player player;
    private final NPC npc;
    private static Location currentLocation;

    public BattleSequence(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;

    }

    public void start() {
        System.out.println(player.getName() + " vs. " + npc.getName());
        System.out.println("Let the battle begin!");

        int playerHealth = player.getHealth();
        int NPCHealth = npc.getHealth();

        while (playerHealth > 0) {

            // Player's turn
            int playerAttackPower = player.attack();
            npc.takeDamage(playerAttackPower);
            System.out.println(player.getName() + " attacks " + npc.getName() + " with " + playerAttackPower + " attack power.");

            if (playerHealth <= 0) {
                playerHealth = 100;
                NPCHealth = 100;
                for (Location location : Main.locations) {
                    if (location.getName().equals("The Gate of Trials")) {
                        currentLocation = location;
                        player.setLocation(currentLocation);
                        break;
                    }


                    // NPC's turn
                    int npcAttackPower = npc.attack();
                    player.takeDamage(npcAttackPower);
                    System.out.println(npc.getName() + " attacks " + player.getName() + " with " + npcAttackPower + " attack power.");

                }
            }
        }
    }
}
