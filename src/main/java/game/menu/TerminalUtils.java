package game.menu;

import java.io.IOException;

public class TerminalUtils {
    private static String originalSettings;
    
    public static void saveTerminalState() throws IOException, InterruptedException {
        if (!isWindows()) {
            Process p = new ProcessBuilder("sh", "-c", "stty -g </dev/tty").start();
            originalSettings = new String(p.getInputStream().readAllBytes()).trim();
        }
    }

    public static void resetTerminalOverkill() throws IOException, InterruptedException {
        if (isWindows()) {
            new ProcessBuilder("cmd", "/c", "cls").start().waitFor();
        } else {
            new ProcessBuilder("sh", "-c", "reset").start().waitFor();
        }
    }

    public static void resetTerminal() throws IOException, InterruptedException {
        if (isWindows()) {
            // Windows-specific reset
            new ProcessBuilder("cmd", "/c", "cls").start().waitFor();
        } else {
            // Restore original settings AND ensure clean slate
            new ProcessBuilder("sh", "-c", "stty " + originalSettings + " </dev/tty && stty sane").start().waitFor();
            System.out.print("\033[?25h\033[0m"); // Show cursor and reset formatting
        }
    }

    public static void prepareForLanterna() throws IOException, InterruptedException {
        if (!isWindows()) {
            // Full Lanterna preparation including raw mode
            new ProcessBuilder("sh", "-c", "stty -echo -icanon -isig -ixon -ixoff").start().waitFor();
        }
        System.out.print("\033[?25l"); // Hide cursor
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
