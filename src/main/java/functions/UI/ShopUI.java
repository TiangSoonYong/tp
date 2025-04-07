package functions.UI;


import players.Player;
import equipments.Equipment;
import functions.TypewriterEffect;


public class ShopUI {
    public static void printShopCollection(Equipment[] equipments) {
        TypewriterEffect.print("[Shopkeeper] Here is my shop collection!");
        for (int i = 1; i <= equipments.length; i++) {
            System.out.println(i + ": " + equipments[i - 1] + "( " + equipments[i - 1].getValue() + " gold)");
        }
    }

    public static void printShopMenu(Player player) {
        System.out.println(player.name + " has " + player.gold + " gold — choose an action:");
        System.out.println("1. Buy");
        System.out.println("2. Sell");
        System.out.println("3. Exit the Shop");
    }

    public static void printBuyInstructions() {
        System.out.println("Input the corresponding index of the equipment you want to buy.");
    }

    public static void printSellInstructions() {
        System.out.println("Input the corresponding index of the equipment you want to sell.");
        System.out.println("0: Armor");
        System.out.println("1: Boots");
        System.out.println("2: Weapon");
    }
}
