package players.abilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import players.Player;

/**
 * A class contains Junit test for ability class
 */
public class AbilityTest {
    Ability ability;

    /* Create a subclass for testing */
    static class TestAbility extends Ability {
        public TestAbility() {
            super(AbilityType.BASIC_ATTACK, "Test", "", "🔥", 2, 1.5, 10);
        }

        @Override
        public void additionalFeatures(Player player) {}
    }

    @BeforeEach
    public void setUp() {
        ability = new players.abilities.AbilityTest.TestAbility();
    }

    @Test
    public void isReady_currentPowerIsGreater_shouldReturnTrue() {
        assertTrue(ability.isReady(15));
    }

    @Test
    public void isReady_whenNotEnoughPower_shouldReturnFalse() {
        assertFalse(ability.isReady(5));
    }

    @Test
    public void isReady_negativeValue_assertionThrown() {
        try{
            ability.isReady(-10);
        }catch(AssertionError e){
            assertEquals("power must not be negative", e.getMessage());
        }
    }

    @Test
    public void isReady_whenOnCooldown_shouldReturnFalse() {
        ability.startCooldown();
        assertFalse(ability.isReady(15));
    }

    @Test
    public void isCDReady_whenCooldownZero_shouldReturnTrue() {
        assertTrue(ability.isCDReady());
    }

    @Test
    public void startCooldown_setCurrentCooldownToDefinedCoolDown_returnTrue() {
        ability.startCooldown();
        assertEquals(2, ability.currentCoolDown);
    }

    @Test
    public void resetCooldown_setCurrentCooldownToZero_coolDownEqualsZero() {
        ability.startCooldown();
        ability.resetCooldown();
        assertEquals(0, ability.currentCoolDown);
    }

    @Test
    public void tickCooldown_coolDownEqualsTwo_shouldDecreaseCooldown() {
        ability.startCooldown();
        ability.tickCooldown();
        assertEquals(1, ability.currentCoolDown);
    }

    @Test
    public void tickCooldown_coolDownIsZero_shouldNotGoBelowZero() {
        ability.resetCooldown();
        ability.tickCooldown();
        assertEquals(0, ability.currentCoolDown);
    }
}
