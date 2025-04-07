package equipments;

/**
 * Represents Armor that Player can augment
 */
public abstract class Equipment {

    public String name;
    public int defense;
    public int attack;
    public int health;
    public int value;

    public Equipment(String name, int defense, int attack, int health, int value) {
        this.name = name;
        this.defense = defense;
        this.attack = attack;
        this.health = health;
        this.value = value;
    }

    public abstract int getId();

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getHealth() {
        return this.health;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        assert obj != null:"object cannot be null";

        // First check if the obj is of type Equipment
        if (this == obj) {
            return true;  // If both references are the same, they're equal
        }

        if (obj instanceof Equipment) {
            // Cast obj to Equipment to access Equipment-specific methods
            Equipment other = (Equipment) obj;

            // Now you can safely call getName() and other methods
            return this.getName().equals(other.getName());
        }

        return false;  // If obj is not an instance of Equipment, return false
    }

    public abstract String getEquipmentType();

    public abstract String toText();
}
