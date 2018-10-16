package myFirstGame.RoomTemplates;

import GameLogic.State.InitState;
import GameLogic.State.Player;
import GameLogic.UI.JukeBox;
import GameLogic.UI.Log;

public abstract class StartingAreaTemplateRoom extends InitState {
    public StartingAreaTemplateRoom(int id, Log log, Player player, String searchText) {
        super(id, log, player, searchText);
    }

    @Override
    public void onEnter(){
        JukeBox.stopLoop();
    }
}
