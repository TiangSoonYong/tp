package functions;

import equipments.Equipment;
import equipments.armors.Tshirt;
import equipments.boots.Slippers;
import equipments.weapons.Stick;
import exceptions.RolladieException;
import game.Game;
import players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    Player testPlayer;
    List<Equipment> testEquipments = new ArrayList<Equipment>(List.of(new Tshirt(), new Slippers(), new Stick()));
    String testFileDirectory = "src/test/data/StorageTest/";
    String testFileName = "testFile";
    Storage testStorage = new Storage(testFileDirectory, testFileName);

    @Test
    public void saveAndLoad_equals() throws RolladieException {
        testPlayer = generateTestPlayer();
        int saveSlot = 100;
        int testWave = saveSlot;
        testStorage.saveGame(saveSlot, testWave, testPlayer);
        Game loadedGame = testStorage.loadGame(saveSlot);
        Player loadedPlayer = loadedGame.getPlayer();
        int loadedWave = loadedGame.getWave();
        assertEquals(loadedWave, testWave);
        assertEquals(loadedPlayer.name, testPlayer.name);
        assertEquals(loadedPlayer.hp, testPlayer.hp);
        assertEquals(loadedPlayer.maxHp, testPlayer.maxHp);
        assertEquals(loadedPlayer.baseAttack, testPlayer.baseAttack);
        assertEquals(loadedPlayer.diceRolls.length, testPlayer.diceRolls.length);
        assertEquals(loadedPlayer.equipmentList, testPlayer.equipmentList);
        assertEquals(loadedPlayer.gold, testPlayer.gold);
        assertEquals(loadedPlayer.power, testPlayer.power);
        assertEquals(loadedPlayer.maxPower, testPlayer.maxPower);
        assertEquals(loadedPlayer.abilities.size(), testPlayer.abilities.size());
    }

    @Test
    public void invalidEquipment_startNewGame() {
        int saveSlot = 101;
        assertThrows(NoSuchElementException.class, () -> testStorage.loadGame(saveSlot));
    }

    @Test
    public void invalidSaveSlot_throwException() {
        int saveSlot = 102;
        assertThrows(RolladieException.class, () -> testStorage.loadGame(saveSlot));
    }

    @Test
    public void invalidFilePath_throwException() {
        String testFileDirectory = "INVALID";
        testStorage = new Storage(testFileDirectory, testFileName);
        testPlayer = generateTestPlayer();
        assertThrows(RolladieException.class, () -> testStorage.saveGame(0,0, testPlayer));
    }

    private Player generateTestPlayer() {
        int testWave = 100;
        Random random = new Random();
        String name = "test";
        int hp = random.nextInt();
        int maxHp = random.nextInt();
        int baseAttack = random.nextInt();
        int numDice = 2;
        int gold = random.nextInt();
        int power = random.nextInt();
        int maxPower = random.nextInt();
        return new Player(testWave, name, hp, maxHp, baseAttack, numDice, testEquipments, gold, power, maxPower);
    }
}
