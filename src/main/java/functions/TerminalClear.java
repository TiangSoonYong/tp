package functions;

public class TerminalClear {
    /**
     * Buffer for reducing flickering on Windows terminals
     */
    public static void clearAndWrite(String content) {
        System.out.print("\033[H\033[2J" + content);
        System.out.flush();
    }
}
