package myFirstGame;

import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.JukeBox;
import GameLogic.State.UI.Log;
import myFirstGame.RoomTemplates.ForestTemplateRoom;

import java.util.Map;

public class G1R11 extends ForestTemplateRoom {
    public G1R11(int id, Log log, Player player) {
        super(id, log, player, "");
    }




    public boolean darkForestComplete() {  //NEEDS UPDATING WHEN ROOM DONE.
        return false;
    }


    private boolean treesMoved = false;
    @Override
    public void look(String where) {
        if (!treesMoved && where != null) {
            treesMoved = true;
            JukeBox.playMP3Times(JukeBox.MOVINGTREES, 2);
            log.slowPrintln("The dark forest starts shifting around you, the trees shrinking and growing.");
            log.slowerPrintln("There is nowhere to go.");
            log.slowerPrintln("In front of you lies a chest.");
            log.slowPrintln("Here some event that when succeeded allows them to open chest and find first rare item and opens up an exit to the dark forest entrance. End of Demo.");
            searchText ="there is nowhere to go. In front of you lies a chest.";
        }

        super.look(where);

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
