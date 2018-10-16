package myFirstGame;

import java.util.Map;

import GameLogic.State.InitState;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.UI.Log;

public class testRoom extends InitState {
    public testRoom(int id, Log log, Player player, String searchText) {
        super(id, log, player, searchText);
    }



    @Override
    protected void onEnter() {

    }

    @Override
    public void search(String what) {

    }

    @Override
    public Map<String, State> exits() {
        return null;
    }
}
