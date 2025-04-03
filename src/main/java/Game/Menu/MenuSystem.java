package Game.Menu;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import Exceptions.RolladieException;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuSystem {
    private Terminal terminal = null;
    public MenuSystem() {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            // terminal.enterPrivateMode();
            // terminal.clearScreen();
            // terminal.setCursorVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean displayMenu(String title, Map<String, MenuAction> options) throws IOException {
        String[] optionTitles = options.keySet().toArray(new String[0]);

        Menu menu = new Menu(terminal, title, optionTitles);

        while (true) {
            int selectedOption = menu.display();
            MenuAction action = options.get(optionTitles[selectedOption]);

            boolean shouldExit = action.execute();
            if (shouldExit) return true;
        }
    }

    public void reinitializeTerminal() throws IOException {
        // Close old terminal
        if (this.terminal != null) {
            this.terminal.close();
        }
        // Create fresh terminal instance
        this.terminal = new DefaultTerminalFactory().createTerminal();
    }

    public void exitPrivateMode() {
        try {
            terminal.exitPrivateMode();
            terminal.setCursorVisible(true);;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterPrivateMode() {
        try {
            terminal.enterPrivateMode();
            terminal.setCursorVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearScreen() {
        try {
            terminal.clearScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean exitApplication() {
        System.exit(0);
        return true;
    }

    @FunctionalInterface
    public interface MenuAction {
        boolean execute();

        static MenuAction wrap(MenuActionWithException action) {
            return () -> {
                try {
                    return action.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            };
        }
    }

    @FunctionalInterface
    public interface MenuActionWithException {
        boolean execute() throws IOException;
    }
}
