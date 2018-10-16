package myFirstGame;

import java.util.HashMap;
import java.util.Map;

import GameLogic.State.State;
import GameLogic.UI.*;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import myFirstGame.Items.EmptyVial;
import myFirstGame.Items.LeatherBoots;
import myFirstGame.Items.OldHelmet;
import myFirstGame.RoomTemplates.StartingAreaTemplateRoom;

public class G1R3 extends StartingAreaTemplateRoom {
    public G1R3(int id, Log log, Player player) {
        super(id, log, player, "It looks like a clearing in the forest. On the ground you see two corpses.");
    }

    private boolean vialsSpawned = false;
    private boolean bootsSpawned = false;

    @Override
    public void onEnter() {
        if (!vialsSpawned) {

            addTakeable(new EmptyVial());
            addTakeable(new EmptyVial());
            vialsSpawned = true;
        }
        if (!bootsSpawned) {
            addTakeable(new LeatherBoots());
            bootsSpawned = true;
        }
     super.onEnter();
    }

    @Override
    public void take(String what){

        switch (what) {
            case "corpse":
            case "corpses":
            case "bodies":
            case "body":
                log.slowPrintln("Definitely not.");
                break;

            case "vials":
            case "empty vials":
            case "vial":
            case "empty vial":
                what = "empty vial";
                break;
            case "boots":
            case "leather boots":
            case "shoes":
            case "leather shoes":
                what = "leather boots";
                break;
        }
        super.take(what);

    }
    public void look(String where) {

        switch (where) {
            case "corpse":
            case "corpses":
            case "bodies":
            case "body":
        log.slowPrintln("The bodies seem to have been dragged and pillaged.");
        break;
            case "clearing":
            case "beautiful clearing":
                log.slowPrintln("A beautiful clearing.");
                break;
      }  super.look(where);

    }

    @Override
    public void search(String what) {
        switch (what) {
                case "corpse":
                case "corpses":
                case "bodies":
                case "body":
                    log.slowPrintln("You find a few empty vials firmly gripped in the hands of the bodies. One body is wearing some leather boots.");
                    break;
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
