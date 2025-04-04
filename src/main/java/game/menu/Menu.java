package game.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Menu {
    private final Terminal terminal;
    private final String title;
    private final String[] options;
    private int selectedOption = 0;
    private boolean isFirstRun = true;

    public Menu(Terminal terminal, String title, String[] options) {
        this.terminal = terminal;
        this.title = title;
        this.options = options;
    }

    public int display() throws IOException {
        while (true) {
            if (isFirstRun) {
                terminal.clearScreen();
                isFirstRun = false;
            }
            drawMenu();

            KeyStroke keyStroke = terminal.readInput();
            switch (keyStroke.getKeyType()) {
            case ArrowUp -> {
                selectedOption = Math.max(0, selectedOption - 1);
                terminal.clearScreen();
            }
            case ArrowDown -> {
                selectedOption = Math.min(options.length - 1, selectedOption + 1);
                terminal.clearScreen();
            }
            case Enter -> {
                return selectedOption;
            }
            default -> {}
            }
        }
    }

    private void drawMenu() throws IOException {
        drawMenu(10, 3);
    }

    private void drawMenu(int x, int y) throws IOException {
        // terminal.clearScreen();
        terminal.setCursorPosition(x, y);
        terminal.putString(title);

        for (int i = 0; i < options.length; i++) {
            terminal.setForegroundColor((i == selectedOption) ? TextColor.ANSI.GREEN_BRIGHT : TextColor.ANSI.WHITE);
            terminal.setCursorPosition(x, y + 2 + i);
            terminal.putString(options[i]);
            terminal.setForegroundColor(TextColor.ANSI.WHITE);
        }
        terminal.flush();
    }
}
