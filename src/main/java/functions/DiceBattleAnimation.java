package functions;

import java.util.Random;

import functions.UI.UI;

public class DiceBattleAnimation {
    private static volatile boolean skipAnimation = false;

    private static final String[][] DICE_ART = {
        {
            " _______ ",
            "|       |",
            "|   O   |",
            "|       |",
            " ------- "
        },
        {
            " _______ ",
            "| O     |",
            "|       |",
            "|     O |",
            " ------- "
        },
        {
            " _______ ",
            "| O     |",
            "|   O   |",
            "|     O |",
            " ------- "
        },
        {
            " _______ ",
            "| O   O |",
            "|       |",
            "| O   O |",
            " ------- "
        },
        {
            " _______ ",
            "| O   O |",
            "|   O   |",
            "| O   O |",
            " ------- "
        },
        {
            " _______ ",
            "| O   O |",
            "| O   O |",
            "| O   O |",
            " ------- "
        }
    };

    public static void main(String[] args) throws InterruptedException {
        int[] player1Faces = {3, 6};
        int[] player2Faces = {1, 5, 2};
        animateBattle(player1Faces, player2Faces);
    }

    public static void animateBattleWithInterrupt(int[] player1Rolls, int[] player2Rolls) throws InterruptedException {
        skipAnimation = false;
        Thread inputThread = new Thread(() -> {
            try {
                // System.in.read();
                UI.nextLine();
                skipAnimation = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        inputThread.setDaemon(true);
        inputThread.start();

        animateBattle(player1Rolls, player2Rolls);
    }

    /**
     * Prints a dice rolling animation to the terminal
     * 
     * @param player1Rolls individual dice results for Player 1
     * @param player2Rolls individual dice results for Player 2
     * @return final dice settled ascii image representation
     * @throws InterruptedException
     */
    public static String animateBattle(int[] player1Rolls, int[] player2Rolls) throws InterruptedException {
        Random rand = new Random();
        int frames = 16;

        for (int frame = 0; frame < frames; frame++) {
            if (skipAnimation) {
                break;
            }
            int[] p1Rolls = randomRolls(player1Rolls.length, rand);
            int[] p2Rolls = randomRolls(player2Rolls.length, rand);
            
            int[] p1Offsets = generateOffsets(player1Rolls.length, frame);
            int[] p2Offsets = generateOffsets(player2Rolls.length, frame + 3); // staggered for variety

            TerminalClear.clearAndWrite("🎲 Dice Rolling...\nYour Hero ......... This Enemy\n");

            // System.out.println("🎲 Dice Rolling...");
            printBattleBoards(p1Rolls, p2Rolls, p1Offsets, p2Offsets);

            Thread.sleep(90);
        }

        // Final result frame
        String finalDiceRender = printBattleBoards(player1Rolls, player2Rolls, new int[player1Rolls.length], 
            new int[player2Rolls.length]);

        TerminalClear.clearAndWrite("🎲 Final Rolls:\nYour Hero ......... This Enemy\n" + finalDiceRender);

        return finalDiceRender + "\n";
    }

    private static int[] randomRolls(int size, Random rand) {
        int[] rolls = new int[size];
        for (int i = 0; i < size; i++) {
            rolls[i] = rand.nextInt(6) + 1;
        }
        return rolls;
    }

    private static int[] generateOffsets(int count, int frame) {
        int[] offsets = new int[count];
        for (int i = 0; i < count; i++) {
            offsets[i] = (int) (Math.sin((frame + i) * 0.5) * 2);
        }
        return offsets;
    }

    private static String printBattleBoards(int[] p1Faces, int[] p2Faces, int[] p1Offsets, int[] p2Offsets) {
        int dieHeight = 5;

        int p1TotalHeight = getMaxOffset(p1Offsets, dieHeight) + p1Faces.length * (dieHeight + 1);
        int p2TotalHeight = getMaxOffset(p2Offsets, dieHeight) + p2Faces.length * (dieHeight + 1);
        int maxHeight = Math.max(p1TotalHeight, p2TotalHeight);

        StringBuilder[] lines = new StringBuilder[maxHeight];
        for (int i = 0; i < maxHeight; i++) {
            lines[i] = new StringBuilder();
        }

        // Draw Player 1 (left side)
        int currentP1Line = 0;
        for (int i = 0; i < p1Faces.length; i++) {
            int offset = p1Offsets[i];
            String[] art = DICE_ART[p1Faces[i] - 1];

            for (int j = 0; j < dieHeight; j++) {
                int lineIndex = currentP1Line + offset + j;
                if (lineIndex >= 0 && lineIndex < maxHeight) {
                    lines[lineIndex].append(art[j]);
                }
            }

            currentP1Line += dieHeight + 1;
        }

        // Add spacing between players
        for (StringBuilder line : lines) {
            while (line.length() < 12) line.append(" ");
            line.append("  |   ");
        }

        // Draw Player 2 (right side)
        int currentP2Line = 0;
        for (int i = 0; i < p2Faces.length; i++) {
            int offset = p2Offsets[i];
            String[] art = DICE_ART[p2Faces[i] - 1];

            for (int j = 0; j < dieHeight; j++) {
                int lineIndex = currentP2Line + offset + j;
                if (lineIndex >= 0 && lineIndex < maxHeight) {
                    lines[lineIndex].append(art[j]);
                }
            }

            currentP2Line += dieHeight + 1;
        }

        StringBuilder combined = new StringBuilder();

        for (StringBuilder line : lines) {
            if (line.toString().trim().length() > 0) {
                System.out.println(line);
                combined.append(line).append("\n");
            }
        }

        return combined.toString();
    }

    private static int getMaxOffset(int[] offsets, int dieHeight) {
        int max = 0;
        for (int offset : offsets) {
            if (offset + dieHeight > max) {
                max = offset + dieHeight;
            }
        }
        return max;
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
