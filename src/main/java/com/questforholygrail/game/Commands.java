package com.questforholygrail.game;

import com.questforholygrail.game.UI.Display;
import com.questforholygrail.game.UI.UserInput;
import java.util.List;
import java.util.Map;

public class Commands {

    private static Player player;
    private static Location currentLocation;

    public Commands(Player player, Location currentLocation) {
        Commands.player = player;
        Commands.currentLocation = currentLocation;
    }

    public void parseCommand(String command) {
        //splits input at whitespaces
        String[] words = command.trim().split(" ", 2);

        //puts first input word into verb
        String verb = words[0].toLowerCase();
        String noun = "";

        //assigns second word from input to noun (this could be an issue for multiple-word nouns)
        if (words.length > 1) {
            noun = words[1].toLowerCase();
        }

        switch (verb) {
            case "look":
                //gets list of items and characters in current room, items in inventory
                List<Item> itemList = currentLocation.getItems();
                List<NPC> npcList = currentLocation.getNpc();
                List<Item> inventory2 = player.getInventory();

                //if noun is empty, asks user for noun
                if (noun.equals("")) {
                    Display.printScreenLn("Look at what?");
                    break;
                }
                boolean itemFound = false;

                //checks room information
                if(noun.equalsIgnoreCase(currentLocation.getName())) {
                    Display.printScreenLn(currentLocation.getDescription());
                }

                //checks current location for item
                for (Item item : itemList) {
                    if (item.getName().equalsIgnoreCase(noun)) {
                        Display.printScreenLn(item.getAction().get("look"));
                        itemFound = true;
                        break;
                    }
                }

                //checks player inventory for items
                for (Item item : inventory2) {
                    if (item.getName().equalsIgnoreCase(noun)) {
                        Display.printScreenLn(item.getAction().get("look"));
                        itemFound = true;
                        break;
                    }
                }

                //if item isn't found, looks at characters in room
                if (!itemFound) {
                    boolean npcFound = false;
                    for (NPC npc : npcList) {
                        if (npc.getName().equalsIgnoreCase(noun)) {
                            Display.printScreenLn(npc.getAction().get("look"));
                            npcFound = true;
                            break;
                        }
                    }
                    //if the NPC isn't found, tells player there's no (noun) there
                    if (!npcFound) {
                        Display.printScreenLn("You can't do that.");
                    }
                }
                break;

            case "go":
                // handle "go" command
                //if no noun, prompts player to add noun
                if (noun.equals("")) {
                    Display.printScreenLn("Go where?");
                } else {
                    //gets possible exit directions from current location
                    Map<String, String> directions = currentLocation.getDirections();
                    //if the noun is a valid direction for the current room, tries to change current location to the new location
                    if (directions.containsKey(noun)) {

                        String nextLocationName = directions.get(noun);
                        for (Location location : Main.getLocations()) {
                            if (location.getName().equals(nextLocationName)) {
                                //if current location is locked, checks player's inventory for key
                                if (location.isLocked()) {
                                    List<Item> inventory = player.getInventory();
                                    boolean hasKey = false;
                                    for (Item item : inventory) {
                                        //if key, player unlocks the door and removes key from inventory
                                        if (item.getName().equals("key")) {
                                            hasKey = true;
                                            Display.printScreenLn("The door unlocked!");
                                            inventory.remove(item);
                                            break;
                                        }
                                    }
                                    //if no key, player can't open the door, returns to calling method
                                    if (!hasKey) {
                                        Display.printScreenLn("This door is locked...");
                                        break;
                                    }
                                }
                                //if door not locked/unlocked, currentLocation updates
                                currentLocation = location;
                                player.setLocation(currentLocation);
                                currentLocation.setLocked(false);
                            }
                        }
                    } else {
                        //if not valid direction, player is provided feedback
                        Display.printScreenLn("You can't go in that direction.");
                    }
                }
                break;

            case "get":
                // handle get command
                List<Item> roomItem = currentLocation.getItems();
                List<Item> myInventory = player.getInventory();

                //if no noun, provides feedback to player
                if (noun.equals("")) {
                    Display.printScreenLn("Get what?");
                } else {
                    boolean itemFound1 = false;
                    //checks items in room for match to noun
                    for (Item element : roomItem) {
                        if (element.getName().equalsIgnoreCase(noun)) {
                            itemFound1 = true;
                            //adds item to player inventory, removes from room item list
                            myInventory.add(element);
                            roomItem.remove(element);
                            Display.printScreenLn(element.getAction().get("get"));
                            break;
                        }
                    }
                    if (!itemFound1) {
                        //provides feedback to user if no match for noun found
                        Display.printScreenLn("You can't do that.");
                    }
                }
                break;

            case "drop":
                // handle drop command
                List<Item> myInventory1 = player.getInventory();
                List<Item> room = currentLocation.getItems();

                //provides feedback to player if no noun provided
                if (noun.equals("")) {
                    Display.printScreenLn("Drop what?");
                } else {
                    boolean itemFound2 = false;
                    //checks inventory for item with matching name of noun
                    for (Item inventory1 : myInventory1) {
                        if (inventory1.getName().equalsIgnoreCase(noun)) {
                            itemFound2 = true;
                            //if item found, removes item from player inventory and adds to room item list
                            myInventory1.remove(inventory1);
                            room.add(inventory1);
                            Display.printScreenLn(inventory1.getAction().get("drop"));
                            break;
                        }
                    }
                    //if no match found, provides feedback to player
                    if (!itemFound2) {
                        Display.printScreenLn("You can't drop that.");
                    }
                }
                break;

            case "use":
                // handle use command for potion
                List<Item> myInventory2 = player.getInventory();
                int health = player.getHealth();

                //if no noun provided, gives feedback to player
                if (noun.equals("")) {
                    Display.printScreenLn("Use what?");
                } else {
                    boolean potionFound = false;
                    //checks inventory for potion
                    for (Item item : myInventory2) {
                        if (item.getName().equalsIgnoreCase("potion")) {
                            //if potion found, increases player heath, removes potion from inventory
                            potionFound = true;
                            health += 50;
                            player.setHealth(health);
                            myInventory2.remove(item);
                            Display.printScreenLn(item.getAction().get("use"));
                            break;
                        }
                    }
                    //if no potion found, provides feedback to player
                    if (noun.equalsIgnoreCase("potion") && !potionFound) {
                        Display.printScreenLn("You don't have any potions to use!");
                    } else if (!noun.equalsIgnoreCase("potion") && !potionFound){
                        Display.printScreenLn("The 'use' command can only be used on potions.");
                    }
                }
                break;

            case "talk":
                // handle talk command
                List<NPC> npc = currentLocation.getNpc();
                //if no noun provided, prompts user for noun
                if (noun.equals("")) {
                    Display.printScreenLn("Talk to who?");
                } else {
                    boolean npcFound = false;
                    //checks room for NPC with name matching noun
                    for (NPC element : npc) {
                        if (element.getName().equalsIgnoreCase(noun)) {
                            npcFound = true;
                            //if found, NPC talks
                            Display.printScreenLn(element.getAction().get("talk"));
                        }
                    }
                    //if not found, provides feedback to player
                    if (!npcFound) {
                        Display.printScreenLn("Who is " + noun + "?");
                    }
                }
                break;


            case "sound" :
                // handle sound command
                switch (noun) {
                    case "":
                        Display.printScreenLn(
                                "You can use following commands Sound On/Off/Start/Stop.");
                        break;
                    case "up":
                        Sound.increaseSound();
                        break;
                    case "down":
                        Sound.reduceSound();
                        break;
                    case "stop":
                        Sound.setEffectsMuted(true);
                        Sound.stopSound();
                        break;
                    case "start":
                        Sound.setEffectsMuted(false);
                        Sound.playSound();
                        break;
                }
                break;

            case "help":
                // handle get command
                Display.printScreenLn("--------------------------------------");
                Display.printScreenLn("Commands:"
                        + "\n" + "Go - Move around"
                        + "\n" + "Look - Look at something"
                        + "\n" + "Talk - Talk to someone"
                        + "\n" + "Use - Use your potion to heal your wounds"
                        + "\n" + "Get - Pick up items"
                        + "\n" + "Drop - Drop items"
                        + "\n" + "Sound - Can Up/Down/Start/Stop");
                Display.printScreenLn("--------------------------------------");
                Display.printScreenLn("Battle:"
                        + "\n" + "Attack - Attack an enemy"
                        + "\n" + "Heal - Use your potion");
                Display.printScreenLn("--------------------------------------");
                break;
            case "quit":
                // if user entered 'quit', returns to Main to end main loop
                break;

            case "equip":
                //if user enters 'equip'
                if (noun.equals("")) {
                    Display.printScreenLn("Equip what?");
                } else {
                    boolean matchedItem = false;
                    for(Item item : player.getInventory()){
                        if (item.getName().equalsIgnoreCase(noun)){
                            matchedItem = true;
                            if(item.getAction().containsKey("equip")){
                                player.setEquippedItem(item);
                                Display.printScreenLn(item.getAction().get("equip"));
                            } else {
                                Display.printScreenLn("You can't equip that!");
                            }
                            break;
                        }
                    }
                    if(!matchedItem){
                        Display.printScreenLn("You don't have that in your inventory.");
                    }
                }
                break;

            // default case to validate user input
            default:
                Display.printScreenLn(
                        "Invalid command. Type 'help' for a list of available commands.");
                break;
        }
    }

