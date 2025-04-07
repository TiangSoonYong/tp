package players.abilities;

import players.Player;

public class Heal extends Ability {
    public Heal() {
        super(AbilityType.HEAL, "Heal", "+20 HP", "🛡️", 2, 0, 0);
    }

    public void additionalFeatures(Player player) {
        player.heal(20);
        player.updatePower(10);
    }
}
