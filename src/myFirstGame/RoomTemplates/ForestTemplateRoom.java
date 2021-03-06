package myFirstGame.RoomTemplates;

import GameLogic.State.InitState;
import GameLogic.UI.JukeBox;
import GameLogic.UI.Log;
import GameLogic.State.Player;

public abstract class ForestTemplateRoom extends InitState {

    public ForestTemplateRoom(int id, Log log, Player player, String searchText) {
        super(id, log, player, searchText);
    }

    @Override
    protected void onEnter() {
        JukeBox.stopAllLoopsExcept(JukeBox.BACKGROUND_WIND);
        JukeBox.playMP3(JukeBox.BACKGROUND_WIND, true);
    }
}