    public static void battle() {

        while (currentLocation.isBattle()) {
            List<NPC> npcList1 = currentLocation.getNpc();
            List<Item> myInventory3 = player.getInventory();
            int playerHealth = player.getHealth();
            int playerAttack = player.getAttack();
            //minimum attack
            int min = 10;
            //calculates player's attack power
            int playerRandomAttack = (int)(Math.random() * (playerAttack - min + 1) + min);
            //gets enemy info
            for (NPC element : npcList1) {
                int enemyHealth = element.getHealth();
                int enemyAttack = element.getAttack();
                //calculates enemy attack power
                int npcRandomAttack = (int)(Math.random() * (enemyAttack - min + 1) + min);
                //prints battle update
                Display.printScreenLn(element.getName());
                Display.printScreenLn("--------------------------------------");
                Display.printScreenLn("Player HP: " + playerHealth);
                Display.printScreenLn("Enemy HP: " + enemyHealth);
                Display.printScreenLn("--------------------------------------");
                Display.printScreenLn("You're being attacked!");
                //takes user input
                String input = UserInput.getInput().toLowerCase();
                if (input.equals("attack")) {
                    //applies player's attack to enemy health
                    enemyHealth = enemyHealth - playerRandomAttack;
                    element.setHealth(enemyHealth);
                    Display.printScreenLn("--------------------------------------");
                    Display.printScreenLn("You attack the enemy and did " + playerRandomAttack + " damage!");
                    if (enemyHealth <= 0) {
                        //if enemy defeated, applies health changes to player
                        //removes npc from room
                        player.setHealth(playerHealth);
                        showStatus();
                        Display.printScreenLn("You defeated the enemy!");
                        Display.printScreenLn("--------------------------------------");
                        currentLocation.setBattle(false);
                        npcList1.remove(element);
                        break;
                    } else {
                        //if enemy not defeated, applies NPC attack to player health
                        playerHealth = playerHealth - npcRandomAttack;
                        player.setHealth(playerHealth);
                        Display.printScreenLn("The enemy attacked you and did " + npcRandomAttack + " damage!");
                        Display.printScreenLn("--------------------------------------");
                        if (playerHealth <= 0) {
                            //if player defeated, restarts player at beginning of game with full health
                            for (Location location : Main.getLocations()) {
                                if (location.getName().equals("The Gate of Trials")) {
                                    Display.printScreenLn("You lost");
                                    currentLocation = location;
                                    player.setHealth(100);
                                    element.setHealth(100);
                                    player.setLocation(currentLocation);
                                    showStatus();
                                    break;
                                }
                            }
                        }
                    }
                } else if (input.equals("heal")) {
                    boolean potionFound = false;
                    //if player has potion, heals player and removes potion from inventory
                    for (Item item : myInventory3) {
                        if (item.getName().equalsIgnoreCase("potion")) {
                            potionFound = true;
                            playerHealth += 50;
                            player.setHealth(playerHealth);
                            myInventory3.remove(item);
                            Display.printScreenLn(item.getAction().get("use"));
                            Display.printScreenLn("--------------------------------------");
                            break;
                        }
                    }
                    //if player doesn't have potion, provides feedback to player
                    if (!potionFound) {
                        Display.printScreenLn("You don't have any potions!");
                        Display.printScreenLn("--------------------------------------");
                        break;
                    }
                } else {
                    //if player input was not "heal" or "attack", provides feedback to player
                    Display.printScreenLn("You have to attack!");
                    Display.printScreenLn("--------------------------------------");
                }
            }
        }
    }


