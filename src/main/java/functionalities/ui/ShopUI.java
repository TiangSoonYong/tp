package functionalities.ui;

import game.characters.Player;
import game.equipment.Equipment;

public class ShopUI extends UI {
    public static void printShopEntry() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Narrator: You enter the equipment shop.");
        System.out.println(LINE_SEPARATOR);
        System.out.println("Shopkeeper: Welcome to my shop! Take your time!");
        System.out.println(LINE_SEPARATOR);
    }

    public static void printShopExit() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Shopkeeper: Thanks for coming! Hope to see you again!");
        System.out.println(LINE_SEPARATOR);
        System.out.println("Narrator: You leave the equipment shop.");
        System.out.println(LINE_SEPARATOR);
    }

    public static void printBuyEquipment(Equipment equipment) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Narrator: You bought " + equipment.getName() + " for " + equipment.getValue() + " gold.");
        System.out.println(LINE_SEPARATOR);
    }

    public static void printSellEquipment(Equipment equipment) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Narrator: You sold " + equipment.getName() + " for " + equipment.getValue() / 2 + " gold.");
        System.out.println(LINE_SEPARATOR);
    }

    public static void printShopCollection(Player player, Equipment... equipments) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Shop Equipment Collection:");
        for (int i = 0; i < equipments.length; i++) {
            System.out.println((i + 1) + ". " + equipments[i].toString());
        }
        System.out.println(LINE_SEPARATOR + "\n");
        System.out.println("Please enter \"buy [equipment_index]\"  or \"sell [equipmentType]\" or \"leave\".");
        System.out.println(LINE_SEPARATOR);
        System.out.println("Please do not buy equipment types you already own. Current Player info: ");
        System.out.println(LINE_SEPARATOR);
        System.out.println(player.toString());
    }
}
