package functions;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import equipments.Equipment;
import equipments.armors.Armor;
import equipments.armors.ArmorDatabase;
import equipments.boots.Boots;
import equipments.boots.BootsDatabase;
import equipments.weapons.Weapon;
import equipments.weapons.WeaponDatabase;
import exceptions.RolladieException;
import functions.UI.UI;
import game.Game;
import players.Player;

/**
 * Translates the game data from and into text save file
 */
public class Storage {
    public static final String SAVE_DELIMITER = " | ";
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "savefile";
    private static final String FILE_TYPE = ".txt";
    private static final String LOAD_DELIMITER = " \\| ";

    /**
     * Saves the attributes of the game into a text file
     * defined by FILE_NAME + saveSLOT + FILE_TYPE in FILE_DIRECTORY
     *
     * @param saveSlot
     * @param player
     * @param wave
     * @throws RolladieException
     */
    public static void saveGame(int saveSlot, int wave, Player player) throws RolladieException {
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filename = FILE_NAME + saveSlot + FILE_TYPE;
        File file = new File(FILE_DIRECTORY, filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);

            String waveText = Integer.toString(wave);
            fw.write(waveText + System.lineSeparator());

            String playerText = player.toText();
            fw.write(playerText + System.lineSeparator());

            fw.close();
            UI.printMessage("✅ Game saved to save slot " + saveSlot);
        } catch (IOException e) {
            throw new RolladieException("❌ Save failed: " + e.getMessage());
        }
    }

    /**
     * Returns Game object after decoding text from savefile into game parameters
     *
     * @return Game
     */
    public static Game loadGame(int saveSlot) throws RolladieException {
        String filename = FILE_NAME + saveSlot + FILE_TYPE;
        File f = new File(FILE_DIRECTORY + filename);
        Scanner s;
        try {
            s = new Scanner(f);

            int wave = Integer.parseInt(s.nextLine().trim());

            String[] playerData = s.nextLine().split(LOAD_DELIMITER);
            Player player = parsePlayerFromText(wave, playerData);

            UI.printMessage("✅ Game loaded from save slot " + saveSlot);
            return new Game(player, wave);

        } catch (FileNotFoundException e) {
            throw new RolladieException("savefile.txt not found!");
        } catch (RolladieException e) {
            UI.printErrorMessage("❌ Load failed: " + e.getMessage() + "\nStarting new game instead");
        }
        return new Game();
    }

    /**
     * Returns player object defined by playerData
     * Decodes player from text within savefile
     *
     * @param wave
     * @param playerData
     * @return
     * @throws RolladieException
     */
    private static Player parsePlayerFromText(int wave, String[] playerData) throws RolladieException {
        String name = playerData[0];
        int hp = Integer.parseInt(playerData[1]);
        int maxHp = Integer.parseInt(playerData[2]);
        int baseAttack = Integer.parseInt(playerData[3]);
        int numDice = Integer.parseInt(playerData[4]);
        List<Equipment> equipmentList = parseEquipmentListFromText(Arrays.copyOfRange(playerData, 5, 8));
        int gold = Integer.parseInt(playerData[8]);
        int power = Integer.parseInt(playerData[9]);
        int maxPower = Integer.parseInt(playerData[10]);

        return new Player(wave, name, hp, maxHp, baseAttack, numDice, equipmentList, gold, power, maxPower);
    }


    /**
     * Returns list of equipment defined by equipmentsData
     * Intermediate operation for parsing player
     *
     * @param equipmentsData
     * @return
     * @throws RolladieException
     */
    private static List<Equipment> parseEquipmentListFromText(String[] equipmentsData) throws RolladieException {
        int defaultIndex = -1;
        Equipment armor = ArmorDatabase.getArmorByIndex(defaultIndex);
        Equipment boots = BootsDatabase.getBootsByIndex(defaultIndex);
        Equipment weapon = WeaponDatabase.getWeaponByIndex(defaultIndex);

        for (int i = 0; i < equipmentsData.length; i++) {
            String[] equipmentText = equipmentsData[i].split(" ");
            String equipmentType = equipmentText[0];
            int equipmentIndex = Integer.parseInt(equipmentText[1]);
            switch (equipmentType) {
            case Armor.EQUIPMENT_TYPE:
                armor = ArmorDatabase.getArmorByIndex(equipmentIndex);
                break;
            case Boots.EQUIPMENT_TYPE:
                boots = BootsDatabase.getBootsByIndex(equipmentIndex);
                break;
            case Weapon.EQUIPMENT_TYPE:
                weapon = WeaponDatabase.getWeaponByIndex(equipmentIndex);
                break;
            default:
                throw new RolladieException("Invalid equipment type");
            }
        }
        return List.of(armor, boots, weapon);
    }
}
