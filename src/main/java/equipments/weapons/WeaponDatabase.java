
//@@author vincesum
package equipments.weapons;

import exceptions.RolladieException;
import java.util.ArrayList;

/**
 * Database class that maintains a list of Weapon objects.
 * This ensures easy access without the need for repeated instantiations.
 */
public class WeaponDatabase {
    /**
     * A static list containing predefined weapon objects.
     */
    private static final ArrayList<Weapon> weaponList = new ArrayList<>();

    // Static block to initialize predefined weapons
    static {
        weaponList.add(new Weapon("Wooden Sword", 0, 10, 0, 5));
        weaponList.add(new Weapon("Iron Sword", 0, 20, 0, 10));
        weaponList.add(new Weapon("Kunai", 0, 30, 0, 20));
        weaponList.add(new Weapon("Executioner's Axe", 0, 40, 0, 30));
        weaponList.add(new Weapon("Dragon Tooth Sword", 0, 50, 0, 40));
    }

    public static ArrayList<Weapon> getAllWeapon() {
        return weaponList;
    }

    /**
     * Find Weapon based on weaponName
     * @param name Name of the weapon being queried
     * @return Corresponding Weapon
     */
    public static Weapon getWeaponByName(String name) throws RolladieException {
        for (Weapon weapon : weaponList) {
            if (weapon.getName().equalsIgnoreCase(name)) {
                return weapon;
            }
        }
        throw new RolladieException("Weapon not found!");
    }

    public static int getIndexByName(String name) {
        for (int i = 0; i < weaponList.size(); i++) {
            if (weaponList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int getNumberOfWeaponTypes() {
        return weaponList.size();
    }

    public static Weapon getWeaponByIndex(int index) {
        if (index < 0) {
            return new Stick();
        }
        return weaponList.get(index);
    }
}
