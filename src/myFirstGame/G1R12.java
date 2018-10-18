package myFirstGame;

import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.UI.Log;
import myFirstGame.RoomTemplates.StartingAreaTemplateRoom;

import java.util.HashMap;
import java.util.Map;

public class G1R12 extends StartingAreaTemplateRoom {

    public G1R12(int id, Log log, Player player) {
        super(id, log, player, "In front of you sits a sword stuck in a stone. The road behind it has been blocked by a rockslide.");
    }
    @Override
    public void look (String where) {
        switch (where) {
            case "sword":
            case "stone":
            case "sword in stone":
                log.slowPrintln("A rusty sword that has been shoved into a stone one way or another.");
                break;
            case "rocks":
            case "rockslide":
                log.slowPrintln("A pile of rocks that block the way.");
                break;

        } super.look(where);
    }
    @Override
    public void search(String what) {

        switch (what) {
            case "sword":
            case "stone":
            case "sword in stone":
                log.slowPrintln("It seems to be stuck pretty tight.");
                break;
            case "rocks":
            case "rockslide":
                log.slowPrintln("The rocks must have ended up here during a rockslide.");
                break;

        }
    }

    @Override
    public void take(String what){

        switch (what) {
            case "sword":
            case "sword in stone":
                log.slowPrintln("You are not strong enough to pull it out.");
                break;
            case "rocks":
            case "rockslide":
                log.slowPrintln("To take one rock would risk the whole thing falling on you.");
                break;
        }

    }
    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("east", MapManager.getTile(2));
        return map;
    }
}
