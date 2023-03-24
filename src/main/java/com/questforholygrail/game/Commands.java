package com.questforholygrail.game;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Commands {

    private static Player player;
    static Location currentLocation;

    public Commands(Player player, Location currentLocation) {
        Commands.player = player;
        Commands.currentLocation = currentLocation;
    }

    public void parseCommand(String command) {

        // adjust regex for one or more white spaces \s+
        // line 22 command.trim().split("\\s+")
        String[] words = command.trim().split("\\s+");
        String verb = words[0].toLowerCase();
        String noun = "";

        if (words.length > 1) {
            noun = words[1].toLowerCase();
        }

        switch (verb) {
            case "look":
                List<Item> itemList = currentLocation.getItems();
                List<NPC> npcList = currentLocation.getNpc();
                List<Item> inventory2 = player.getInventory();
                if (noun.equals("")) {
                    System.out.println("Look at what?");
                    break;
                }
                boolean itemFound = false;
                for (Item item : itemList) {
                    if (item.getName().equalsIgnoreCase(noun)) {
                        System.out.println(item.getAction().get("look"));
                        itemFound = true;
                        break;
                    }
                }
                for (Item item : inventory2) {
                    if (item.getName().equalsIgnoreCase(noun)) {
                        System.out.println(item.getAction().get("look"));
                        itemFound = true;
                        break;
                    }
                }
                if (!itemFound) {
                    boolean npcFound = false;
                    for (NPC npc : npcList) {
                        if (npc.getName().equalsIgnoreCase(noun)) {
                            System.out.println(npc.getAction().get("look"));
                            npcFound = true;
                            break;
                        }
                    }
                    if (!npcFound) {
                        System.out.println("There is no " + noun + ".");
                    }
                }
                break;

            case "go":
                // handle "go" command
                if (noun.equals("")) {
                    System.out.println("Go where?");
                } else {
                    Map<String, String> directions = currentLocation.getDirections();
                    if (directions.containsKey(noun)) {

                        String nextLocationName = directions.get(noun);
                        for (Location location : Main.locations) {
                            if (location.getName().equals(nextLocationName)) {
                                if (location.isLocked()) {
                                    List<Item> inventory = player.getInventory();
                                    boolean hasKey = false;
                                    for (Item item : inventory) {
                                        if (item.getName().equals("key")) {
                                            hasKey = true;
                                            System.out.println("The door unlocked!");
                                            inventory.remove(item);
                                            break;
                                        }
                                    }
                                    if (!hasKey) {
                                        System.out.println("This door is locked...");
                                        break;
                                    }
                                }
                                currentLocation = location;
                                player.setLocation(currentLocation);
                                currentLocation.setLocked(false);
                            }
                        }
                    } else {
                        System.out.println("You can't go in that direction.");
                    }
                }
                break;

            case "get":
                // handle get command
                List<Item> roomItem = currentLocation.getItems();
                List<Item> myInventory = player.getInventory();
                if (noun.equals("")) {
                    System.out.println("Get what?");
                } else {
                    boolean itemFound1 = false;
                    for (Item element : roomItem) {
                        if (element.getName().equalsIgnoreCase(noun)) {
                            itemFound1 = true;
                            myInventory.add(element);
                            roomItem.remove(element);
                            System.out.println(element.getAction().get("get"));
                            break;
                        }
                    }
                    if (!itemFound1) {
                        System.out.println("There is no " + noun + ".");
                    }
                }
                break;

            case "drop":
                // handle drop command
                List<Item> myInventory1 = player.getInventory();
                List<Item> room = currentLocation.getItems();
                if (noun.equals("")) {
                    System.out.println("Drop what?");
                } else {
                    boolean itemFound2 = false;
                    for (Item inventory1 : myInventory1) {
                        if (inventory1.getName().equalsIgnoreCase(noun)) {
                            itemFound2 = true;
                            myInventory1.remove(inventory1);
                            room.add(inventory1);
                            System.out.println(inventory1.getAction().get("drop"));
                            break;
                        }
                    }
                    if (!itemFound2) {
                        System.out.println("You can't drop that.");
                    }
                }
                break;

            case "use":
                // handle use command for potion
                List<Item> myInventory2 = player.getInventory();
                int health = player.getHealth();
                if (noun.equals("")) {
                    System.out.println("Use what?");
                } else {
                    boolean potionFound = false;
                    for (Item item : myInventory2) {
                        if (item.getName().equalsIgnoreCase("potion")) {
                            potionFound = true;
                            health += 50;
                            player.setHealth(health);
                            myInventory2.remove(item);
                            System.out.println(item.getAction().get("use"));
                            break;
                        }
                    }
                    if (!potionFound) {
                        System.out.println("You can only use potions.");
                    }
                }
                break;

            case "talk":
                // handle talk command
                List<NPC> npc = currentLocation.getNpc();
                if (noun.equals("")) {
                    System.out.println("Talk to who?");
                } else {
                    boolean npcFound = false;
                    for (NPC element : npc) {
                        if (element.getName().equalsIgnoreCase(noun)) {
                            npcFound = true;
                            System.out.println(element.getAction().get("talk"));
                        }
                    }
                    if (!npcFound) {
                        System.out.println("Who is " + noun + "?");
                    }
                }
                break;


            case "sound" :
                // handle sound command
                if (noun.equals("")) {
                    System.out.println("You can use following commands Sound On/Off/Start/Stop.");
                } else if (noun.equals("up")) {
                    Sound.increaseSound();
                } else if (noun.equals("down")) {
                    Sound.reduceSound();
                } else if (noun.equals("stop")) {
                    Sound.stopSound();
                } else if (noun.equals("start")) {
                    Sound.playSound();
                }
                break;

            case "help":
                // handle get command
                System.out.println("Commands:"
                    + "\n" + "Go - Move around"
                    + "\n" + "Look - Look at something"
                    + "\n" + "Talk - Talk to someone"
                    + "\n" + "Use - Use your potion to heal your wounds"
                    + "\n" + "Get - Pick up items"
                    + "\n" + "Drop - Drop items"
                    + "\n" + "Help - See commands again"
                    + "\n" + "Sound - Can Up/Down/Start/Stop");
                break;

            // default case to validate user input
            default:
                System.out.println(
                    "Invalid command. Type 'help' for a list of available commands.");
                break;
        }
    }

    public static void battle() {
        Scanner scanner = new Scanner(System.in);
        List<NPC> npcList1 = currentLocation.getNpc();
        int playerHealth = player.getHealth();
        int playerAttack = player.getAttack();
        while (currentLocation.isBattle()) {
            for (NPC element : npcList1) {
                int enemyHealth = element.getHealth();
                int enemyAttack = element.getAttack();
                System.out.println(element.getName());
                System.out.println("--------------------------------------");
                System.out.println("Player HP: " + playerHealth);
                System.out.println("Enemy HP: " + enemyHealth);
                System.out.println("--------------------------------------");
                System.out.println("You're being attacked!");
                String input = scanner.nextLine().toLowerCase();
                if (!input.equals("attack")) {
                    System.out.println("You have to attack!");
                } else {
                    enemyHealth = enemyHealth - playerAttack;
                    element.setHealth(enemyHealth);
                    System.out.println("--------------------------------------");
                    System.out.println("You attack and did " + playerAttack + " damage!");
                    if (enemyHealth <= 0) {
                        player.setHealth(playerHealth);
                        showStatus();
                        System.out.println("You defeated the enemy!");
                        System.out.println("--------------------------------------");
                        currentLocation.setBattle(false);
                        npcList1.remove(element);
                        break;
                    } else {
                        playerHealth = playerHealth - enemyAttack;
                        player.setHealth(playerHealth);
                        System.out.println("They attacked and did " + enemyAttack + " damage!");
                        System.out.println("--------------------------------------");
                        if (playerHealth <= 0) {
                            for (Location location : Main.locations) {
                                if (location.getName().equals("The Gate of Trials")) {
                                    System.out.println("You lost");
                                    currentLocation = location;
                                    player.setHealth(100);
                                    if (element.getName().equals("Goblin")) {
                                        element.setHealth(75);
                                    } else if (element.getName().equals("Chimera")) {
                                        element.setHealth(100);
                                    }
                                    player.setLocation(currentLocation);
                                    showStatus();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void playRiddle() {
        if (currentLocation.getName().equals("Goblin's Game Room")) {
            int guessCounter = 0;
            Scanner scanner = new Scanner(System.in);
            while (currentLocation.isPuzzle()) {
                System.out.println(
                    "This room has a riddle:" + currentLocation.getRiddles().get("question")
                        + "?");
                String guess = scanner.nextLine().toLowerCase();

                if (guessCounter >= 2) {
                    for (Location location : Main.locations) {
                        if (location.getName().equals("The Gate of Trials")) {
                            System.out.println(currentLocation.getRiddles().get("lost"));
                            currentLocation = location;
                            player.setLocation(currentLocation);
                            break;
                        }
                    }
                    showStatus();
                    break;
                } else if (!guess.equals("fire")) {
                    guessCounter++;
                    System.out.println(
                        currentLocation.getRiddles().get("incorrect") + " You guessed "
                            + guessCounter + " time wrong out of 3 tries.");
                } else {
                    showStatus();
                    System.out.println(currentLocation.getRiddles().get("correct"));
                    currentLocation.setPuzzle(false);
                    break;
                }
            }
        }
    }

    public static void showStatus() {
        System.out.println("--------------------------------------");
        System.out.println("Location: " + currentLocation.getName());
        System.out.println("Directions: " + currentLocation.getDirections().keySet());
        System.out.println("Health: " + player.getHealth());
        System.out.print("Inventory: ");
        for (Item element : player.getInventory()) {
            System.out.print("[" + element.getName() + "]");
        }
        System.out.println(" ");
        System.out.println("--------------------------------------");
    }

    public static void roomDescription() {
        System.out.println(currentLocation.getDescription());

        Main.sound.soundFXLoad(player);

        System.out.println("--------------------------------------");
    }

    public static void showNPC() {
        List<NPC> npcList = currentLocation.getNpc();
        if (npcList.isEmpty()) {
            return;
        } else {
            for (NPC element : npcList) {
                System.out.println("You see a " + element.getName());
                System.out.println("--------------------------------------");
            }
        }
    }

    public static void showItem() {
        List<Item> itemList = currentLocation.getItems();
        if (itemList.isEmpty()) {
            return;
        } else {
            for (Item element : itemList) {
                System.out.println("You see a " + element.getName());
                System.out.println("--------------------------------------");
            }
        }
    }

    public static void gameIntro() {
        //Display commands
        System.out.println("--------------------------------------");
        System.out.println("Commands:"
            + "\n" + "Go - Move around"
            + "\n" + "Look - Look at something"
            + "\n" + "Talk - Talk to someone"
            + "\n" + "Use - Use your potion to heal your wounds"
            + "\n" + "Get - Pick up items"
            + "\n" + "Drop - Drop items"
            + "\n" + "Help - See commands again"
            + "\n" + "Sound - Can Up/Down/Start/Stop");
        System.out.println("--------------------------------------");

        System.out.println("Let the adventure begin!");

        // Display basic information about the game
        System.out.println(
            "-------------------------------------------------------------------------------");
        System.out.println(
            "You walk into a dark, damp dungeon. You are in search of the holy grail.");
        System.out.println(
            "The ancient dragon's minions has stolen the key to open the gate that leads to the Holy Grail!");
        System.out.println(
            "They broke the key into 3 pieces and scattered them throughout the dungeon!");
        System.out.println("Find them to continue your journey towards the Holy Grail!");
    }

    public static void gameTitle() {
        System.out.println("-----------------------");
        System.out.println("Quest For The Holy Grail");
    }

}
