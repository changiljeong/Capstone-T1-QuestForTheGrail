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
                // handle "look" command
                if (noun.equals("")) {
                    List<String> items = currentLocation.getItems();
                    if (!items.isEmpty()) {
                        System.out.println("You see the following items:");
                        for (String item : items) {
                            System.out.println("- " + item);
                        }
                    } else {
                        System.out.println("You don't see any items here.");
                    }
                } else {
                    System.out.println("You look at the " + noun + ".");
                    // handle looking at specific object
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

            case "help":
                // handle get command
                System.out.println("Commands:"
                    + "\n" + "Go - move around"
                    + "\n" + "Look - look at something"
                    + "\n" + "Get - pick up stuff"
                    + "\n" + "Help - see commands again");
        }
    }

    public static void showStatus() {
        System.out.println("--------------------------------------");
        System.out.println("Location: " + currentLocation.getName());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Inventory: " + player.getInventory());
        System.out.println("--------------------------------------");
    }

    public static void roomDescription() {
        System.out.println(currentLocation.getDescription());
    }

    public static void gameIntro() {
        //Display commands
        System.out.println("--------------------------------------");
        System.out.println("Commands:"
            + "\n" + "Go - move around"
            + "\n" + "Look - look at something"
            + "\n" + "Get - pick up stuff"
            + "\n" + "Help - see commands again");
        System.out.println("--------------------------------------");

        System.out.println("Let the adventure begin!");

        // Display basic information about the game
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("You walk into a dark, damp dungeon. You are in search of the holy grail.");
        System.out.println("The ancient dragon's minions has stolen the key to open the gate that leads to the Holy Grail!");
        System.out.println("They broke the key into 3 pieces and scattered them throughout the dungeon!");
        System.out.println("Find them to continue your journey towards the Holy Grail!");
        System.out.println("-------------------------------------------------------------------------------");
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
