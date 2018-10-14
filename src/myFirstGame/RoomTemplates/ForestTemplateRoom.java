package myFirstGame.RoomTemplates;

import GameLogic.State.InitState;
import GameLogic.State.UI.JukeBox;
import GameLogic.State.UI.Log;
import GameLogic.State.Player;

public abstract class ForestTemplateRoom extends InitState {

    public ForestTemplateRoom(int id, Log log, Player player, String searchText) {
        super(id, log, player, searchText);
    }

    @Override
    protected void onEnter() {
        JukeBox.playMP3(JukeBox.BACKGROUND_WIND, true);
    }
}
