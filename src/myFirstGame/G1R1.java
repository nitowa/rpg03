package myFirstGame;

import java.util.HashMap;
import java.util.Map;


import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.*;

public class G1R1 extends ForestTemplateRoom {
    public G1R1(int id, Log log, Player player) {
        super(id, log, player, "It appears that someone made camp here not too long ago.\nThere are remains of a fire, and some logs that seem to have been repurposed as chairs.");
    }

    @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

    }

    @Override
    public void search(String what) {

        switch (what) {

            case "camp":
                log.slowPrintln("Looks like a camp for two people. Everything of value has presumably been stolen.");
                break;
            case "fire":

                log.slowPrintln("You search the fire and find nothing, except that your hands have, unfortunately, now turned very black.");

                break;

            case "logs":
                log.slowPrintln("Certainly good for sitting.");

                break;
            case "chairs":
                log.slowPrintln("Certainly good for logging.");
                break;
            default:

                log.slowPrintln("You find nothing.");

        }

    }

    @Override
    public Map<String, State> exits() { Map<String, State> map = new HashMap<>();
        map.put("north", MapManager.getTile(2));
        map.put("south", MapManager.getTile(0));
        return map;

    }


}
