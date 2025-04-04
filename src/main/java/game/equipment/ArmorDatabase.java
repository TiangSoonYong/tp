package game.equipment;


import exceptions.RolladieException;

import java.util.ArrayList;

/**
 * Database class that maintains a list of Armor objects.
 * This ensures easy access without the need for repeated instantiations.
 */
public class ArmorDatabase {
    /**
     * A static list containing predefined armor objects.
     */
    private static final ArrayList<Armor> armorList = new ArrayList<>();

    // Static block to initialize predefined armors
    static {
        armorList.add(new Armor("Iron Armor", 50, 150, 5, 40));
        armorList.add(new Armor("Steel Armor", 70, 200, 3, 60));
        armorList.add(new Armor("Leather Armor", 20, 50, 10, 10));
        armorList.add(new Armor("Dragon Scale Armor", 150, 300, 20, 150));
    }

    public static ArrayList<Armor> getAllArmor() {
        return armorList;
    }

    /**
     * Find armor based on armorName
     * @param name Name of the armor being queried
     * @return Corresponding armor
     */
    public static Armor getArmorByName(String name) throws RolladieException {
        for (Armor armor : armorList) {
            if (armor.getName().equalsIgnoreCase(name)) {
                return armor;
            }
        }
        throw new RolladieException("Armor not found!");
    }

    public static int getNumberOfArmorTypes() {
        return armorList.size();
    }

    public static Armor getArmorByIndex(int index) {
        return armorList.get(index);
    }
}
