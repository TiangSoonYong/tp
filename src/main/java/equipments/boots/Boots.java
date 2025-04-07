package equipments.boots;

import equipments.Equipment;

/**
 * Represents Weapon that Player can equip
 */
public class Boots extends Equipment {
    public static final String EQUIPMENT_TYPE = "Boots";

    public Boots(String name, int defense, int attack, int health, int value) {
        super(name, defense, attack, health, value);
    }

    public Boots(String name, int defense, int attack) {
        super(name, defense, attack, 0, 0);
    }

    @Override
    public String getEquipmentType() {
        return EQUIPMENT_TYPE;
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public String toText() {
        return EQUIPMENT_TYPE + " " + BootsDatabase.getIndexByName(this.name);
    }

    public String toString() {
        return EQUIPMENT_TYPE + "   : " + name + " (" + defense + " def) (" + attack + "atk)";
    }
}
