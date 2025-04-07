
//@@author vincesum
package equipments.armors;

import equipments.Equipment;

/**
 * Represents Armor that Player can augment
 */
public class Armor extends Equipment {
    public static final String EQUIPMENT_TYPE = "Armor";

    public Armor(String name, int defense, int attack, int health, int value) {
        super(name, defense, attack, health, value);
    }

    public Armor(String name, int defense) {
        super(name, defense, 0, 0, 0);
    }

    @Override
    public String getEquipmentType() {
        return EQUIPMENT_TYPE;
    }

    @Override
    public String toText() {
        return EQUIPMENT_TYPE + " " + ArmorDatabase.getIndexByName(this.name);
    }

    @Override
    public int getId() {
        return 0;
    }

    public String toString() {
        return EQUIPMENT_TYPE +"   : " + name + " (" + defense + " def)";
    }
}
