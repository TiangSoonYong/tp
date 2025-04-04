package game.characters;

/**
 * A class to create the enemy object.
 */
public class Enemy extends Character {
    /**
     * Constructor to construct an enemy object.
     *
     * @param health an integer array to represent the health bar of enemy.
     * @param attack an integer to represent the damage value in an attack.
     * @param defense an integer to represent the defense value.
     * @param name a string to represent the name of the enemy.
     */

    public Enemy(int[] health, int attack, int defense, String name) {
        super(health, attack, defense, name);
    }


    /**
     * Overloaded Constructor to construct an enemy object with defined parameters
     * Usage within Storage Class for loading game
     * @param healthBars
     * @param attackValue
     * @param defenseValue
     * @param characterName
     * @param maxHealth
     */
    public Enemy(int[] healthBars, int attackValue, int defenseValue, String characterName, int maxHealth) {
        super(healthBars, attackValue, defenseValue, characterName, maxHealth);
    }

    public String getEnemyName() {
        return super.getCharacterName();
    }
}
