package myFirstGame;


import GameLogic.State.*;
import GameLogic.State.UI.Log;
import java.util.HashMap;
import java.util.Map;


public class SmallTroggR2 extends CombatState {
    private boolean troggRan = false;

    private YoungTrogg YoungTrogg = new YoungTrogg(player,log);

    public SmallTroggR2(int id, Log log, Player player) {
        super(id, log, player, " Your path is blocked by a Trogg that is wearing what appears to be five helmets.");

    }

    @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

    }

    @Override
    public void roomEnterLogic(){

    }


    @Override
    public void search(String what) {

        switch (what){
            case "trogg":
                if(!troggRan) {
                    log.slowPrintln("A trogg. Don't let him get your helmets.");
                    while (!troggRan) {
                        try {
                            YoungTrogg.performAction(player);

                        } catch (YouDied youDied) {
                            log.slowPrint("You fall dead to the ground, and the Trogg starts searching you for treasure.");
                        }
                        readAndExecuteAction();
                    }
                    break;
                }
            case "forest":
                if(troggRan){
                    log.slowPrintln("The forest floor is slightly disturbed by the event.");
                    break;
                }
            default:
                log.slowPrintln("You find nothing.");
        }

    }

    @Override
    public Map<String, State> exits() {
        Map<String, State> exits = new HashMap<>();
        return exits;
    }


    @Override
    public void attack(String who) {
        try {
            YoungTrogg.takeDamage(player.calculateDamageDealt());

        } catch (YouDied youDied) {
            log.slowPrintln("You finally land a blow on the putrid creatures head! In its dazed state, the Trogg runs away.");
            searchText = "Without the Trogg here, you notice how much better the forest smells.";
            exits.put("west", MapManager.getTile(21));
            exits.put("south", MapManager.getTile(1));
            exits.put("north", MapManager.getTile(3));

          //  log.delayPrintln("", 100);
            troggRan = true;
        }
    }
    public void move(String where) {

        State next = exits.get(where);

        if ((!troggRan)|| (where.equals("north") || (where.equals("south")) || (where.equals("east")) || (where.equals("west")))) {
            if (!troggRan) {
                log.slowPrintln("The angry Trogg blocks you from running away.");
            }
        }
    }

    @Override
    public void cast(String spell){

    }
}
