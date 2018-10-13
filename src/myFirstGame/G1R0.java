package myFirstGame;

import java.util.HashMap;
import java.util.Map;

import GameLogic.Inventory.BagFullThrowable;
import GameLogic.Inventory.Items.Item;
import GameLogic.State.UI.*;
import GameLogic.State.*;
import myFirstGame.Items.HealthPotion;

public class G1R0 extends ForestTemplateRoom {
    public G1R0(int id, Log log, Player player) {
        super(id, log, player, "On the ground you see a few rocks, and a couple of rotten branches.");
    }

    boolean PotionFoundComplete = false;


    @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

    }

    @Override
    public void search(String what) {
        switch (what) {

            case "rotten branches":
                log.slowPrintln("They almost fall apart as you search them.");
                break;

            case "branches":
                log.slowPrintln("They almost fall apart as you search them.");
                break;

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
            case ("old dusty potion"):

                what = "health potion";

            case ("potion"):

                what = "health potion";
            default:
                super.take(what);
        }


    }
    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("north", MapManager.getTile(1));
        map.put("rocky", MapManager.getTile(-1));
        return map;
    }
}
