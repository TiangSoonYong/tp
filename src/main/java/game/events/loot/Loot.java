package game.events.loot;

import exceptions.RolladieException;
import functionalities.ui.UI;
import game.characters.Player;
import game.currency.Gold;
import game.events.Event;
import functionalities.ui.LootUI;
import game.RollDice;

public class Loot extends Event {
    private static final int DEFAULT_LOOT = 50;
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


    Loot(Player player) {
        super(player);
    }

    @Override
    public void run() throws RolladieException {
        LootUI.printLootEntry();
        Gold loot = generateRandomLoot();
        player.earnGold(loot);
        LootUI.printLootExit(CHEST, loot.getAmount(), player);
    }

    public Gold generateRandomLoot() throws RolladieException {
        int diceValue = RollDice.rollDice();
        int bonusLoot = RollDice.diceOutcome(diceValue) / 2;
        UI.printMessage("You get a bonus of " + bonusLoot + "gold.");
        return new Gold(DEFAULT_LOOT + bonusLoot);
    }

    @Override
    public String getEventIcon() {
        return "Loot";
    }

    @Override
    public String toText() {
        return this.getEventIcon();
    }
}
