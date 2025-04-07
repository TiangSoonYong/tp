package game;

import events.Loot;
import events.Shop;
import exceptions.RolladieException;
import functions.Storage;
import functions.UI.UI;
import players.Player;
import equipments.armors.ArmorDatabase;
import equipments.boots.BootsDatabase;
import equipments.Equipment;
import equipments.weapons.WeaponDatabase;
import events.Battle;
import events.Event;
import functions.UI.GameOverUI;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Manages all game logic specifically: Event Generation and Sequence
 */
public class Game {
    private static final int MAX_NUMBER_OF_WAVES = 8;
    private Queue<Event> eventsQueue = new LinkedList<>();
    private Player player;
    private Event currentEvent;
    private int wave;
    private int turnsWithoutShop = 0;
    private boolean hasWonCurrBattle = false;

    /**
     * Constructor to instantiate a new game
     * Creates a new player based on a Hero preset
     * Generates the event queue
     * Polls the first event from the queue to be the current event
     */
    public Game() {
        this.player = Player.createNewPlayer();
        this.wave = 1;
        this.eventsQueue = generateEventQueue(this.wave);
        this.currentEvent = nextEvent();
    }

    public int getWave() {
        return this.wave;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * Overloaded constructor used to generate defined game
     * Main usage is within the Storage class to load game from save file
     *
     * @param player
     * @param wave
     */
    public Game(Player player, int wave) {
        this.player = player;
        this.wave = wave;
        this.eventsQueue = generateEventQueue(wave);
        this.currentEvent = nextEvent();
    }

    /**
     * Runs the current game until the event sequence is completed
     * Ends the game prematurely if the player died within the event
     */
    public void run() {
        while (this.currentEvent != null && this.player.isAlive()) {
            try {
                //If current battle is won, sets loot event to give rewards
                currentEvent.setHasWon(hasWonCurrBattle);
                //Saves game on loot or shop screen after a battle.
                if (this.currentEvent instanceof Battle) {
                    saveGame();
                }
                this.currentEvent.run();
                if (this.currentEvent.isExit) {
                    UI.printExitMessage();
                    return;
                }
                if (!player.isAlive()) {
                    break;
                }
                //Checks if current battle is won
                if (this.currentEvent instanceof Battle) {
                    this.wave++;
                    hasWonCurrBattle = currentEvent.getHasWon();
                }
                this.currentEvent = nextEvent();
            } catch (RolladieException | InterruptedException e) {
                UI.printErrorMessage(e.getMessage());
            }
        }
        GameOver gameOver = new GameOver(hasWonCurrBattle);
        gameOver.run();
    }


    /**
     * Returns a filled queue of events
     * Used during the construction of a new game
     *
     * @param start
     * @return eventsQueue
     */
    private Queue<Event> generateEventQueue(int start) {
        Queue<Event> eventsQueue = new LinkedList<>();
        int i;
        for (i = start; i <= MAX_NUMBER_OF_WAVES; i++) {
            eventsQueue.add(generateBattle(i));
            eventsQueue.add(generateLoot((i + 1) * 10));
            if (i % 2 == 0) {
                eventsQueue.add(generateShopEvent(i));
            }
        }
        eventsQueue.add(generateBattle(i + 1));
        return eventsQueue;
    }

    /**
     * Returns a battle to insert into the event queue.
     *
     * @return Event
     */
    private Battle generateBattle(int wave) {
        Battle newBattle = new Battle(this.player, wave);
        return newBattle;
    }

    private Event generateLoot(int loot) {
        return new Loot(this.player, loot);
    }

    public Event generateShopEvent(int wave) {
        Equipment[] equipmentsForSale = {
                ArmorDatabase.getArmorByIndex((wave / 2) - 1),
                BootsDatabase.getBootsByIndex((wave / 2) - 1),
                WeaponDatabase.getWeaponByIndex((wave / 2) - 1),
        };
        return new Shop(this.player, equipmentsForSale);
    }

    /**
     * returns the next event inside the event queue
     *
     * @return Event
     */
    private Event nextEvent() {
        return this.eventsQueue.poll();
    }

    /**
     * Calls the Storage class to save the current game status
     */
    public void saveGame() throws RolladieException {
        UI.printMessage("💾 Save game? (y/n): ");
        String saveInput = UI.readInput();
        if (saveInput.equalsIgnoreCase("y")) {
            int saveSlot = UI.promptSaveFile();
            Storage.saveGame(saveSlot, wave, this.player);
        }
    }

}
