import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnjastrow on 2/1/17.
 */
public class Player extends Character {
    String weapon;
    String location;
    List<String> items = new ArrayList<>();

    public Player() {
        this.health = 20;
        this.damage = 20;
    }

    public void chooseName() {
        System.out.println("What is your Name?");
        name = Game.nextLine();
        System.out.println("Welcome, " + name);
    }

    public void chooseWeapon() throws Exception {
        System.out.println("Choose your weapon [sword/mace]");
        weapon = Game.nextLine();

        switch (weapon.toUpperCase()) {
            case "SWORD" :
                System.out.println("Sword is a good choice!");
                break;
            case "MACE" :
                System.out.println("Mace is another good choice.");
                break;
            default:
                throw new Exception("Invalid weapon!!");
        }
    }

    public void chooseLocation() throws Exception {
        System.out.println("Choose your location [forest/tunnel]");
        location = Game.nextLine();

        if (location.equalsIgnoreCase("FOREST")){
            System.out.println("Entering the forest...");
        }
        else if (location.equalsIgnoreCase("TUNNEL")) {
            System.out.println("Enter tunnel...");
        }
        else throw new Exception("Invalid location!");

    }

    public void findItem(String item) {
        System.out.printf("You found a %s! Pick it up? [y/n]", item);
        String answer = Game.nextLine().toLowerCase();
        if (answer.equals("y")){
            items.add(item);
            System.out.println("You picked up an item!");
        }
        else System.out.println("(Leaving that item)");
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
