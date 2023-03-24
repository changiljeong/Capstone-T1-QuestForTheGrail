//package com.questforholygrail.game;
//
//
//public class BattleSequence {
//    private final Player player;
//    private final NPC npc;
//
//
//    public BattleSequence(Player player, NPC npc) {
//        this.player = player;
//        this.npc = npc;
//    }
//
//    public void start() {
//        Location currentLocation = Main.locations[0];
//        System.out.println(player.getName() + " vs. " + npc.getName());
//        System.out.println("Let the battle begin!");
//
//        int playerHealth = player.getHealth();
//        int npcHealth = npc.getHealth();
//
//        while (playerHealth >= 0 && npcHealth >= 0) {
//            // Player's turn
//            int playerAttackPower = player.attack();
//            npc.takeDamage(playerAttackPower);
//            System.out.println(player.getName() + " attacks " + npc.getName() + " with " + playerAttackPower + " attack power.");
//
//            // NPC's turn
//            int npcAttackPower = npc.attack();
//            player.takeDamage(npcAttackPower);
//            System.out.println(npc.getName() + " attacks " + player.getName() + " with " + npcAttackPower + " attack power.");
//
//            // Check if NPC is defeated
//            if (npcHealth < 0) {
//                System.out.println(npc.getName() + " has been defeated!");
//                npc.setDead(true);
//                break;
//            }
//
//            // Check if player is defeated
//            playerHealth = player.getHealth();
//            if (playerHealth < 0) {
//                System.out.println(player.getName() + " has been defeated!");
//                System.out.println(player.getName() + " sorry you lost");
//                player.setDead(true);
//                break;
//            }
//        }
//    }
//}




package com.questforholygrail.game;


public class BattleSequence {
    private final Player player;
    private final NPC npc;

    public BattleSequence(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public void start() throws InterruptedException {
        System.out.println(player.getName() + " vs. " + npc.getName());
        System.out.println("Let the battle begin!");

        int playerHealth = player.getHealth();
        int npcHealth = npc.getHealth();

        while (playerHealth > 0 && npcHealth > 0) {
            // Player's turn
            int playerAttackPower = player.attack();
            npc.takeDamage(playerAttackPower);
            System.out.println(player.getName() + " attacks " + npc.getName() + " with " + playerAttackPower + " attack power.");
            System.out.println(npc.getName() + " remaining health: " + npc.getHealth());

            Thread.sleep(500);

            // Check if NPC is defeated
            if (npc.getHealth() <= 0) {
                System.out.println(npc.getName() + " has been defeated!");
                npc.setDead(true);
                break; // terminate battle sequence
            }

            // NPC's turn
            int npcAttackPower = npc.attack();
            player.takeDamage(npcAttackPower);
            System.out.println(npc.getName() + " attacks " + player.getName() + " with " + npcAttackPower + " attack power.");
            System.out.println(player.getName() + " remaining health: " + player.getHealth());

            Thread.sleep(500);

            // Check if player is defeated
            if (player.getHealth() <= 0) {
                System.out.println(player.getName() + " has been defeated!");
                System.out.println(player.getName() + " sorry you lost");
                player.setDead(true);
                break; // terminate battle sequence
            }
            if (npcHealth <= 0) {
                System.out.println(npc.getName() + " has been defeated!");
                npc.setDead(true);
                player.getLocation().removeNPC(npc);
            }

            // Update health variables
            playerHealth = player.getHealth();
            npcHealth = npc.getHealth();
        }
    }
}