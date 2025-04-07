package game;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import exceptions.RolladieException;
import storage.Storage;
import functionalities.ui.UI;
import game.menu.MenuSystem;
import game.menu.MenuSystem.MenuAction;
import game.menu.TerminalUtils;

/**
 * Entry point of the Rolladie application
 * Initializes the main menu and starts interactions with the user
 */

public class Rolladie {

    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";
        UI.printWelcomeMessage();

        String userInput = UI.readInput();
        Game game;
        while(!userInput.equals("exit")) {
            try {
                switch (userInput) {
                case "start":
                    game = new Game();
                    break;
                case "load":
                    game = Storage.loadGame();
                    break;
                default:
                    throw new RolladieException("Invalid option");
                }
                game.run();
                UI.printWelcomeMessage();
            } catch (RolladieException e) {
                UI.printErrorMessage(e.getMessage());
            } finally {
                userInput = UI.readInput();
            }
        }
        UI.printExitMessage();
    }
}
