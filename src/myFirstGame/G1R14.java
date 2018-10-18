package myFirstGame;

import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.UI.Log;
import myFirstGame.RoomTemplates.StartingAreaTemplateRoom;

import java.util.Map;

public class G1R14 extends StartingAreaTemplateRoom {

    public G1R14(int id, Log log, Player player) {
        super(id, log, player, "");
    }

    @Override
    public void search(String what) {

    }

    @Override
    public Map<String, State> exits() {

        return null;
    }
}
