import java.util.List;
import java.util.Map;

public class Commands {
    private Player player;
    private static Location currentLocation;

    public Commands(Player player, Location currentLocation) {
        this.player = player;
        this.currentLocation = currentLocation;
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
                                System.out.println("You are in the " + currentLocation.getName());
                                System.out.println("You see a " + currentLocation.getItems());
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
}
