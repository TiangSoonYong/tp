package Functionalities;

import Game.Characters.Player;
import Game.Characters.Enemy;
import Game.Characters.Character;

public class UI {
    public static String lineSeparator = "======================================================================";
    public static String logo =
            " ____    " + "  ___   " + " _      " + " _      " + "    _    " + " ____   " + " ___  " + " _____  " + "\n" +
            "|  _ \\   " + " / _ \\  " + "| |     " + "| |     " + "   / \\   " + "|  _ \\  " + "|_ _| " + "| ____| " + "\n" +
            "| |_) |  " + "| | | | " + "| |     " + "| |     " + "  / _ \\  " + "| | | | " + " | |  " + "|  _|   " + "\n" +
            "|  _ <   " + "| |_| | " + "| |___  " + "| |___  " + " / /_\\ \\ " + "| |_| | " + " | |  " + "| |___  " + "\n" +
            "|_| \\_\\  " + " \\___/  " + "|_____| " + "|_____| " + "/_/   \\_\\" + "|____/  " + "|___| " + "|_____| ";

    public String playerModel =
            "";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to");
        System.out.println(lineSeparator);
        System.out.println(logo);
        System.out.println(lineSeparator);
        System.out.println("A text-based RPG game where your fate is determined by the roll of a die!!");
    }
    public void printPlayerInfo(Player player) {
        System.out.println();
    }

    public void battleEntry(Enemy enemy) {
        System.out.println("A villainous " + enemy.getCharacterName() + " stands in your way!");

    }

    public void battleExit(Enemy enemy, Player player) {
        if (!player.isAlive) {
            System.out.println(enemy.getCharacterName() + " has defeated you. Another one bites the dust!");
            return;
        } else {
            System.out.println("You have vanquished " + enemy.getCharacterName());
        }

        if (player.getHealthBars()[0] > 90) {
            System.out.println("\"Ha! that was easy!\", you thought to yourself, unaware of greater dangers that lurk ahead...");
        } else if (player.getHealthBars()[0] > 50) {
            System.out.println("You dust yourself off, ready for whatever challenge comes your way...");
        } else {
            System.out.println("You stumble away from the battlefield, severely wounded...");
        }
    }

    public void printPlayerAttack(Player player, Enemy enemy, int damage) {
        System.out.println("You punch the " + enemy.getCharacterName() + " with your bare fist!");
        if (damage > 30) {
            System.out.println("WHAMMM!! The " + enemy.getCharacterName() + " took a whopping " + damage + " damage!");
        } else if (damage > 10) {
            System.out.println("You dealt " + damage + " damage.");
        } else {
            System.out.println("That tickled the " + enemy.getCharacterName() + ". You dealt a measly " + damage + " damage.");
        }
    }

    public void printEnemyAttack(Player player, Enemy enemy, int damage) {
        System.out.println("The " + enemy.getCharacterName() + " lunges forward and attacks!");
        if (damage > 30) {
            System.out.println("Ouch!! The " + enemy.getCharacterName() + " nearly sends you flying! It dealt " + damage + " damage!");
        } else if (damage > 10) {
            System.out.println("It dealt " + damage + " damage.");
        } else {
            System.out.println("You barely felt that attack... the " + enemy.getCharacterName() + " dealt only " + damage + " damage.");
        }
    }

    public void printCharacterInfo(Character c) {
        System.out.println(c.getCharacterName());
        System.out.print("Heath: ");
        int[] healthbars = c.getHealthBars();
        for (int health : healthbars) {
            int maxhealth = 100;
            System.out.print("[");
            for (int i = 0; i < health; i++) {
                System.out.print("#");
            }
            for (int i = health; i < maxhealth; i++) {
                System.out.print("_");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}
