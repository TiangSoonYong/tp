
//@@author vincesum
package equipments.boots;

import equipments.EmptySlot;
import equipments.Equipment;
import exceptions.RolladieException;

import java.util.ArrayList;

public class BootsDatabase {
    private static final ArrayList<Boots> bootsList = new ArrayList<>();

    // Static block to initialize predefined boots
    static {
        bootsList.add(new Boots("Leather Soles", 5, 5, 0, 10));
        bootsList.add(new Boots("Chainmail Boots", 9, 9, 0, 15));
        bootsList.add(new Boots("Iron Boots", 12, 16, 0, 25));
        bootsList.add(new Boots("Diamond Boots", 26, 22, 0, 40));
        bootsList.add(new Boots("Dragon Scale Strides", 40, 40, 0, 50));
    }

    // Get all boots
    public static ArrayList<Boots> getAllBoots() {
        return bootsList;
    }

    /**
     * Find boots based on bootsName
     * @param name Name of the boots being queried
     * @return Corresponding boots
     */
    public static Boots getBootsByName(String name) throws RolladieException {
        for (Boots boots : bootsList) {
            if (boots.getName().equalsIgnoreCase(name)) {
                return boots;
            }
        }
        throw new RolladieException("Boots not found!");
    }

    public static int getIndexByName(String name) {
        for (int i = 0; i < bootsList.size(); i++) {
            if (bootsList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int getNumberOfBootsTypes() {
        return bootsList.size();
    }

    public static Boots getBootsByIndex(int index) {
        if (index < 0) {
            return new Slippers();
        }
        return bootsList.get(index);
    }
}
