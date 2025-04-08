package functions.ui;

public class LootUI {
    private static final String CHEST =
            "                            _.--.\n" +
                    "                        _.-'_:-'||\n" +
                    "                    _.-'_.-::::'||\n" +
                    "               _.-:'_.-::::::'  ||\n" +
                    "             .'`-.-:::::::'     ||\n" +
                    "            /.'`;|:::::::'      ||_\n" +
                    "           ||   ||::::::'     _.;._'-._\n" +
                    "           ||   ||:::::'  _.-!oo @.!-._'-.\n" +
                    "           \'.  ||:::::.-!()oo @!()@.-'_.|\n" +
                    "            '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n" +
                    "              `>'-.!@%()@'@_%-'_.-o _.|'||\n" +
                    "               ||-._'-.@.-'_.-' _.-o  |'||\n" +
                    "               ||=[ '-._.-\\U/.-'    o |'||\n" +
                    "               || '-.]=|| |'|      o  |'||\n" +
                    "               ||      || |'|        _| ';\n" +
                    "               ||      || |'|    _.-'_.-'\n" +
                    "               |'-._   || |'|_.-'_.-'\n" +
                    "                '-._'-.|| |' `_.-'\n" +
                    "                    '-.||_/.-'";


    /**
     * Prints the loot that the player gets.
     * @param gold the amount of gold player gets.
     */
    public static void printLoot(int gold) {
        System.out.println(CHEST);
        System.out.println("You got " + gold + " gold!");
    }

    public static void printNoLoot() {
        System.out.println("As you did not win the battle, you did not get anything...");
    }

    public static void halt() {
        System.out.println("\nPress Enter to continue...");
        UI.nextLine();
    }
}
