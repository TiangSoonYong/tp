package functionalities.ui;

import game.Game;
import game.characters.Character;
import game.characters.Player;

import java.util.Scanner;

/**
 * The UI class is responsible for handling the user interface in the text-based RPG game.
 * It displays messages related to the game, player, and enemy interactions, and renders the game's LOGO.
 */
public class UI {
    /**
     * A separator string used to format the output in the UI.
     */
    public static final String LINE_SEPARATOR = "=====================================================================";


    private static final Scanner SCANNER = new Scanner(System.in);


    public static String readInput() {
        String inputLine = SCANNER.nextLine().toLowerCase();
        return inputLine;
    }

    /**
     * A string containing the ASCII art LOGO of the game.
     */
    public static final String LOGO =
        " ____    " + "  ___   " + " _      " + " _      " + "    _    " + " ____   " + " ___  " + " _____  " + "\n" +
        "|  _ \\ " + " / _ \\  " + "| |     " + "| |     " + "   / \\   " + "|  _ \\  " + "|_ _| " + "| ____| " + "\n" +
        "| |_) |  " + "| | | | " + "| |     " + "| |     " + "  / _ \\  " + "| | | | " + " | |  " + "|  _|   " + "\n" +
        "|  _ <   " + "| |_| | " + "| |___  " + "| |___  " + " / /_\\ \\ " + "| |_| | " + " | |  " + "| |___  " + "\n" +
        "|_| \\_\\  " + " \\___/  " + "|_____| " + "|_____| " + "/_/   \\_\\" + "|____/  " + "|___| " + "|_____| ";


    public static final String DIE_FACE_1 =
        " _______ \n" +
        "|       |\n" +
        "|   O   |\n" +
        "|       |\n" +
        " ------- \n";

    public static final String DIE_FACE_2 =
        " _______ \n" +
        "| O     |\n" +
        "|       |\n" +
        "|     O |\n" +
        " ------- \n";

    public static final String DIE_FACE_3 =
        " _______ \n" +
        "| O     |\n" +
        "|   O   |\n" +
        "|     O |\n" +
        " ------- \n";

    public static final String DIE_FACE_4 =
        " _______ \n" +
        "| O   O |\n" +
        "|       |\n" +
        "| O   O |\n" +
        " ------- \n";

    public static final String DIE_FACE_5 =
        " _______ \n" +
        "| O   O |\n" +
        "|   O   |\n" +
        "| O   O |\n" +
        " ------- \n";

    public static final String DIE_FACE_6 =
        " _______ \n" +
        "| O   O |\n" +
        "| O   O |\n" +
        "| O   O |\n" +
        " ------- \n";



    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }


    /**
     * Prints a welcome message to the player, including the game LOGO and a description.
     */
    public static void printWelcomeMessage() {
        System.out.println("Welcome to");
        System.out.println(LINE_SEPARATOR);
        System.out.println(LOGO);
        System.out.println(LINE_SEPARATOR);
        System.out.println("A text-based RPG game where your fate is determined by the roll of a die!!");
    }


    public static void printCharacterInfo(Character c) {
        System.out.println(c);
    }

    public static void printPlayerEarnGold(Player player, int amount) {
        System.out.println("You gained " + amount + " gold. You now have a total of " + player.getGold() + " gold.");
    }

    public static void printPlayerSpentGold(Player player, int amount) {
        System.out.println("You spent " + amount + " gold. You now have a total of " + player.getGold() + " gold.");
    }

    public static void printExitMessage() {
        System.out.println("Narrator: Leaving so soon? I expected more from you!");
    }

    public static void printDeathMessage() {
        System.out.println("Narrator: Game over, you've died! L");
        System.out.println("Press Enter to return to Main Menu...");
        SCANNER.nextLine();
    }

    public static void showContinueScreen(Game game) {
//        int wave = game.getWave();
/*        Player player = game.getPlayer();
        System.out.println("\n===== CONTINUE SCREEN =====");
        System.out.println("👤 Name: " + player.name);
        System.out.println("❤️ HP: " + player.hp + " / " + player.maxHp);
        System.out.println("⚡ Power: " + player.power + " / " + player.maxPower);
        System.out.println("🗡️ Weapon: " + player.weapon.name + " (+" + player.weapon.bonusPerDie + " dmg)");
        System.out.println("🛡️ Armor: " + player.armor.name + " (+" + player.armor.defense + " def)");

        System.out.println("🧪 Abilities:");
        for (Ability a : player.abilities) {
            System.out.printf("   • %s (%s) | Cooldown: %d | Cost: %d\n", a.name, a.icon, a.cooldown, a.powerCost);
        }*/

//        System.out.println("🌊 Current Wave: " + wave);
        System.out.println("\nPress Enter to continue...");
        SCANNER.nextLine();
    }
}

