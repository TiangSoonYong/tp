package equipments;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EquipmentTest {

    private Equipment sword;
    private Equipment shield;

    @BeforeEach
    public void setUp() {
        sword = new equipments.EquipmentTest.TestEquipment("Iron Sword",
                30, 50, 20, 100);
        shield = new equipments.EquipmentTest.TestEquipment("Steel Shield",
                50, 0, 40, 150);
    }

    @Test
    public void getName_validInput_correctNameReturned() {
        assertEquals("Iron Sword", sword.getName());
        assertEquals("Steel Shield", shield.getName());
    }

    @Test
    public void getAttack_validInput_correctValueReturned() {
        assertEquals(50, sword.getAttack());
        assertEquals(0, shield.getAttack());
    }

    @Test
    public void getDefense_validInput_correctValueReturned() {
        assertEquals(30, sword.getDefense());
        assertEquals(50, shield.getDefense());
    }

    @Test
    public void getHealth_validInput_correctValueReturned() {
        assertEquals(20, sword.getHealth());
        assertEquals(40, shield.getHealth());
    }

    @Test
    public void getValue_validInput_correctValueReturned() {
        assertEquals(100, sword.getValue());
        assertEquals(150, shield.getValue());
    }

    @Test
    public void equals_validObject() {

        Equipment anotherSword = new equipments.EquipmentTest.TestEquipment("Iron Sword",
                30, 50, 20, 100);
        Equipment differentShield = new equipments.EquipmentTest.TestEquipment("Golden Shield",
                60, 0, 50, 200);

        assertTrue(sword.equals(anotherSword));
        assertFalse(sword.equals(differentShield));
        assertFalse(sword.equals(new Object()));
    }

    @Test
    public void equals_objectIsNull_assertionThrown() {


        try{
            sword.equals(null);
        }catch(AssertionError e){
            assertEquals("object cannot be null", e.getMessage());

        }
    }

    /* Concrete subclass to allow testing of the abstract class Equipment */
    private static class TestEquipment extends Equipment {
        public TestEquipment(String name, int defense, int attack, int health, int value) {
            super(name, defense, attack, health, value);
        }

        @Override
        public String getEquipmentType() {
            return "test";
        }

        @Override
        public int getId() {
            return 0;
        }
    }
}
