package myFirstGame;

import java.util.HashMap;
import java.util.Map;

import GameLogic.Inventory.BagFullThrowable;
import GameLogic.Inventory.Items.Item;
import GameLogic.State.UI.*;
import GameLogic.State.*;
import myFirstGame.Items.Charcoal;
import myFirstGame.Items.HealthPotion;
import myFirstGame.Items.rottenBranch;

public class G1R0 extends ForestTemplateRoom {
    public G1R0(int id, Log log, Player player) {
        super(id, log, player, "theres not much to see except for a few rocks, and a couple of rotten branches.");
    }

    boolean PotionFoundComplete = false;
    boolean branchFoundComplete = false;
    @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

    }

    @Override
    public void look(String where) {

        if (!branchFoundComplete && where.equals("")) {
            addTakeable(new rottenBranch());
            branchFoundComplete = true;
        } super.look(where);

    }

    @Override
    public void search(String what) {
        switch (what) {

            case "rotten branches":
            case "branch":
            case "branches":
                log.slowPrintln("They almost fall apart as you search them.");
                break;
            case "rock":
            case "rocks":
               if (PotionFoundComplete == false) {
                   log.slowPrintln("Under the rocks you spot an old dusty potion. It must have been here a very long time.");
                   addTakeable(new HealthPotion());
                   PotionFoundComplete = true;
               }
               else {
                   log.slowPrintln("Without the potion under here, the rocks suddenly seem less interesting.");
               }

                break;
            default:
                log.slowPrintln("You find nothing.");

        }

    }
    @Override
    public void take(String what){

        switch (what) {

            case ("rotten branch"):
            case ("branches") :
            case ("branch"):
                what = "rotten branch";

                break;

            case ("old dusty potion"):

                what = "health potion";

            case ("potion"):

                what = "health potion";


        }super.take(what);


    }
    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("north", MapManager.getTile(1));
        map.put("rocky", MapManager.getTile(-1));
        return map;
    }
}
