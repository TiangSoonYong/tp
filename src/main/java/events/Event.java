package events;

import exceptions.RolladieException;
import players.Player;

public abstract class Event {
    public boolean isExit;
    protected Player player;
    protected boolean hasWon;

    public Event(Player player) {
        this.player = player;
        hasWon = false;
        isExit = false;
    }

    public boolean getHasWon() {
        return hasWon;
    }
    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
    public abstract void run() throws RolladieException, InterruptedException;
}
