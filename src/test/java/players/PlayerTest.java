package players;

import equipments.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import players.abilities.Ability;
import players.abilities.BasicAttack;
import players.abilities.PowerStrike;
import equipments.armors.Armor;
import equipments.weapons.Weapon;
import java.io.ByteArrayInputStream;
import java.util.List;

public class PlayerTest {

    private Player player;
    private Player opponent;
    private List<Equipment> equipmentList;
    private List<Equipment> opponentList;

    @BeforeEach
    public void setUp(){
        equipmentList = List.of(new Weapon("Sword", 2),
                new Armor("Leather", 1));
        player = new Player("Hero", 100, 5, 2,
                equipmentList, true);

        opponentList = List.of(new Weapon("Claws", 1), new Armor("Shield", 4));
        opponent = new Player("Enemy", 100, 3, 4,
                opponentList, false);
    }

    @Test
    public void createNewPlayer_validInput_playerIsAlive(){
        String simulatedInput = "TestHero\n";
        ByteArrayInputStream testInput = new ByteArrayInputStream(simulatedInput.getBytes());
        Player.resetScanner(testInput);
        Player player = Player.createNewPlayer();
        assertTrue(player.isAlive());
    }

    @Test
    public void totalRoll_checkDiceSum_sumIsCorrect(){

       player.diceRolls = new int[]{5,10,12};
        int total = player.totalRoll();

        assertEquals(27, total);
    }


    @Test
    void totalRoll_NegativeRolls_sumIsCorrectlyCalculated() {
        player.diceRolls = new int[]{-1, 5, 3};
        int total = player.totalRoll();

        assertEquals(7, total);
    }

    @Test
    public void computeDamageTo_testDamageCalculation_damageEqualsNine() throws InterruptedException{

        player.maxPower = 100;
        player.power = 50;
        player.lastAbilityUsed = new BasicAttack();

        player.diceRolls = new int[]{4, 3};


        int damage = player.computeDamageTo(opponent);
        assertEquals(9,damage);
    }

    @Test
    public void computeDamageTo_opponentIsNotAlive_assertionThrown() throws InterruptedException{
        try{
            opponent =  new Player("Enemy", 0, 5, 2, opponentList, true);

            player.lastAbilityUsed = new BasicAttack();
            player.lastAbilityUsed.damageMult = 1;
            player.computeDamageTo(opponent);
        }catch(AssertionError e){
            assertEquals("Opponent must be alive to receive damage", e.getMessage());

        }
    }

    @Test
    public void applyDamage_largeDamage_DevastatingBlow() throws InterruptedException {

        opponent.lastAbilityUsed = new BasicAttack();
        String result = opponent.applyDamage(15, player, "Battle: ");

        assertEquals(85, opponent.getHp());
        assertTrue(result.contains("devastating blow"));
    }

    @Test
    public void applyDamage_playerDamageIsNegative_assertionThrown()throws InterruptedException {

        int player_damage = -10;

        try{
            player.applyDamage(player_damage,opponent,"Testing");

        }catch(AssertionError e){
            assertEquals("damage value must be non-negative",e.getMessage());
        }
    }

    @Test
    public void heal_healAmountIsNegative_assertionThrown() {
        try{
            player.heal(-100);
        }catch(AssertionError e){
            assertEquals("amount to heal must be non-negative", e.getMessage());
        }
    }

    @Test
    public void heal_healAmountEqualsFIve_hpUpdatedCorrectly() {
        player.heal(5);
        assertEquals(100, player.getHp());
    }

    @Test
    public void updatePower_updateAmountIsFive_powerValueUpdatedCorrectly(){
        player.updatePower(5);
        assertEquals(55, player.power);
    }


    @Test
    public void updatePower_updateAmountIsNegative_assertionThrown() {
        try{
            player.updatePower(-100);
        }catch(AssertionError e){
            assertEquals("power value must be non-negative", e.getMessage());
        }
    }

    @Test
    public void resetAllCooldowns_nullAbilityInList_gracefulHandling() {
        player.abilities.add(null);
        player.abilities.add(new BasicAttack());

        assertThrows(NullPointerException.class, player::resetAllCooldowns);
    }

    @Test
    public void resetAllCooldowns_setsAllCooldownsToZero() {
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

