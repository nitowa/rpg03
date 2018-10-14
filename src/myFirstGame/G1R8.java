package myFirstGame;



import GameLogic.State.MapManager;
import GameLogic.State.Player;

import java.util.HashMap;
import java.util.Map;

import GameLogic.State.State;
import GameLogic.State.UI.*;
import myFirstGame.Items.Plank;
import myFirstGame.RoomTemplates.ForestTemplateRoom;


public class G1R8 extends ForestTemplateRoom {
    public G1R8(int id, Log log, Player player) {
        super(id, log, player, "Nearby you can spot a small hut. It looks old and abandoned.");

    }
    @Override
    public void onEnter(){
        G1R11 room;
        room = (G1R11)MapManager.getTile(11);
        if (room.darkForestComplete()){
            exits.put("north",MapManager.getTile(7));
        }
        else {
            log.slowerPrintln("The trees start to shift and move to close the exit behind you.");
            JukeBox.playMP3Times(JukeBox.MOVINGTREES, 2);
            log.slowerPrintln("Where am i?...");
        }

        super.onEnter();
    }


    boolean plankFoundComplete = false;
    boolean plankTakenComplete = false;

    @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

    }
    public void look(String where) {

        if (!plankFoundComplete && where.equals("")) {
            addTakeable(new Plank());
            plankFoundComplete = true;
        } super.look(where);

    }

    @Override
    public void search(String what) {

        switch (what) {


            case "hut":
            case "small hut":
            case "old hut":
            case "old abandoned hut":
            case "abandoned hut":
                log.slowPrintln("The ruins of an old hut. Whoever lived here has been gone for a long time.\nThe building is mostly rubble now.");
                break;
            case "rubble":
                log.slowPrintln("The rubble consists of some planks and broken furniture.");
                break;
            case "planks":
            case "plank":
                log.slowPrintln("Most of the planks have succumbed to rot, but one of them seems to still hold strong.");
                break;
            case "furniture":
            case "broken furniture":
                log.slowPrintln("A lot of broken furniture. Whoever lived here would'nt miss it anymore.");
                break;
        }
    }


    @Override
    public void take(String what){

        switch (what) {

            case ("plank"):
            case ("planks") :
                what = "plank";
                if (plankFoundComplete && !plankTakenComplete) {
                    log.slowPrintln("You take the one plank that has not succumbed to rot.");
                    plankTakenComplete = true;
                    break; }
                else {
                    break;
                }


            case ("furniture"):
            case ("old furniture"):
            case ("broken furniture"):
                log.slowPrintln("The old furniture is basically rubble.");
                break;
            default: log.slowPrintln("Take what?");

        }super.take(what);


    }
    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("east", MapManager.getTile(9));
        return map;

    }


}