package equipments.weapons;

import equipments.Equipment;

/**
 * Represents Weapon that Player can equip
 */
public class Weapon extends Equipment {
    public static final String EQUIPMENT_TYPE = "Weapon";


    public Weapon(String name, int defense, int attack, int health, int value) {
        super(name, defense, attack, health, value);
    }

    public Weapon(String name, int attack) {
        super(name, 0, attack, 0, 0);
    }

    @Override
    public String getEquipmentType() {
        return EQUIPMENT_TYPE;
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public String toText() {
        return EQUIPMENT_TYPE + " " + WeaponDatabase.getIndexByName(this.name);
    }

    public String toString() {
        return EQUIPMENT_TYPE + "  : " + name + " (" + attack + " atk)";
    }
}
