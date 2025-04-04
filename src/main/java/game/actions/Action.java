package game.actions;

public abstract class Action {
    private String args;


    public Action() {
        this.args = null;
    }

    public Action(String args){
        this.args = args;
    }


    public abstract String getName();
    public String getArgs() {
        return args;
    };

    // public abstract boolean execute(Game game, Storage storage, UIHandler ui) throws RolladieException;
    // public String toString() {
    //     return this.getClass().getSimpleName();
    // }
}