    public static void playRiddle() {
        int guessCounter = 0;

        while (currentLocation.isPuzzle()) {
            Display.printScreenLn("You're trapped!");
            //prints riddle
            Display.printScreenLn(currentLocation.getRiddles().get("question"));
            //increments guessCounter
            guessCounter++;
            //gets user input
            String guess = UserInput.getInput().toLowerCase();
            if (guessCounter == 3 && !guess.equals("fire")) {
                //if player is out of guesses, starts player at beginning
                for (Location location : Main.getLocations()) {
                    if (location.getName().equals("The Gate of Trials")) {
                        Display.printScreenLn("Sorry! You lose!");
                        currentLocation = location;
                        player.setLocation(currentLocation);
                        break;
                    }
                }
                showStatus();
                break;
            } else if (!guess.equals("fire")) {
                //prints feedback to player
                Display.printScreenLn(
                        currentLocation.getRiddles().get("incorrect") + " You guessed "
                                + guessCounter + " time(s) wrong out of 3 tries.");
                Display.printScreenLn("--------------------------------------");
            } else {
                //removes puzzle from room if guess was correct
                Display.printScreenLn("You've solved the riddle!");
                showStatus();
                currentLocation.setPuzzle(false);
                break;
            }
        }
    }

    //prints status widget
    public static void showStatus() {
        Display.printScreenLn("--------------------------------------");
        Display.printScreenLn("Location: " + currentLocation.getName());
        Display.printScreenLn("Directions: " + currentLocation.getDirections().keySet());
        Display.printScreenLn("Health: " + player.getHealth());
        Display.printScreen("Inventory: ");
        for (Item element : player.getInventory()) {
            Display.printScreen("[" + element.getName() + "]");
        }
        Display.printScreenLn(" ");
        Display.printScreen("Equipped Item: ");
        Display.printScreen((player.getEquippedItem() == null ? "": "[" +player.getEquippedItem().getName() + "]"));
        Display.printScreenLn(" ");
        Display.printScreenLn("--------------------------------------");
    }

