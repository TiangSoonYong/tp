package game;

import functions.ui.GameOverUI;
import functions.ui.UI;

public class GameOver {
    private boolean hasWon;
    public GameOver(boolean hasWon) {
        this.hasWon = hasWon;
    }
    public void run() {
        if (hasWon) {
            GameOverUI.printVictory();
            System.out.println("You have won the game! Enter \"enter\" to go back to main menu.");
            UI.nextLine();;
        } else {
            GameOverUI.printGameOver();
            System.out.println("You have lost the game! Enter \"enter\" to go back to main menu.");
            UI.nextLine();;
        }
    }
}
