import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private List<String> inventory;
    private Location location;

    public Player(String name, int health, Location location) {
        this.name = name;
        this.health = health;
        this.inventory = new ArrayList<>();
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}

