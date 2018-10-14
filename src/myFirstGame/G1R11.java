package myFirstGame;

import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.JukeBox;
import GameLogic.State.UI.Log;

import java.util.HashMap;
import java.util.Map;

public class G1R11 extends ForestTemplateRoom {
    public G1R11(int id, Log log, Player player) {
        super(id, log, player, "");

    }


    public void look(String where) {
        if (where != null) {
            log.slowPrintln("The forest appears to split up in all directions..");
            log.slowerPrintln("....Again?");
            log.slowerPrintln("....Something feels eerie...");
        }

         super.look(where);

    }
    // when moving towards anything but south the other entrances grow shut.
    @Override
    public void move(String where) {
        State next = exits.get(where);

        if(next != null){
            JukeBox.playMP3(JukeBox.WALK);

            exits.remove(MapManager.getTile(10));
            exits.remove( MapManager.getTile(0));
            exits.remove( MapManager.getTile(0));
            next.enter();
            super.move(where);
        }

        super.move(where);
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
        Map<String, State> map = new HashMap<>();
        map.put("west", MapManager.getTile(10));
        map.put("east", MapManager.getTile(0));
        map.put("south", MapManager.getTile(12));
        map.put("north", MapManager.getTile(0));
        return map;
    }
}
