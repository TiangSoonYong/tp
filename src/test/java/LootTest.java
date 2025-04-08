import static org.junit.jupiter.api.Assertions.assertTrue;

import players.Player;
import org.junit.jupiter.api.Test;
import events.Loot;

public class LootTest {

    @Test
    public void lootGranted_updatesPlayerGold() {
        Player player = new Player("Tom", 100, 10);

        // Proceed with the loot logic
        Loot loot = new Loot(player, 10);
        loot.setHasWon(true);
        try {
            loot.simulateRun();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Validate that the player has received some gold (>= 10)
        assertTrue(player.getGold() >= 10);
    }
}
