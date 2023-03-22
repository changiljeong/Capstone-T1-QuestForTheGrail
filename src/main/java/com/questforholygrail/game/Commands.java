package com.questforholygrail.game;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Commands {

    private static Player player;
   // private static NPC npc;
    private static Location currentLocation;

    public Commands(Player player, Location currentLocation) {
        Commands.player = player;
        Commands.currentLocation = currentLocation;
    }

    public void parseCommand(String command) {
        String[] words = command.split(" ");
        String verb = words[0].toLowerCase();
        String noun = "";

        if (words.length > 1) {
            noun = words[1].toLowerCase();
        }



        switch (verb) {
            case "look":
                List<Item> itemList = currentLocation.getItems();
                List<NPC> npcList = currentLocation.getNpc();
                if (noun.equals("")) {
                    System.out.println("Look at what?");
                    break;
                }
                boolean itemFound1 = false;
                for (Item item : itemList) {
                    if (item.getName().equalsIgnoreCase(noun)) {
                        System.out.println(item.getAction().get("look"));
                        itemFound1 = true;
                        break;
                    }
                }
                if (!itemFound1) {
                    boolean npcFound = false;
                    for (NPC npc : npcList) {
                        if (npc.getName().equalsIgnoreCase(noun)) {
                            System.out.println(npc.getAction().get("look"));
                            npcFound = true;
                            break;
                        }
                    }
                    if (!npcFound) {
                        System.out.println("There is no " + noun + " or one here.");
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
                                currentLocation = location;
                                player.setLocation(currentLocation);
                            }
                        }
                    } else {
                        System.out.println("You can't go in that direction.");
                    }
                }
                break;

            case "get" :
                // handle get command
                List<Item> roomItem = currentLocation.getItems();
                List<Item> myInventory = player.getInventory();
                if (noun.equals("")) {
                    System.out.println("Get what?");
                } else {
                    boolean itemFound = false;
                    for (Item element : roomItem) {
                        if (element.getName().equalsIgnoreCase(noun)) {
                            itemFound = true;
                            myInventory.add(element);
                            roomItem.remove(element);
                            System.out.println(element.getAction().get("get"));
                            break;
                        }
                    }
                    if (!itemFound) {
                        System.out.println("There is no " + noun + ".");
                    }
                }
                break;

            case "drop" :
                // handle drop command
                List<Item> myInventory1 = player.getInventory();
                List<Item> room = currentLocation.getItems();
                if (noun.equals("")) {
                    System.out.println("Drop what?");
                } else {
                    for (Item inventory1 : myInventory1) {
                        if (inventory1.getName().equalsIgnoreCase(noun)) {
                            myInventory1.remove(inventory1);
                            room.add(inventory1);
                            System.out.println(inventory1.getAction().get("drop"));
                            break;
                        }
                    }
                }
                break;

            case "talk" :
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

//            case "attack":
//                List<NPC> npcs = currentLocation.getNpc();
//                NPC npc = new NPC()
//
//                if (noun.equals("")) {
//                    System.out.println("Attack who?");
//                } else {
//                    boolean NpcFound = false;
//                    int playerHealth = player.getHealth();
//                    int NPCHealth = npc.getHealth();
//                    for (NPC npc1 : npcs) {
//                        if (npc1.getName().equalsIgnoreCase(noun)) {
//                            NpcFound = true;
//                            if (playerHealth > 0) {
//                                BattleSequence battle = new BattleSequence(player, npc1);
//                                battle.start();
//                            } else if (NPCHealth <= 0) {
//                                npcs.remove(npc1);
//                            }
//                        }
//                    }
//                    if (!NpcFound) {
//                        System.out.println("There is no " + noun + " to attack.");
//                    }
//                }
//                break;

            case "help":
                // handle get command
                System.out.println("Commands:"
                    + "\n" + "Go - move around"
                    + "\n" + "Look - Look at something"
                    + "\n" + "Talk - Talk to someone"
                    + "\n" + "Get - pick up stuff"
                    + "\n" + "Help - see commands again"
                    + "\n" + "Attack - Attack command");
                break;

            // default case to validate user input
            default:
                System.out.println("Invalid command. Type 'help' for a list of available commands.");
                break;
        }
    }

    public static void playRiddle() {

        if (currentLocation.getName().equals("Goblin's Game Room")) {
            int guessCounter = 0;
            Scanner scanner = new Scanner(System.in);
            while(currentLocation.isPuzzle()) {
                System.out.println("<<Riddle Room Question>>>: " + currentLocation.getRiddles().get("question") + "?");
                String guess = scanner.nextLine().toLowerCase();

                if (guessCounter>=2) {
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
                    System.out.println(currentLocation.getRiddles().get("incorrect") + " You guessed " + guessCounter + " time wrong out of 3 tries.");
                } else {
                    System.out.println(currentLocation.getRiddles().get("correct") + " Hint: " + currentLocation.getDirections().keySet() );
                    currentLocation.setPuzzle(false);
                    break;
                }
            }
        }}



    public static void showStatus() {
        System.out.println("--------------------------------------");
        System.out.println("Location: " + currentLocation.getName());
        System.out.println("Directions: " + currentLocation.getDirections().keySet());
        System.out.println("Health: " + player.getHealth());



        System.out.print("Item Inventory: " );
        for (Item element : player.getInventory()) {
            System.out.print(" +" + element.getName());
        }
        System.out.println(" ");
        System.out.println("--------------------------------------");
    }

    public static void roomDescription() {
        System.out.println(currentLocation.getDescription());
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
            + "\n" + "Get - Pick up stuff"
            + "\n" + "Help - See commands again");
        System.out.println("--------------------------------------");

        System.out.println("Let the adventure begin!");

        // Display basic information about the game
        System.out.println(
            "-------------------------------------------------------------------------------");
        System.out.println(
            "You walk into a dark, damp dungeon. You are in search of the holy grail.");
        System.out.println(
            "The ancient dragon's minions package com.questforholygrail.game;\n" +
                    "\n" +
                    "import java.util.List;\n" +
                    "import java.util.Map;\n" +
                    "\n" +
                    "public class Commands {\n" +
                    "\n" +
                    "    private static Player player;\n" +
                    "    private static Location currentLocation;\n" +
                    "\n" +
                    "    public Commands(Player player, Location currentLocation) {\n" +
                    "        Commands.player = player;\n" +
                    "        Commands.currentLocation = currentLocation;\n" +
                    "    }\n" +
                    "\n" +
                    "    public static void setPlayer(Player player) {\n" +
                    "    }\n" +
                    "\n" +
                    "    public static void setCurrentLocation(Location currentLocation) {\n" +
                    "    }\n" +
                    "\n" +
                    "    public void parseCommand(String command) {\n" +
                    "        String[] words = command.split(\" \");\n" +
                    "        String verb = words[0].toLowerCase();\n" +
                    "        String noun = \"\";\n" +
                    "\n" +
                    "        if (words.length > 1) {\n" +
                    "            noun = words[1].toLowerCase();\n" +
                    "        }\n" +
                    "\n" +
                    "        switch (verb) {\n" +
                    "            case \"look\":\n" +
                    "                List<Item> itemList = currentLocation.getItems();\n" +
                    "                List<NPC> npcList = currentLocation.getNpc();\n" +
                    "                if (noun.equals(\"\")) {\n" +
                    "                    System.out.println(\"Look at what?\");\n" +
                    "                    break;\n" +
                    "                }\n" +
                    "                boolean itemFound1 = false;\n" +
                    "                for (Item item : itemList) {\n" +
                    "                    if (item.getName().equalsIgnoreCase(noun)) {\n" +
                    "                        System.out.println(item.getAction().get(\"look\"));\n" +
                    "                        itemFound1 = true;\n" +
                    "                        break;\n" +
                    "                    }\n" +
                    "                }\n" +
                    "                if (!itemFound1) {\n" +
                    "                    boolean npcFound = false;\n" +
                    "                    for (NPC npc : npcList) {\n" +
                    "                        if (npc.getName().equalsIgnoreCase(noun)) {\n" +
                    "                            System.out.println(npc.getAction().get(\"look\"));\n" +
                    "                            npcFound = true;\n" +
                    "                            break;\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "                    if (!npcFound) {\n" +
                    "                        System.out.println(\"There is no \" + noun + \" or one here.\");\n" +
                    "                    }\n" +
                    "                }\n" +
                    "                break;\n" +
                    "\n" +
                    "            case \"go\":\n" +
                    "                // handle \"go\" command\n" +
                    "                if (noun.equals(\"\")) {\n" +
                    "                    System.out.println(\"Go where?\");\n" +
                    "                } else {\n" +
                    "                    Map<String, String> directions = currentLocation.getDirections();\n" +
                    "                    if (directions.containsKey(noun)) {\n" +
                    "                        String nextLocationName = directions.get(noun);\n" +
                    "                        for (Location location : Main.locations) {\n" +
                    "                            if (location.getName().equals(nextLocationName)) {\n" +
                    "                                currentLocation = location;\n" +
                    "                                player.setLocation(currentLocation);\n" +
                    "                            }\n" +
                    "                        }\n" +
                    "                    } else {\n" +
                    "                        System.out.println(\"You can't go in that direction.\");\n" +
                    "                    }\n" +
                    "                }\n" +
                    "                break;\n" +
                    "\n" +
                    "            case \"get\" :\n" +
                    "                // handle get command\n" +
                    "                List<Item> roomItem = currentLocation.getItems();\n" +
                    "                List<Item> myInventory = player.getInventory();\n" +
                    "                if (noun.equals(\"\")) {\n" +
                    "                    System.out.println(\"Get what?\");\n" +
                    "                } else {\n" +
                    "                    boolean itemFound = false;\n" +
                    "                    for (Item element : roomItem) {\n" +
                    "                        if (element.getName().equalsIgnoreCase(noun)) {\n" +
                    "                            itemFound = true;\n" +
                    "                            myInventory.add(element);\n" +
                    "                            roomItem.remove(element);\n" +
                    "                            System.out.println(element.getAction().get(\"get\"));\n" +
                    "                            break;\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "                    if (!itemFound) {\n" +
                    "                        System.out.println(\"There is no \" + noun + \".\");\n" +
                    "                    }\n" +
                    "                }\n" +
                    "                break;\n" +
                    "\n" +
                    "            case \"drop\" :\n" +
                    "                // handle drop command\n" +
                    "                List<Item> myInventory1 = player.getInventory();\n" +
                    "                List<Item> room = currentLocation.getItems();\n" +
                    "                if (noun.equals(\"\")) {\n" +
                    "                    System.out.println(\"Drop what?\");\n" +
                    "                } else {\n" +
                    "                    for (Item inventory1 : myInventory1) {\n" +
                    "                        if (inventory1.getName().equalsIgnoreCase(noun)) {\n" +
                    "                            myInventory1.remove(inventory1);\n" +
                    "                            room.add(inventory1);\n" +
                    "                            System.out.println(inventory1.getAction().get(\"drop\"));\n" +
                    "                            break;\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "                }\n" +
                    "                break;\n" +
                    "\n" +
                    "            case \"talk\" :\n" +
                    "                // handle talk command\n" +
                    "                List<NPC> npc = currentLocation.getNpc();\n" +
                    "                if (noun.equals(\"\")) {\n" +
                    "                    System.out.println(\"Talk to who?\");\n" +
                    "                } else {\n" +
                    "                    boolean npcFound = false;\n" +
                    "                    for (NPC element : npc) {\n" +
                    "                        if (element.getName().equalsIgnoreCase(noun)) {\n" +
                    "                            npcFound = true;\n" +
                    "                            System.out.println(element.getAction().get(\"talk\"));\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "                    if (!npcFound) {\n" +
                    "                        System.out.println(\"Who is \" + noun + \"?\");\n" +
                    "                    }\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "\n" +
                    "            case \"help\":\n" +
                    "                // handle get command\n" +
                    "                System.out.println(\"Commands:\"\n" +
                    "                    + \"\\n\" + \"Go - move around\"\n" +
                    "                    + \"\\n\" + \"Look - Look at something\"\n" +
                    "                    + \"\\n\" + \"Talk - Talk to someone\"\n" +
                    "                    + \"\\n\" + \"Get - pick up stuff\"\n" +
                    "                    + \"\\n\" + \"Help - see commands again\");\n" +
                    "                break;\n" +
                    "\n" +
                    "            // default case to validate user input\n" +
                    "            default:\n" +
                    "                System.out.println(\"Invalid command. Type 'help' for a list of available commands.\");\n" +
                    "                break;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    public static void showStatus() {\n" +
                    "        System.out.println(\"--------------------------------------\");\n" +
                    "        System.out.println(\"Location: \" + currentLocation.getName());\n" +
                    "        System.out.println(\"Directions: \" + currentLocation.getDirections().keySet());\n" +
                    "        System.out.println(\"Health: \" + player.getHealth());\n" +
                    "        System.out.println(\"Inventory: \" + player.getInventory());\n" +
                    "        System.out.println(\"--------------------------------------\");\n" +
                    "    }\n" +
                    "\n" +
                    "    public static void roomDescription() {\n" +
                    "        System.out.println(currentLocation.getDescription());\n" +
                    "        System.out.println(\"--------------------------------------\");\n" +
                    "    }\n" +
                    "\n" +
                    "    public static void showNPC() {\n" +
                    "        List<NPC> npcList = currentLocation.getNpc();\n" +
                    "        if (npcList.isEmpty()) {\n" +
                    "            return;\n" +
                    "        } else {\n" +
                    "            for (NPC element : npcList) {\n" +
                    "                System.out.println(\"You see a \" + element.getName());\n" +
                    "                System.out.println(\"--------------------------------------\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    public static void showItem() {\n" +
                    "        List<Item> itemList = currentLocation.getItems();\n" +
                    "        if (itemList.isEmpty()) {\n" +
                    "            return;\n" +
                    "        } else {\n" +
                    "            for (Item element : itemList) {\n" +
                    "                System.out.println(\"You see a \" + element.getName());\n" +
                    "                System.out.println(\"--------------------------------------\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    public static void gameIntro() {\n" +
                    "        //Display commands\n" +
                    "        System.out.println(\"--------------------------------------\");\n" +
                    "        System.out.println(\"Commands:\"\n" +
                    "            + \"\\n\" + \"Go - Move around\"\n" +
                    "            + \"\\n\" + \"Look - Look at something\"\n" +
                    "            + \"\\n\" + \"Talk - Talk to someone\"\n" +
                    "            + \"\\n\" + \"Get - Pick up stuff\"\n" +
                    "            + \"\\n\" + \"Help - See commands again\");\n" +
                    "        System.out.println(\"--------------------------------------\");\n" +
                    "\n" +
                    "        System.out.println(\"Let the adventure begin!\");\n" +
                    "\n" +
                    "        // Display basic information about the game\n" +
                    "        System.out.println(\n" +
                    "            \"-------------------------------------------------------------------------------\");\n" +
                    "        System.out.println(\n" +
                    "            \"You walk into a dark, damp dungeon. You are in search of the holy grail.\");\n" +
                    "        System.out.println(\n" +
                    "            \"The ancient dragon's minions has stolen the key to open the gate that leads to the Holy Grail!\");\n" +
                    "        System.out.println(\n" +
                    "            \"They broke the key into 3 pieces and scattered them throughout the dungeon!\");\n" +
                    "        System.out.println(\"Find them to continue your journey towards the Holy Grail!\");\n" +
                    "    }\nas stolen the key to open the gate that leads to the Holy Grail!");
        System.out.println(
            "They broke the key into 3 pieces and scattered them throughout the dungeon!");
        System.out.println("Find them to continue your journey towards the Holy Grail!");
    }

    public static void gameTitle() {
        System.out.println("-----------------------");
        System.out.println("Quest For The Holy Grail");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⣦⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⢀⣤⠀⠀⠀\n"
            + "⠀⠀⠀⢿⣷⣄⠀⠀⠀⠀⣴⠟⠛⠛⠉⠉⠛⠛⠻⣦⠀⠀⠀⠀⣠⣾⡿⠀⠀⠀\n"
            + "⠀⠀⠀⠸⣿⣿⣦⣄⠀⠀⣿⣦⣤⣄⣀⣀⣠⣤⣶⣿⠀⠀⣠⣼⣿⣿⠃⠀⠀⠀\n"
            + "⠀⢠⣄⡀⠙⢿⣿⣿⣷⡀⢻⣿⣿⣿⣿⣿⣿⣿⣿⡟⢀⣾⣿⣿⡿⠋⢀⣠⠄⠀\n"
            + "⠀⠀⠻⣿⣿⣶⣿⣿⣿⣧⠈⢿⣿⣿⣿⣿⣿⣿⡿⠁⣼⣿⣿⣿⣶⣿⣿⠏⠀⠀\n"
            + "⠀⠀⠀⠈⠻⢿⣿⣿⣿⣿⣧⡈⠻⢿⣿⣿⡿⠟⢀⣾⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀\n"
            + "⠀⠀⠀⠀⣀⣠⣤⣴⣿⣿⣿⡿⠂⢠⣿⣿⡄⠐⢿⣿⣿⣿⣦⣤⣄⡀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠈⠉⠛⠛⠛⠛⠉⠀⢀⣾⣿⣿⣷⡀⠀⠉⠛⠛⠛⠛⠉⠁⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠻⠟⠛⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("-----------------------");
    }

}
