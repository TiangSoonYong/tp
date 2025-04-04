package game.equipment;

import exceptions.RolladieException;

import java.util.ArrayList;

public class BootsDatabase {
    private static final ArrayList<Boots> bootsList = new ArrayList<>();

    // Static block to initialize predefined boots
    static {
        bootsList.add(new Boots("Iron Boots", 20, 30, 30));
        bootsList.add(new Boots("Steel Boots", 30, 30, 40));
        bootsList.add(new Boots("Leather Soles", 10, 50, 10));
        bootsList.add(new Boots("Dragon Scale Strides", 50, 100, 120));
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

    public static int getNumberOfBootsTypes() {
        return bootsList.size();
    }

    public static Boots getBootsByIndex(int index) {
        return bootsList.get(index);
    }
}
