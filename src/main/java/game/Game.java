package game;

import exceptions.RolladieException;
import storage.Storage;
import functionalities.ui.UI;
import game.characters.Player;
import game.equipment.ArmorDatabase;
import game.equipment.BootsDatabase;
import game.equipment.Equipment;
import game.equipment.WeaponDatabase;
import game.events.battle.Battle;
import game.events.shop.Shop;
import game.events.Event;

import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * Manages all game logic specifically: Event Generation and Sequence
 */
public class Game {
    private static final int MAX_NUMBER_OF_EVENTS = 5;
    private Queue<Event> eventsQueue = new LinkedList<>();
    private Player player;
    private Event currentEvent;
    private int score = 0;
    private int turnsWithoutShop = 0;

    /**
     * Constructor to instantiate a new game
     * Creates a new player based on a Hero preset
     * Generates the event queue
     * Polls the first event from the queue to be the current event
     */
    public Game() {
        this.player = new Player(new int[]{100}, 10, 10, "Hero");
        this.eventsQueue = generateEventQueue();
        this.currentEvent = nextEvent();
    }

    /**
     * Overloaded constructor used to generate defined game
     * Main usage is within the Storage class to load game from save file
     * @param player
     * @param currentEvent
     * @param eventsQueue
     */
    public Game(Player player, Event currentEvent, Queue<Event> eventsQueue) {
        this.player = player;
        this.eventsQueue = eventsQueue;
        this.currentEvent = currentEvent;
    }

    /**
     * Runs the current game until the event sequence is completed
     * Ends the game prematurely if the player died within the event
     */
    public void run() {
        while(this.currentEvent!=null && this.player.isAlive) {
            try {
                saveGame();
                this.currentEvent.run();
                if (player.isAlive) {
                    optionalShopEvent();
                }
                this.currentEvent = nextEvent();
            } catch (RolladieException e) {
                UI.printErrorMessage(e.getMessage());
            }
        }
        if (!this.player.isAlive) {
            UI.printDeathMessage();
        }

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
    }


    /**
     * Returns a filled queue of events
     * Used during the construction of a new game
     * @return eventsQueue
     */
    private Queue<Event> generateEventQueue() {
        Queue<Event> eventsQueue = new LinkedList<>();
        for (int i = 0; i < MAX_NUMBER_OF_EVENTS; i++) {
            eventsQueue.add(generateEvent(i));
        }
        return eventsQueue;
    }

    /**
     * Returns an event to insert into the event queue
     * Current version only has a Battle event
     * Future development would include a more robust event generation
     * Idea: Interleaving the event queue with Battle and Non-Battle events
     * @return Event
     */
    private Event generateEvent(int turn) {
        return new Battle(this.player, turn);
    }

    /**
     * returns the next event inside the event queue
     * @return Event
     */
    private Event nextEvent() {
        return this.eventsQueue.poll();
    }

    /**
     * Calls the Storage class to save the current game status
     */
    private void saveGame() throws RolladieException {
        Storage.saveGame(this.player, this.currentEvent, this.eventsQueue);
    }

    private void optionalShopEvent() {
        if (Math.random() <= (0.3 + 0.2 * turnsWithoutShop)) {
            turnsWithoutShop = 0;
            // shop entered
            Equipment[] equipmentsForSale = {
                ArmorDatabase.getArmorByIndex((int) (Math.random() * ArmorDatabase.getNumberOfArmorTypes())),
                BootsDatabase.getBootsByIndex((int) (Math.random() * BootsDatabase.getNumberOfBootsTypes())),
                WeaponDatabase.getWeaponByIndex((int) (Math.random() * WeaponDatabase.getNumberOfWeaponTypes()))
            };
            
            try {
                new Shop(player, equipmentsForSale).run();
            } catch (RolladieException e) {
                e.printStackTrace();
            }
        } else {
            // shop not provisioned
            turnsWithoutShop++;
        }
    }
}
