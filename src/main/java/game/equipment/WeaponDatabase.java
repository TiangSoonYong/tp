package game.equipment;

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
        weaponList.add(new Weapon("Iron Sword", 40, 100, 20, 30));
        weaponList.add(new Weapon("Steel Daggers", 50, 80, 40, 50));
        weaponList.add(new Weapon("Executioner's Axe", 90, 120, 10, 100));
        weaponList.add(new Weapon("Dragon Tooth Sword", 150, 200, 50, 300));
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

    public static int getNumberOfWeaponTypes() {
        return weaponList.size();
    }

    public static Weapon getWeaponByIndex(int index) {
        return weaponList.get(index);
    }
}
