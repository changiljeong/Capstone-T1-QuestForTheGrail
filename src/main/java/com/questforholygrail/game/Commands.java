package com.questforholygrail.game;

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
        String[] words = command.split(" ");
        String verb = words[0].toLowerCase();
        String noun = "";

        if (words.length > 1) {
            noun = words[1].toLowerCase();
        }

        switch (verb) {
            case "look":
                List<Item> itemList = currentLocation.getItems();
                List<String> inventory = player.getInventory();
                if (noun.equals("")) {
                    System.out.println("Look what?");
                } else {
                    for (Item element : itemList) {
                        if (element.getName().equals(noun) || inventory.contains(noun)) {
                            // Can we do here for having 2 items?
                            System.out.println("You see an item: " + element.getName() + "with description of : " + element.getDescription());
                            System.out.println("---------------------");
                        }

                        // Does not print out...
                        else {
                            System.out.println("Their is no " + noun + ".");
                        }
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
//
//            case "get" :
//                // handle get command
//                Map<String, String> roomItem = currentLocation.getItems();
//                List<String> myInventory = player.getInventory();
//                if (noun.equals("")) {
//                    System.out.println("get what?");
//                } else if (roomItem.containsValue(noun)) {
//                    myInventory.add(roomItem.get("name"));
//                    currentLocation.getItems().remove("name");
//                    System.out.println("You got a " + noun + "!");
//                } else {
//                    System.out.println("There is no " + noun);
//                }
//
//
//                break;

            case "help":
                // handle get command
                System.out.println("Commands:"
                    + "\n" + "Go - move around"
                    + "\n" + "Look - Look at something"
                    + "\n" + "Get - pick up stuff"
                    + "\n" + "Help - see commands again");
                break;

            default:
                System.out.println("Invalid command. Type 'help' for a list of available commands.");
                break;
        }
    }

    public static void showStatus() {
        System.out.println("--------------------------------------");
        System.out.println("Location: " + currentLocation.getName());
        System.out.println("Directions: " + currentLocation.getDirections().keySet());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Inventory: " + player.getInventory());
        System.out.println("--------------------------------------");
    }

    public static void roomDescription() {
        System.out.println(currentLocation.getDescription());
        System.out.println("--------------------------------------");
    }


    public static void showItem() {

        List<Item> itemList = currentLocation.getItems();
        if (itemList.isEmpty()) {
            System.out.println("There is no items here");
        } else {
            for (Item element : itemList) {
                System.out.println("You see the item name of " + element.getName());
            }
        }
    }

    public static void gameIntro() {
        //Display commands
        System.out.println("--------------------------------------");
        System.out.println("Commands:"
            + "\n" + "Go - move around"
            + "\n" + "Look - Look at something"
            + "\n" + "Get - pick up stuff"
            + "\n" + "Help - see commands again");
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