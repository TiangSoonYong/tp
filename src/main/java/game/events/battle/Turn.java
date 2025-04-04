package Game.Events.Battle;

import Game.Actions.Action;

public abstract class Turn {
    protected Action currentAction;
    public boolean hasSurrendered = false;
}
