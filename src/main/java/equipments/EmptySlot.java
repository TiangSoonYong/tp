//@@author James17042002
package equipments;

public class EmptySlot extends Equipment {
    public EmptySlot() {
        super("empty", 0,0,0, 0);
    }

    @Override
    public String getEquipmentType() {
        return "empty";
    }

    @Override
    public String toText() {
        return "";
    }

    @Override
    public int getId() {
        return -1;
    }
}
