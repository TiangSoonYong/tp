package events;

import exceptions.RolladieException;
import players.Player;

public abstract class Event {
    public boolean isExit = false;
    protected Player player;
    protected boolean hasWon = false;


    public Event(Player player) {
        this.player = player;
    }

    public boolean getHasWon() {
        return hasWon;
    }
    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
    public abstract void run() throws RolladieException, InterruptedException;
}
