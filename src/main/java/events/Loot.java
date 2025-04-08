package events;

import functions.ui.Narrator;
import players.Player;
import exceptions.RolladieException;
import functions.ui.LootUI;

import java.util.Random;

public class Loot extends Event {
    private int baseLoot;

    public Loot(Player player, int loot) {
        super(player);
        baseLoot = loot;
    }


    /**
     * Runs the loot event, which generates loot from 30 to 70 gold and returns it to player.
     *
     * @throws RolladieException
     */
    @Override
    public void run() throws RolladieException {
        if (hasWon) {
            int loot = generateRandomLoot();
            player.earnGold(loot);
            LootUI.printLoot(loot);
            Narrator.commentOnLootEntry();
        } else {
            LootUI.printNoLoot();
            Narrator.commentOnLootDefeat();
        }
        LootUI.halt();
    }

    /**
     * Runs a simulation of the loot event for testing purposes.
     * Does not have LootUI.printLoot(loot) as it causes scanner issues with testing.
     * @throws RolladieException
     */
    public void simulateRun() throws RolladieException {
        if (hasWon) {
            int loot = generateRandomLoot();
            player.earnGold(loot);
            LootUI.printLoot(loot);
        } else {
            LootUI.printNoLoot();
        }
    }
    private int generateRandomLoot() throws RolladieException {
        Random rand = new Random();
        int bonusLoot = rand.nextInt(10);
        return baseLoot + bonusLoot;
    }
}
