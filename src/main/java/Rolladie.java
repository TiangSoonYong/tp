/*
 * This source file was generated by the Gradle 'init' task
 */

import functions.Storage;
import game.Game;
import exceptions.RolladieException;

import functions.UI.UI;

public class Rolladie {

    public static void main(String[] args) {
        mainMenu();
    }

    /**
     * Starts the game menu and shows options for new game or loading from save
     */
    public static void mainMenu() {
        UI.printWelcomeMessage();

        UI.printOptions();
        String userInput = UI.readInput();
        Game game;
        while(!userInput.equals("3")) {
            try {
                switch (userInput) {
                case "1":
                    game = new Game();
                    break;
                case "2":
                    int saveSlot = UI.promptSaveFile();
                    try {
                        game = Storage.loadGame(saveSlot);
                        UI.showContinueScreen(game);
                    } catch (RolladieException e) {
                        UI.printErrorMessage(e.getMessage());
                        UI.halt();
                        game = new Game();
                    }
                    break;
                default:
                    throw new RolladieException("Invalid option");
                }
                game.run();
                UI.printWelcomeMessage();
                UI.printOptions();
            } catch (RolladieException e) {
                UI.printErrorMessage(e.getMessage());
            } finally {
                userInput = UI.readInput();
            }
        }
    }
}

