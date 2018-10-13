package myFirstGame;

import GameLogic.State.UI.Log;
import GameLogic.State.Player;
import GameLogic.State.State;

public abstract class ForestTemplateRoom extends State {

    public ForestTemplateRoom(int id, Log log, Player player, String searchText) {
        super(id, log, player, searchText);
    }
}
