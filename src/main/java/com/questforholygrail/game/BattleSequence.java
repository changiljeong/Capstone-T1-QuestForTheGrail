package com.questforholygrail.game;

public class BattleSequence {
    private final Player player;
    private final NPC npc;

    public BattleSequence(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public void start() {
        System.out.println(player.getName() + " vs. " + npc.getName());
        System.out.println("Let the battle begin!");

        while (!player.isDead() && !npc.isDead()) {

            // Player's turn
            int playerAttackPower = player.attack();
            npc.takeDamage(playerAttackPower);
            System.out.println(player.getName() + " attacks " + npc.getName() + " with " + playerAttackPower + " attack power.");
            if (npc.isDead()) {
                break;
            }

            // NPC's turn
            int npcAttackPower = npc.attack();
            player.takeDamage(npcAttackPower);
            System.out.println(npc.getName() + " attacks " + player.getName() + " with " + npcAttackPower + " attack power.");
            if (player.isDead()) {
                break;
            }
        }

        if (player.isDead()) {
            System.out.println(player.getName() + " has been defeated!");
        } else if (npc.isDead()) {
            System.out.println(npc.getName() + " has been defeated!");
        }
    }
}
