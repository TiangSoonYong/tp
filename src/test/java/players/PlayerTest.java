/*
package players;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import players.abilities.Ability;
import players.abilities.BasicAttack;
import players.abilities.PowerStrike;
import equipments.armors.Armor;
import equipments.weapons.Weapon;

public class PlayerTest {

    @Test
    public void totalRoll_checkDiceSum_sumIsCorrect(){
        Player player = new Player("Hero", 100, 5, 2,
                new Weapon("Sword", 2), new Armor("Leather", 1), true);

        player.diceRolls = new int[]{5,10,12};
        int total = player.totalRoll();

        assertEquals(27, total);
    }


    @Test
    void totalRoll_NegativeRolls_sumIsCorrectlyCalculated() {
        Player player = new Player("Hero", 100, 5, 3,
                new Weapon("Sword", 2), new Armor("Shield", 1), true);

        player.diceRolls = new int[]{-1, 5, 3};
        int total = player.totalRoll();

        assertEquals(7, total);
    }

    @Test
    public void computeDamageTo_testDamageCalculation_damageEqualsNine() throws InterruptedException{
        Player attacker = new Player("Hero", 100, 5, 2,
                new Weapon("Sword", 2), new Armor("Leather", 1), true);

        attacker.maxPower = 100;
        attacker.power = 50;
        attacker.lastAbilityUsed = new BasicAttack();

        attacker.diceRolls = new int[]{4, 3};

        Player opponent = new Player("Enemy", 100, 3, 4,
                new Weapon("Claws", 1), new Armor("Shield", 4), false);

        int damage = attacker.computeDamageTo(opponent);
        assertEquals(9,damage);
    }

    @Test
    public void computeDamageTo_opponentIsNotAlive_assertionThrown() throws InterruptedException{
        try{
            Player opponent =  new Player("Enemy", 0, 5, 2,
                    new Weapon("Sword", 2), new Armor("Leather", 1), true);
            Player attacker = new Player("Hero", 100, 5, 2,
                    new Weapon("Sword", 2), new Armor("Leather", 1), true);

            attacker.lastAbilityUsed = new BasicAttack();
            attacker.lastAbilityUsed.damageMult = 1;
            attacker.computeDamageTo(opponent);
        }catch(AssertionError e){
            assertEquals("Opponent must be alive to receive damage", e.getMessage());

        }
    }



    @Test
    public void applyDamage_largeDamage_DevastatingBlow() throws InterruptedException {
        Player opponent = new Player("Knight", 100, 6, 4,
                new Weapon("Spear", 2), new Armor("Steel", 3), true);
        Player attacker = new Player("Dragon", 100, 10, 5,
                new Weapon("Flame Claws", 5), new Armor("Scales", 4), false);


        opponent.lastAbilityUsed = new BasicAttack();
        String result = opponent.applyDamage(15, attacker, "Battle: ");

        assertEquals(85, opponent.getHp());
        assertTrue(result.contains("devastating blow"));
    }

    @Test
    public void applyDamage_playerDamageIsNegative_assertionThrown()throws InterruptedException {
        Player attacker = new Player("Hero", 100, 5, 2,
                new Weapon("Sword", 2), new Armor("Leather", 1), true);

        Player opponent = new Player("Enemy", 100, 3, 4,
                new Weapon("Claws", 1), new Armor("Shield", 4), false);
        int attacker_damage = 0;

        try{
            attacker.applyDamage(attacker_damage,opponent,"Testing");

        }catch(AssertionError e){
            assertEquals("damage value must be non-negative",e.getMessage());

        }

    }

    @Test
    public void heal_healAmountIsNegative_assertionThrown() {
        try{
            Player player = new Player("Hero", 100, 5, 2,
                    new Weapon("Sword", 2), new Armor("Leather", 1), true);

            player.heal(-100);
        }catch(AssertionError e){
            assertEquals("amount to heal must be non-negative", e.getMessage());
        }
    }

    @Test
    public void heal_healAmountEqualsFIve_hpUpdatedCorrectly() {
        Player player = new Player("Hero", 100, 5, 2,
                new Weapon("Sword", 2), new Armor("Leather", 1), true);

        player.heal(5);
        assertEquals(100, player.getHp());

    }

    @Test
    public void updatePower_updateAmountIsFive_powerValueUpdatedCorrectly(){
        Player player = new Player("Hero", 100, 5, 2,
                new Weapon("Sword", 2), new Armor("Leather", 1), true);

        player.updatePower(5);
        assertEquals(55, player.power);
    }


    @Test
    public void updatePower_updateAmountIsNegative_assertionThrown() {
        try{
            Player player = new Player("Hero", 100, 5, 2,
                    new Weapon("Sword", 2), new Armor("Leather", 1), true);

            player.updatePower(-100);
        }catch(AssertionError e){
            assertEquals("power value must be non-negative", e.getMessage());
        }
    }

    @Test
    public void resetAllCooldowns_nullAbilityInList_gracefulHandling() {
        Player player = new Player("TestHero", 100, 5, 3,
                new Weapon("TestSword", 1), new Armor("TestArmor", 1), true);

        player.abilities.add(null); // Unexpected null
        player.abilities.add(new BasicAttack());

        assertThrows(NullPointerException.class, player::resetAllCooldowns);
    }

    @Test
    public void resetAllCooldowns_setsAllCooldownsToZero() {

        Player player = new Player("TestHero", 100, 5, 3,
                new Weapon("TestSword", 1), new Armor("TestArmor", 1), true);

        Ability ability1 = new BasicAttack();
        Ability ability2 = new PowerStrike();

        ability1.currentCoolDown = 100;
        ability2.currentCoolDown = -100;

        player.abilities.add(ability1);
        player.abilities.add(ability2);

        player.resetAllCooldowns();

        assertEquals(0, ability1.currentCoolDown);
        assertEquals(0, ability2.currentCoolDown);
    }

}
*/
