package game.events.battle;


import exceptions.RolladieException;
import functionalities.ui.UI;

import game.characters.Character;

/**
 * Implements the battle logic inside a battle between the player and the enemy.
 */
public class BattleLogic {
    private static final int START_OF_TURN = 0;

    private Character player;
    private Character enemy;
    private int turnNumber;
    private boolean playerTurn;
    private boolean hasWon;
    private boolean hasBattleEnded;

    /**
     * Constructs a Battle Logic object.
     *
     * @param player The player character in battle.
     * @param enemy The enemy character that player is facing.
     */
    public BattleLogic(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
        this.turnNumber = START_OF_TURN;
        this.playerTurn = true;
        this.hasWon = false;
        this.hasBattleEnded = false;
    }

    public boolean getHasWon() {
        return hasWon;
    }

    public boolean getHasBattleEnded() {
        return hasBattleEnded;
    }

    /**
     * Checks whether the battle ends after current turn.
     *
     * @param turn current player/enemy's turn.
     */
    public void checkBattleEnd(Turn turn) {
        if (turn.hasSurrendered) {
            hasWon = false;
            hasBattleEnded = true;
        } else if (!player.isAlive) {
            hasWon = false;
            hasBattleEnded = true;
        } else if (!enemy.isAlive) {
            hasWon = true;
            hasBattleEnded = true;
        }
    }

    /**
     * Handles the player and enemy turns.
     * Calls UI to print player and enemy info after enemy turn.
     */
    private void handleTurnOrder() throws RolladieException {
        if (playerTurn) {
            turnNumber++;
            PlayerTurn turn = new PlayerTurn(player, enemy);
            turn.handleAction();
            checkBattleEnd(turn);
        } else {
            EnemyTurn turn = new EnemyTurn(player, enemy);
            turn.handleAction();
            UI.printCharacterInfo(player);
            UI.printCharacterInfo(enemy);
            checkBattleEnd(turn);
        }
        playerTurn = !playerTurn;
    }

    /**
     * Handles the battle sequence from start to finish.
     *
     * @return a boolean on whether the player has won the battle.
     */
    public boolean battleSequence() throws RolladieException{
        while (!hasBattleEnded) {
            handleTurnOrder();
        }
        return hasWon;
    }

}