    //prints room description
    public static void roomDescription() {
        Main.getSound().soundFXLoad(player);
        Display.printScreenLn("--------------------------------------");
    }

    //prints NPCs in current room
    public static void showNPC() {
        List<NPC> npcList = currentLocation.getNpc();
        if (npcList.isEmpty()) {
            return;
        } else {
            for (NPC element : npcList) {
                Display.printScreenLn("You see a " + element.getName());
                Display.printScreenLn("--------------------------------------");
            }
        }
    }

    //prints items in current room
    public static void showItem() {
        List<Item> itemList = currentLocation.getItems();
        if (itemList.isEmpty()) {
            return;
        } else {
            for (Item element : itemList) {
                Display.printScreenLn("You see a " + element.getName());
                Display.printScreenLn("--------------------------------------");
            }
        }
    }

    //prints game intro
    public static void gameIntro() {
        //Display commands
        Display.printScreenLn("--------------------------------------");
        Display.printScreenLn("Commands:"
                + "\n" + "Go - Move around"
                + "\n" + "Look - Look at something"
                + "\n" + "Talk - Talk to someone"
                + "\n" + "Use - Use your potion to heal your wounds"
                + "\n" + "Get - Pick up items"
                + "\n" + "Drop - Drop items"
                + "\n" + "Sound - Can Up/Down/Start/Stop");
        Display.printScreenLn("--------------------------------------");
        Display.printScreenLn("Battle:"
                + "\n" + "Attack - Attack an enemy"
                + "\n" + "Heal - Use your potion");
        Display.printScreenLn("--------------------------------------");
        Display.printScreenLn("Type \"help\" to see the list of commands again.");
        Display.printScreenLn("--------------------------------------");

        Display.printScreenLn("Let the adventure begin!");

        // Display basic information about the game
        Display.printScreenLn(
                "-------------------------------------------------------------------------------");
        Display.printScreenLn(
                "You walk into a dark, damp dungeon. You are in search of the holy grail.");
        Display.printScreenLn(
                "Monsters and traps are scattered throughout the rooms. Make your way to glory!");
    }

    //prints game title
    public static void gameTitle() {
        Display.printScreenLn("-----------------------");
        Display.printScreenLn("Quest For The Holy Grail");
        Display.printScreenLn("   _________\n"
                + "  |o^o^o^o^o|\n"
                + "  {   _!_   }\n"
                + "   \\   !   /\n"
                + "    `.   .'\n"
                + "      )=(\n"
                + "     ( + )\n"
                + "      ) (\n"
                + "  .--'   `--.\n"
                + "  `---------'");
    }

    public void soundOn(){

    }

    public void soundOff(){

    }

}