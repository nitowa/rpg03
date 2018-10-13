package myFirstGame;


import GameLogic.State.Player;

import java.util.Map;

import GameLogic.State.State;
import GameLogic.State.UI.*;


public class G1R6 extends ForestTemplateRoom {
    public G1R6(int id, Log log, Player player) {
        super(id, log, player, "This n");
    }


    @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

    }

    @Override
    public void search(String what) {

    }

    @Override
    public Map<String, State> exits() {
        return null;
    }


}
