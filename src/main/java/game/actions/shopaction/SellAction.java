package game.actions.shopaction;

public class SellAction extends ShopAction {
    public SellAction(String actionArgs) {
        super(actionArgs);
    }

    public String getName() {
        return "sell";
    }

}
