package GameLogic.State;

import GameLogic.State.UI.Log;

public abstract class InitState extends State {
    public InitState(int id, Log log, Player player, String searchText) {
        super(id, log, player, searchText);
    }

    private boolean inited = false;

    protected abstract void onEnter();

    @Override
    public void enter(){
        onEnter();
        super.enter();
    }
}
