
//@@author vincesum
package equipments.armors;

import exceptions.RolladieException;

import java.util.ArrayList;

public class ArmorDatabase {
    /**
     * A static list containing predefined armor objects.
     */
    private static final ArrayList<Armor> armorList = new ArrayList<>();

    // Static block to initialize predefined armors
    static {
        armorList.add(new Armor("Leather Armor", 8, 0, 0, 10));
        armorList.add(new Armor("Chainmail Armor", 15, 0, 0, 20));
        armorList.add(new Armor("Iron Armor", 20, 0, 0, 25));
        armorList.add(new Armor("Diamond Armor", 30, 0, 0, 45));
        armorList.add(new Armor("Dragon Scale Armor", 40, 0, 0, 60));
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

    public static int getIndexByName(String name) {
        for (int i = 0; i < armorList.size(); i++) {
            if (armorList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int getNumberOfArmorTypes() {
        return armorList.size();
    }

    public static Armor getArmorByIndex(int index) {
        if (index < 0) {
            return new Tshirt();
        }
        return armorList.get(index);
    }
}