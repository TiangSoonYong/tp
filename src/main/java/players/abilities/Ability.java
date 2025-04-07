package players.abilities;

import players.Player;

/**
 * Represents Player-class abilities used during a battle encounter
 */
public abstract class Ability {

    public AbilityType type;
    public String name;
    public String tags;
    public String icon;
    public int coolDown;
    public int currentCoolDown;
    public double damageMult;
    public int powerCost;

    /**
     * Create an Ability that Player object can use during battle
     * 
     * @param type AbilityType enum
     * @param name Name of the Ability
     * @param tags Extra information relating to Ability to aid users' choice. Example: 'Heal +5HP'
     * @param icon Emoji icon to represent the attack type
     * @param coolDown Number of turns that the Ability will become unusable after casting
     * @param damageMult Damage multiplier
     * @param powerCost Amount of `Power` required to cast the ability
     */
    public Ability(AbilityType type, String name, String tags, String icon, int coolDown,
                   double damageMult, int powerCost) {
        this.type = type;
        this.name = name;
        this.tags = tags;
        this.icon = icon;
        this.coolDown = coolDown;
        this.currentCoolDown = 0;
        this.damageMult = damageMult;
        this.powerCost = powerCost;
    }

    /**
     * Checks if the action is ready to be performed based on both cooldown status and power requirement.
     * 
     * @param currentPower the current available power to check against the Ability's power cost
     * @return true if the cooldown is complete and there's sufficient power, false otherwise
     */
    public boolean isReady(int currentPower) {
        return currentCoolDown == 0 && currentPower >= powerCost;
    }

    /**
     * Checks if the cooldown period has completed.
     * 
     * @return true if the cooldown is complete (currentCooldown == 0), false otherwise
     */
    public boolean isCDReady() {
        return currentCoolDown == 0;
    }

    /**
     * Sets the cooldown timer
     */
    public void startCooldown() {
        this.currentCoolDown = coolDown;
    }

    /**
     * Resets the cooldown timer
     */
    public void resetCooldown() {
        this.currentCoolDown = 0;
    }

    /**
     * Decrements the cooldown time
     */
    public void tickCooldown() {
        if (currentCoolDown > 0) currentCoolDown--;
    }

    /**
     * Contains special effects of the Ability
     * @param player the Player which the ability will affect
     */
    public void additionalFeatures(Player player) {}

    public String toText() {
        return this.name;
    }
}
