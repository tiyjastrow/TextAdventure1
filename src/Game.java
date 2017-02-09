import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    static Player player = new Player();

    public static void main(String[] args) throws Exception {

        System.out.println("Just before");
        System.out.println("UPDATED, Welcome traveler!");

        try {
            player = loadGame();
            System.out.println("Loaded saved game.");
            System.out.println("Player.name = " + player.name);
        } catch (Exception e) {
            System.out.println("Starting new game.");
        }

        if (player.name == null ) player.chooseName();

        while (true) {
            player.chooseWeapon();
            player.chooseLocation();

            if (player.items.isEmpty()) {
                player.findItem("shield");
                player.findItem("boots");
                player.findItem("belt");
            }

            Enemy enemy = new Enemy("Ogre", 20, 15);
            player.battle(enemy);

            if (allDone()) break;
        }
    }

    public static void saveGame() throws IOException {
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.include("items").serialize(player);
        System.out.println("..IN saveGame()..\t player.name = " + player.name);
        System.out.println("..IN saveGame()..\t first item = " + player.items.get(0));
        System.out.println(" JSON = " + json);

        File f = new File("game.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    public static Player loadGame() throws FileNotFoundException {
        File f = new File("game.json");
        Scanner s = new Scanner(f);
        s.useDelimiter("\\Z");
        String contents = s.next();
        s.close();

        JsonParser p = new JsonParser();
        return p.parse(contents, Player.class);
    }

    private static boolean allDone() {
        System.out.printf("Nice game %s. Continue playing? ", player.name);
        return ! nextLine().equalsIgnoreCase("y");
    }

    public static String nextLine() {
        String line = scanner.nextLine();
        while (line.startsWith("/")) {
            switch (line) {
                case "/save":
                    try {
                        saveGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Saved game.");
                case "/inv":
                    for (String item : player.items) {
                        System.out.println(item);
                    }
                    break;
                case "/exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command not found!");
            }
            line = scanner.nextLine();
        }
        return line;
    }
}
