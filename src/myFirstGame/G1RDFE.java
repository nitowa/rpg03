package myFirstGame;

import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.UI.Log;
import myFirstGame.Items.DeadLeaves;
import myFirstGame.RoomTemplates.ForestTemplateRoom;

import java.util.HashMap;
import java.util.Map;

public class G1RDFE extends ForestTemplateRoom {
    public G1RDFE(int id, Log log, Player player) {
        super(id, log, player, "You are at the entrance of the dark forest. It's dark and cold here. Dead leaves cover the ground.");

    }



    @Override
    public void look(String where) {

        if ( where.equals("")) {
            addTakeable(new DeadLeaves());
        } super.look(where);

    }

    @Override
    public void take(String what) {

        switch (what) {
            case "leaves":
            case "dead leaves":
            case "leaf":
            case "dead leaf":
                what = "dead leaves";
                log.slowPrintln("You carefully pick up some dead leaves.");
        }

         super.take(what);

    }

    @Override
    public void search(String what) {

    }

    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("east", MapManager.getTile(7));
        map.put("hole", MapManager.getTile(5));
        return map;

    }
}
