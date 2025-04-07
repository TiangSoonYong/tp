package storage;

import exceptions.RolladieException;
import functionalities.ui.UI;
import game.characters.Character;
import game.characters.Enemy;
import game.characters.Player;
import game.equipment.ArmorDatabase;
import game.equipment.BootsDatabase;
import game.equipment.Equipment;
import game.equipment.EquipmentList;
import game.equipment.WeaponDatabase;
import game.events.battle.Battle;
import game.events.Event;
import game.Game;
import game.events.loot.Loot;
import game.events.shop.Shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Scanner;

/**
 * Translates the game data from and into text save file
 * savefile.txt is structured as such:
 * 1. player
 * 2. currentEvent
 * 3. remaining events in eventsQueue
 */
public class Storage {
    public static final String SAVE_DELIMITER = " | ";
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "savefile.txt";
    private static final String LOAD_DELIMITER = " \\| ";


    /**
     * Saves the attributes of the game into a text file
     * defined by FILE_NAME in FILE_DIRECTORY
     *
     * @param player       The player involved in the event.
     * @param currentEvent Event that is currently being processed in the game.
     * @param eventsQueue  Queue of upcoming events that need to be saved.
     */
    public static void saveGame(Player player, Event currentEvent, Queue<Event> eventsQueue) throws RolladieException {
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(FILE_DIRECTORY, FILE_NAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);

            // Encoding and saving of player data
            String playerText = player.toText();
            fw.write(playerText + System.lineSeparator());

            // Encoding and saving of currentEvent data
            String currentEventText = currentEvent.toText();
            fw.write(currentEventText + System.lineSeparator());

            // Encoding and saving of remaining event in eventsQueue
            for (Event event : eventsQueue) {
                String textToAdd = event.toText();
                fw.write(textToAdd + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new RolladieException("savefile.txt failed to save");
        }
    }

    private static Equipment[] parseEquipmentArrayFromText(String[] parameters) throws RolladieException {
        ArrayList<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < parameters.length; i += 2) {
            String equipmentType = parameters[i];
            String equipmentName = parameters[i + 1];
            switch (equipmentType) {
            case "armor":
                equipments.add(ArmorDatabase.getArmorByName(equipmentName));
                break;
            case "boots":
                equipments.add(BootsDatabase.getBootsByName(equipmentName));
                break;
            case "weapon":
                equipments.add(WeaponDatabase.getWeaponByName(equipmentName));
                break;
            default:
                throw new RolladieException("Invalid equipment type");
            }
        }
        return (Equipment[]) equipments.toArray();
    }

    private static EquipmentList parseEquipmentListFromText(String[] parameters) throws RolladieException {
        Optional<Equipment> armor = Optional.empty();
        Optional<Equipment> boots = Optional.empty();
        Optional<Equipment> weapon = Optional.empty();
        for (int i = 0; i < parameters.length; i += 2) {
            String equipmentType = parameters[i];
            String equipmentName = parameters[i + 1];
            if(!equipmentName.equals("empty")) {
                switch (equipmentType) {
                case "armor":
                    armor = Optional.of(ArmorDatabase.getArmorByName(equipmentName));
                    break;
                case "boots":
                    boots = Optional.of(BootsDatabase.getBootsByName(equipmentName));
                    break;
                case "weapon":
                    weapon = Optional.of(WeaponDatabase.getWeaponByName(equipmentName));
                    break;
                default:
                    throw new RolladieException("Invalid equipment type");
                }
            }
        }
        return new EquipmentList(List.of(armor, boots, weapon));
    }

    /**
     * Returns subclass of Character defined by characterType
     * Decodes Character from text within savefile
     *
     * @param characterType Type of the character to be parsed, such as "Player" or "Enemy".
     * @param parameters    The parameters from the save file,
     * @return subclass of Character
     * @throws RolladieException If the characterType is invalid or error parsing the character's attributes.
     */
    private static Character parseCharacterFromText(String characterType, String[] parameters)
            throws RolladieException {
        String healthBarsString = parameters[0];
        // https://stackoverflow.com/questions/7646392/convert-string-to-int-array-in-java
        String[] values = healthBarsString.replaceAll("\\[", "")
                .replaceAll("\\]", "").replaceAll("\\s", "").split(",");
        int[] healthBars = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            try {
                healthBars[i] = Integer.parseInt(values[i]);
            } catch (NumberFormatException nfe) {
                throw new RolladieException("Parsing failed");
            }
        }
        int attackValue = Integer.parseInt(parameters[1]);
        int defenseValue = Integer.parseInt(parameters[2]);
        String characterName = parameters[3];
        int maxHealth = Integer.parseInt(parameters[4]);
        if (characterType.equals("Player")) {
            int amount = Integer.parseInt(parameters[5]);
            String[] equipmentParameters = Arrays.copyOfRange(parameters, 6, parameters.length);
            EquipmentList equipments = parseEquipmentListFromText(equipmentParameters);
            return new Player(healthBars, attackValue, defenseValue, characterName, maxHealth, amount, equipments);
        } else if (characterType.equals("Enemy")) {
            return new Enemy(healthBars, attackValue, defenseValue, characterName, maxHealth);
        } else {
            throw new RolladieException("Invalid Character Type");
        }
    }

    /**
     * Returns subclass of Event defined by eventType
     * Decodes Event from text within savefile
     *
     * @param player     The player involved in the event.
     * @param parameters The parameters from the save file, where the first element specifies the event type.
     * @return subclass of Event
     * @throws RolladieException If the eventType is not recognized or if there is an issue parsing the event details.
     */
    private static Event parseEventFromText(Player player, String[] parameters) throws RolladieException {
        String eventType = parameters[0];
        switch (eventType) {
        case "Battle":
            try {
                Enemy enemy = (Enemy) parseCharacterFromText("Enemy", Arrays.copyOfRange(parameters,
                        1, parameters.length + 1));
                return new Battle(player, enemy);
            } catch (RolladieException e) {
                System.out.println(e.getMessage());
            }
        case "Loot":
            return new Loot(player);
        case "Shop":
            Equipment[] equipments = parseEquipmentArrayFromText(Arrays.copyOfRange(parameters, 1, parameters.length + 1));
            return new Shop(player, equipments);
        default:
            throw new RolladieException("Invalid Event Type");
        }
    }

    /**
     * Returns Game object after decoding text from savefile into game parameters
     *
     * @return Game
     */
    public static Game loadGame() throws RolladieException {
        File f = new File(FILE_DIRECTORY + FILE_NAME);
        Scanner s;
        try {
            s = new Scanner(f);
            // 1st line is player data
            String[] playerData = s.nextLine().split(LOAD_DELIMITER);
            Player player = (Player) parseCharacterFromText("Player", playerData);

            // 2nd line is currentEvent data
            String[] currentEventData = s.nextLine().split(LOAD_DELIMITER);
            Event currentEvent = parseEventFromText(player, currentEventData);

            // 3rd line onwards is eventsQueue data
            Queue<Event> eventsQueue = new LinkedList<>();
            while (s.hasNext()) {
                String[] remainingLine = s.nextLine().split(LOAD_DELIMITER);
                Event event = parseEventFromText(player, remainingLine);
                eventsQueue.add(event);
            }
            return new Game(player, currentEvent, eventsQueue);

        } catch (FileNotFoundException e) {
            throw new RolladieException("savefile.txt not found!");
        } catch (RolladieException e) {
            UI.printErrorMessage("Error:" + e.getMessage() + "\nStarting new game");
        }
        return new Game();
    }
}