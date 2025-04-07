package functions.UI;

import players.Player;
import functions.DiceBattleAnimation;

public class HpBar {
    /**
     * Prints to console an animation depicting the HP dropping after each round of battle
     * 
     * @param p1 player
     * @param p2 computer-controlled Player
     * @param prevHp1 player's previous HP
     * @param prevHp2 computer's previous HP
     * @param diceDisplay String to keep static on-screen before printing HP bars
     * @throws InterruptedException
     */
    public static void animate(Player p1, Player p2, int prevHp1, int prevHp2, String diceDisplay) throws InterruptedException {
        int target1 = p1.hp;
        int target2 = p2.hp;

        int current1 = prevHp1;
        int current2 = prevHp2;

        int max = Math.max(Math.abs(target1 - current1), Math.abs(target2 - current2));

        for (int i = 0; i <= max; i++) {
            if (current1 != target1)
                current1 += Integer.compare(target1, current1);
            if (current2 != target2)
                current2 += Integer.compare(target2, current2);

            String bar1 = buildBar(current1, p1.maxHp);
            String bar2 = buildBar(current2, p2.maxHp);

            DiceBattleAnimation.clearConsole();
            System.out.print("🎲 Final Rolls:\n" + diceDisplay);
            System.out.println("❤️  HP Status:");
            System.out.printf("%-10s : ", p1.name);
            System.out.println(bar1 + " " + current1 + "/" + p1.maxHp);
            System.out.printf("%-10s : ", p2.name);
            System.out.println(bar2 + " " + current2 + "/" + p2.maxHp);

//            Thread.sleep(Math.min(70 + 2 * i, 300));
            Thread.sleep(40);
        }
    }

    private static String buildBar(int hp, int maxHp) {
        int totalBlocks = 30;
        int filledBlocks = (int) ((hp / (double) maxHp) * totalBlocks);
        String color = getColor(hp, maxHp);

        String bar = "[" + color;
        for (int i = 0; i < totalBlocks; i++) {
            bar += (i < filledBlocks) ? "|" : " ";
        }
        bar += AnsiColor.RESET + "]";
        return bar;
    }

    private static String getColor(int hp, int maxHp) {
        double percent = (double) hp / maxHp;
        if (percent >= 0.7)
            return AnsiColor.GREEN;
        else if (percent >= 0.3)
            return AnsiColor.YELLOW;
        else
            return AnsiColor.RED;
    }
}
