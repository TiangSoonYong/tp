package events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import equipments.Equipment;
import equipments.armors.Armor;
import equipments.weapons.Weapon;
import org.junit.jupiter.api.Test;
import players.Player;
import java.util.List;


class BattleTest {

    private List<Equipment> equipmentList;

    @Test
    public void startGameLoop_noEnemy_assertionThrown() throws InterruptedException, exceptions.RolladieException {

        equipmentList = List.of(new Weapon("Sword", 2),
                new Armor("Leather", 1));
        Player player = new Player("Hero", 100, 5, 2, equipmentList, true);

        try {
            int numberOfEnemy = 0;
            Battle battle = new Battle(player, numberOfEnemy);
            battle.startGameLoop(player, numberOfEnemy);

        } catch (AssertionError e) {
            assertEquals("Number of enemy encountered must be at least 1", e.getMessage());
        }
    }

    @Test
    public void generateNewEnemy_waveEqualsFive_enemyHasCrush() {
        int wave = 5;
        Player enemy = Battle.generateNewEnemy(wave);
        assertTrue(enemy.hasAbility("crush"));
    }

    @Test
    public void generateNewEnemy_waveEqualsZero_assertionThrown() {
        int wave = 0;
        try {
            Battle.generateNewEnemy(wave);
        } catch (AssertionError e) {
            assertEquals("Number of enemy encountered must be at least 1", e.getMessage());
        }
    }

}