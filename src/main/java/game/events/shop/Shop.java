package game.events.shop;

import exceptions.RolladieException;
import functionalities.Parser;
import functionalities.ui.ShopUI;
import functionalities.ui.UI;
import game.actions.Action;
import game.characters.Player;
import game.equipment.Equipment;
import game.events.Event;

import static functionalities.Parser.getShopAction;

public class Shop extends Event {
    private Equipment[] equipments;
    private boolean isDone;

    public Shop(Player player, Equipment[] equipments) {
        super(player);
        this.equipments = equipments;
        isDone = false;
    }

    @Override
    public void run() throws RolladieException {
        ShopUI.printShopEntry();
        startShopping();
        ShopUI.printShopExit();
    }

    @Override
    public String getEventIcon() {
        return "Shop";
    }

    @Override
    public String toText() {
        return this.getEventIcon();
    }

    private Action getCurrAction() {
        String inputString = Parser.readInput();
        return getShopAction(inputString);
    }

    public void handleAction() throws RolladieException {
        Action currentAction = getCurrAction();

        switch (currentAction.getName()) {
        case "buy":
            int equipmentIndex = Integer.parseInt(currentAction.getArgs()) - 1;
            Equipment equipment = equipments[equipmentIndex];
            if (player.hasEnoughGold(equipment.getValue())) {
                player.buyEquipment(equipment);
                ShopUI.printBuyEquipment(equipment);
            } else {
                UI.printErrorMessage("You don't have enough gold to buy this equipment!");
            }
            break;
        case "sell":
            String equipmentType = currentAction.getArgs();
            player.sellEquipment(equipmentType);
            ShopUI.printSellEquipment(player.getEquipment(equipmentType));
            break;
        case "leave":
            isDone = true;
            break;
        default:
            throw new RolladieException("You can only use \"buy\", \"sell\" or \"leave\" bro");
        }
    }


    public void startShopping() throws RolladieException {
        while (!isDone) {
            ShopUI.printShopCollection(player, equipments);
            handleAction();
        }
    }
}
