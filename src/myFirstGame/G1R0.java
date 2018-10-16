package myFirstGame;

import java.util.HashMap;
import java.util.Map;

import GameLogic.State.UI.*;
import GameLogic.State.*;
import myFirstGame.Items.HealthPotion;
import myFirstGame.Items.Rock;
import myFirstGame.Items.rottenBranch;
import myFirstGame.RoomTemplates.StartingAreaTemplateRoom;

public class G1R0 extends StartingAreaTemplateRoom {
    public G1R0(int id, Log log, Player player) {
        super(id, log, player, "theres not much to see except for a few rocks, and a couple of rotten branches.");
    }

    private boolean PotionFoundComplete = false;
    private boolean branchFoundComplete = false;

    @Override
    public void look(String where) {

        if (!branchFoundComplete && where.equals("")) {
            addTakeable(new rottenBranch());
            addTakeable(new Rock());
            branchFoundComplete = true;
        }
        switch (where) {

            case "rotten branches":
            case "branch":
            case "branches":
                log.slowPrintln("Some rotten branches.");
                break;
            case "rock":
            case "rocks":
                    log.slowPrintln("Some rocks. One of them appears to give away a faint red glow from under it.");
                break;
        }
        super.look(where);
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

            case "rocks":
            case "rock":
                what = "rock";
                break;

            case ("rotten branch"):
            case ("rotten branches"):
            case ("branches") :
            case ("branch"):
                what = "rotten branch";

                break;

            case ("old dusty potion"):
            case ("potion"):

                what = "health potion";
                break;


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
