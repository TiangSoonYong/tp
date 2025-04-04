package game.actions.shopaction;

public class BuyAction extends ShopAction {
    public BuyAction(String actionArgs) {
        super(actionArgs);
    }

    public String getName() {
        return "buy";
    }

}
