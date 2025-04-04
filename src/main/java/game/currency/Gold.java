package game.currency;


import exceptions.RolladieException;

public class Gold {
    private final int amount;

    public Gold(int amount) {
        this.amount = Math.max(amount, 0);
    }

    public int getAmount() {
        return amount;
    }

    public Gold earnGold(Gold earned) {
        return new Gold(amount + earned.getAmount());
    }

    public Gold spendGold(Gold spent) throws RolladieException {
        if (amount < spent.getAmount()) {
            throw new RolladieException("You do not have enough money!");
        }
        return new Gold(amount - spent.getAmount());
    }

    public String toString() {
        return "Gold: " + amount;
    }
}
