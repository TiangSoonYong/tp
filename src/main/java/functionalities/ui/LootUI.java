package functionalities.ui;

import game.characters.Player;

public class LootUI extends UI {
    public static void printLootEntry() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Narrator: A mysterious chest lies before you... you open it.");
        System.out.println(LINE_SEPARATOR);
    }

    public static void printLootExit(String chest, int amount, Player player) {
        System.out.println("\n" + chest + "\n");
        printPlayerEarnGold(player, amount);
    }
}
