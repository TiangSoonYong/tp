package equipments.weapons;

import exceptions.RolladieException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeaponDatabaseTest {

    @Test
    public void getAllWeapon_weaponListFixed_fourWeapon() {

        assertEquals(5, WeaponDatabase.getAllWeapon().size());
    }

    @Test
    public void getWeaponByName_validName_weaponIsInList() throws RolladieException {

        Weapon weapon = WeaponDatabase.getWeaponByName("Iron Sword");
        assertNotNull(weapon, "Weapon should not be null.");
        assertEquals("Iron Sword", weapon.getName());
    }

    @Test
    public void getWeaponByName_invalidInput_exceptionThrown() {

        RolladieException ex = assertThrows(RolladieException.class,
                () -> WeaponDatabase.getWeaponByName("Nonexistent Sword"));
        assertEquals("Weapon not found!", ex.getMessage());
    }

    @Test
    public void getNumberOfWeaponTypes_weaponListFixed_fourTypes() {

        assertEquals(5, WeaponDatabase.getNumberOfWeaponTypes());
    }

    @Test
    public void getWeaponByIndex_validInput_correctWeaponReturned() {

        Weapon weapon = WeaponDatabase.getWeaponByIndex(0);
        assertNotNull(weapon);
        assertEquals("Wooden Sword", weapon.getName());
    }

    @Test
    public void getWeaponByIndex_invalidInput_exceptionThrown() {

        assertThrows(IndexOutOfBoundsException.class, () -> WeaponDatabase.getWeaponByIndex(10));
    }
}
