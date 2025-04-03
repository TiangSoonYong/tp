package Game;

import Exceptions.RolladieException;
import Functionalities.UI.UI;
import java.util.Random;

/**
 * A class to represent the dice.
 */
public class RollDice {
    /**
     * An integer to represent the maximum dice value.
     */
    public static final int MAX = 6;
    /**
     * An integer to represent the minimum dice value.
     */
    public static final int MIN = 1;
    /**
     * No bonus point given for MISS case.
     */
    public static final int MISS = 0;
    /**
     * 10 bonus point given for HIT case.
     */
    public static final int HIT = 10;
    /**
     * 20 bonus point given for CRUCIAL_HIT case.
     */
    public static final int CRUCIAL_HIT = 20;
    private static int first_dice;
    private static int second_dice;

    /**
     * Return the outcome of rolling 2 dice.
     * @return An integer to represent the outcome of rolling dice.
     */
    public static int rollDice(){
        Random rand = new Random();
        first_dice = rand.nextInt(MAX - MIN + 1) + MIN;
        second_dice = rand.nextInt(MAX - MIN + 1) + MIN;

        printDiceImages(first_dice, second_dice);

        return first_dice + second_dice;
    }

    /**
     * Method to print the dice image to the terminal according to the outcome of dice.
     *
     * @param diceValue An integer to represent the current outcome of rolling dice.
     */
    public static void printDiceImage(int diceValue){
        switch (diceValue){
        case 1:
            System.out.println(UI.DIE_FACE_1);
            break;
        case 2:
            System.out.println(UI.DIE_FACE_2);
            break;
        case 3:
            System.out.println(UI.DIE_FACE_3);
            break;
        case 4:
            System.out.println(UI.DIE_FACE_4);
            break;
        case 5:
            System.out.println(UI.DIE_FACE_5);
            break;
        case 6:
            System.out.println(UI.DIE_FACE_6);
            break;
        }
    }

    /**
     * Prints a list of dices side-by-side
     * 
     * @param diceValues list of integer values representing rolled dice value
     */
    public static void printDiceImages(int... diceValues) {
        // Store dice faces as 2D string arrays (each die is 5x7 or similar)
        String[][] diceFaces = {
            UI.DIE_FACE_1.split("\n"),
            UI.DIE_FACE_2.split("\n"),
            UI.DIE_FACE_3.split("\n"),
            UI.DIE_FACE_4.split("\n"),
            UI.DIE_FACE_5.split("\n"),
            UI.DIE_FACE_6.split("\n")
        };
    
        // Get the height of the dice face
        int faceHeight = diceFaces[0].length; 
    
        // Print the dice line by line
        for (int row = 0; row < faceHeight; row++) {
            for (int diceValue : diceValues) {
                System.out.print(diceFaces[diceValue - 1][row] + "  "); // Print each line with spacing
            }
            System.out.println(); // Move to next row
        }
    }
    

    /**
     * Return the effect of rolling dice.
     * Three possible outcome: MISS, HIT, CRUCIAL_HIT
     *
     * @param diceValue Integer to represent the total outcome of rolling 2 dice
     * @return Integer to represent the effect of rolling dice.
     */
    public static int diceOutcome(int diceValue) throws RolladieException{

        if (diceValue < 2 || diceValue > 12) {
            throw new RolladieException("Invalid dice value: must be between 2 and 12.");
        }

        if (diceValue >= 2 && diceValue < 5) {
            System.out.println("Öops, you got 0 bonus point for this action :(");
            return MISS;
        } else if (diceValue < 10) {
            System.out.println("10 bonus points for this action!");
            return HIT;
        } else {
            System.out.println("20 bonus points for this action :)");
            return CRUCIAL_HIT;
        }

    }

}
