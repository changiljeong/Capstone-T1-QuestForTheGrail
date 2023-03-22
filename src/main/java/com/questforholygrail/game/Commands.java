package com.questforholygrail.game;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Commands {

    private static Player player;
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

            case "help":
                // handle get command
                System.out.println("Commands:"
                    + "\n" + "Go - move around"
                    + "\n" + "Look - Look at something"
                    + "\n" + "Talk - Talk to someone"
                    + "\n" + "Get - pick up stuff"
                    + "\n" + "Help - see commands again");
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
            "The ancient dragon's minions has stolen the key to open the gate that leads to the Holy Grail!");
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
