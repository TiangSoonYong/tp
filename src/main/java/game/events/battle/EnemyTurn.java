package game.events.battle;

import game.characters.Character;

/**
 * Represents the enemy's turn. Enemy can only attack for now.
 */
public class EnemyTurn extends Turn {
    private final Character player;
    private final Character enemy;


    /**
     * Constructs a EnemyTurn object to represent current enemy's turn.
     *
     * @param player The player character in battle.
     * @param enemy The enemy character in battle
     */
    public EnemyTurn(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void handleAction() {
        enemy.attack(player);
    }


}
