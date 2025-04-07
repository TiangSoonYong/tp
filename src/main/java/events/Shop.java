//@@James17042002

package events;

import players.Player;
import equipments.Equipment;
import exceptions.RolladieException;
import functions.UI.UI;
import functions.UI.ShopUI;
import functions.UI.Narrator;

import static functions.UI.UI.readIntegerInput;


public class Shop extends Event {
    public Equipment[] equipments;
    private boolean isDone;

    public Shop(Player player, Equipment[] equipments) {
        super(player);
        this.equipments = equipments;
        isDone = false;
    }

    @Override
    public void run() throws RolladieException, InterruptedException {
        Narrator.commentOnShopEntry();
        startShopping();
        Narrator.commentOnShopExit();
    }


    public void handleShopInput(int input) throws RolladieException, InterruptedException {
        switch (input) {
        case 1: //buy
            ShopUI.printBuyInstructions();
            int buyInput = readIntegerInput();
            if (buyInput > equipments.length || buyInput < 0) {
                UI.printErrorMessage("Buy index out of range!");
                break;
            }
            handleBuyInput(buyInput);
            break;
        case 2: //sell
            ShopUI.printSellInstructions();
            int sellInput = readIntegerInput();
            if (sellInput >= 3 || sellInput < 0) {
                UI.printErrorMessage("Sell index out of range!");
                break;
            }
            handleSellInput(sellInput);
            break;
        case 3: //exit
            isDone = true;
            break;
        default:
            UI.printErrorMessage("You can only use \"buy\", \"sell\" or \"leave\" bro");
        }
    }

    private void handleBuyInput(int buyInput) throws RolladieException, InterruptedException {
        if (buyInput > equipments.length || buyInput < 1) {
            UI.printErrorMessage("Buy index out of range!");
        }
        Equipment equipment = equipments[buyInput - 1];
        boolean hasBought = player.buyEquipment(equipment);
        if (hasBought) {
            Narrator.commentOnShopBuy(player, equipment);
        } else {
            UI.printErrorMessage("Not enough gold!");
        }
    }

    private void handleSellInput(int sellInput) throws RolladieException, InterruptedException {
        if (sellInput >= 3 || sellInput < 0) {
            UI.printErrorMessage("Buy index out of range!");
        }
        Equipment equipment = player.getEquipment(sellInput);

        if (equipment.getId() != -1) {
            player.sellEquipment(sellInput);
            Narrator.commentOnShopSell(player, equipment);
        } else {
            UI.printErrorMessage("Equipment Type not Equipped!");
        }
    }


    private void startShopping() throws RolladieException, InterruptedException {
        while (!isDone) {
            ShopUI.printShopCollection(equipments);
            ShopUI.printShopMenu(player);
            int input = readIntegerInput();
            handleShopInput(input);
        }
    }
}
