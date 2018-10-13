package myFirstGame;


import GameLogic.State.*;
import GameLogic.State.UI.Log;
import java.util.HashMap;
import java.util.Map;


public class G1R2 extends CombatState {
    private boolean troggRan = false;

    private YoungTrogg YoungTrogg = new YoungTrogg(player,log);

    public G1R2(int id, Log log, Player player) {
        super(id, log, player, "Your path is blocked by a Trogg that is carrying\nwhat appears to be a satchel full of helmets.");

    }

    @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

    }
    @Override
    public void roomEnterLogic() {
        if(!troggRan) {
            log.slowerPrintln("You take a step forward and see a trogg walking by. \nYou try to not make noise, but the trogg sees you and attacks.");

            try {
                while (!troggRan) {
                    YoungTrogg.performAction(player);
                    readAndExecuteAction();
                }
            } catch (YouDied youDied) {
                log.slowPrint("You fall dead to the ground, and the Trogg starts searching you for treasure.");
            }
        }


    }


    @Override
    public void search(String what) {

        switch (what){
            case "satchel":
            case "satchel of helmets":
            case "helm" :
                log.slowPrintln("The Trogg does NOT like that.");
                break;

            case "helmet" :
                log.slowPrintln("The Trogg does NOT like that.");
                break;

            case "trogg":
                if(!troggRan) {
                    log.slowPrintln("A trogg. He appears to collect helms. Maybe he's looking for one that fits. \nThe one on his head is definitely too big.");
                    }
                    break;

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
    public void take(String what){

        switch (what) {
            case ("helm"):
                log.slowPrintln("That helm is not coming off with such gentle measures.");
                break;
            case ("helmet"):

                log.slowPrintln("That helm is not coming off with such gentle measures.");
                break;
            default:
                super.take(what);
        }


    }

    @Override
    public void attack(String who) {
        try {
            YoungTrogg.takeDamage(player.calculateDamageDealt(), who);

        } catch (YouDied youDied) {
            searchText = "With the Trogg gone, you notice how much better the forest smells.";
            exits.put("west", MapManager.getTile(21));
            exits.put("south", MapManager.getTile(1));
            exits.put("north", MapManager.getTile(3));
            troggRan = true;
        }
    }

    @Override
    public void move(String where) {

        if ((where.equals("north") || (where.equals("south")) || (where.equals("east")) || (where.equals("west")))) {
            if (!troggRan) {
                log.slowPrintln("The angry Trogg blocks you from running away.");
                return;
            }
            super.move(where);

        }
    }

    @Override
    public void cast(String spell){

    }
}
