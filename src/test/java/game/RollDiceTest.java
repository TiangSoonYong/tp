package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exceptions.RolladieException;
import org.junit.jupiter.api.Test;

class RollDiceTest {

    @Test
    public void diceOutcome_validDiceValue_returnZero() throws RolladieException {
        int diceValue = 2;
        assertEquals(0, RollDice.diceOutcome(diceValue));
    }

    @Test
    public void diceOutcome_invalidDiceValue_exceptionThrown()throws RolladieException{
        int diceValue = 0;
        assertThrows(RolladieException.class,()->RollDice.diceOutcome(diceValue));
    }

}
