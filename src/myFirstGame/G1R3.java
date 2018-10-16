package myFirstGame;

import java.util.HashMap;
import java.util.Map;

import GameLogic.State.State;
import GameLogic.UI.*;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import myFirstGame.Items.OldHelmet;
import myFirstGame.RoomTemplates.StartingAreaTemplateRoom;

public class G1R3 extends StartingAreaTemplateRoom {
    public G1R3(int id, Log log, Player player) {
        super(id, log, player, "It looks like a relatively empty clearing.");
    }

    private boolean helmetSpawned = false;


    @Override
    public void take(String what){

        switch (what) {
            case ("helm"):

                what = "old helmet";

            case ("helmet"):

                what = "old helmet";
            default:
                super.take(what);
        }


    }
    public void look(String where) {

        if (!helmetSpawned && where.equals("")) {
            addTakeable(new OldHelmet());
            helmetSpawned = true;
        } super.look(where);

    }

    @Override
    public void search(String what) {
        switch (what) {


            case "empty clearing":
                log.slowPrintln("Indeed, an empty clearing. . . . \nBut upon further inspection, you notice someone has dropped an old helmet.");
                if (!helmetSpawned) {
                addTakeable(new OldHelmet());
                helmetSpawned = true;
                }
                break;
            case "clearing":
                log.slowPrintln("Indeed, an empty clearing. . . . \nBut upon further inspection, you notice someone has dropped an old helmet.");
                if (!helmetSpawned) {
                    addTakeable(new OldHelmet());
                    helmetSpawned = true;
                }
                break;
            case "helmet":
                log.slowPrintln("A helmet that appears to have been dropped recently.");

                break;
            case "helm":
                log.slowPrintln("A helmet that appears to have been dropped recently.");

                break;
            default:
                log.slowPrintln("You find nothing.");

        }

    }

    @Override
    public Map<String, State> exits() {

        Map<String, State> map = new HashMap<>();
        map.put("north", MapManager.getTile(68));
        map.put("south", MapManager.getTile(2));
        map.put("east", MapManager.getTile(4));


        return map;
    }


}
