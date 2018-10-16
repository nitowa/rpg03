package myFirstGame;


import GameLogic.State.*;
import GameLogic.UI.Log;
import myFirstGame.Items.OldHelmet;
import myFirstGame.Items.WoodenClub;

import java.util.HashMap;
import java.util.Map;


public class G1R2 extends CombatState {
    private boolean troggRan = false;
    private boolean helmetSpawned = false;
    private YoungTrogg YoungTrogg = new YoungTrogg(player,log);

    public G1R2(int id, Log log, Player player) {
        super(id, log, player, "Your path is blocked by a Forest Trogg!");

    }

    @Override
    public void look(String where) {

        switch (where) {

            case "helm":
            case "helmet":
                log.slowPrintln("It looks like a helmet is a Forest Troggs go-to attire.");
                break;
            case "trogg":
                if (!troggRan) {
                    log.slowPrintln("A small, but albeit very angry Forest Trogg.");
                }
                break;
            case "forest":
            case "ground":
            case "floor":
            case "forest floor":
                if (troggRan) {
                    log.slowPrintln("The forest floor has been slightly disturbed by the event. The Troggs club and helmet lie on the ground.");
                }
        }
        super.look(where);
    }

    @Override
    public void roomEnterLogic() {
        if(!troggRan) {
            log.slowerPrintln("You take a step forward and see a Forest Trogg walking by. You try to not make noise, but the Trogg sees you and attacks.");

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

            case "helm" :
            case "helmet" :
                if(troggRan) {
                    log.slowPrintln("A very worn old helmet. It smells rather bad.");
                }
                else {
                    log.slowPrintln("The Trogg does NOT like that.");
                    break;
                }
            case "trogg":
                if(!troggRan) {
                    log.slowPrintln("the Trogg does NOT like that.");
                    }
                    break;
            case "forest":
            case "ground":
            case "floor":
            case "forest floor":
                if(troggRan){
                    log.slowPrintln("The forest floor is slightly disturbed by the event. The Troggs club and helmet lie on the ground.");
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
            case ("helmet"):
                what = "old helmet";
                if (!troggRan && !YoungTrogg.getHelmetoff()) {
                    log.slowPrintln("That helm is not coming off with such gentle measures.");

                }
                break;
            case "club":
            case "wooden club":
            case "wood club":
                what = "wooden club";
           break;
        }super.take(what);
    }

    @Override
    public void attack(String who) {

        if (who.equals("helm") || who.equals("head") || who.equals("helmet")) {
            if (!helmetSpawned) {
                addTakeable(new OldHelmet());
                helmetSpawned = true;
            }
        }
        try {
            YoungTrogg.takeDamage(player.calculateDamageDealt(), who);

        } catch (YouDied youDied) {
            addTakeable(new WoodenClub());
            searchText = "With the Trogg gone, the forest definitely smells better.";
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

    @Override
    protected void onEnter() {
        //see roomenterlogic
    }
}
