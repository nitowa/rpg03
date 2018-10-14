package myFirstGame;

import GameLogic.Inventory.Items.Item;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.JukeBox;
import GameLogic.State.UI.Log;
import myFirstGame.Items.Plank;
import myFirstGame.RoomTemplates.ForestTemplateRoom;

import java.util.HashMap;
import java.util.Map;

public class G1R9 extends ForestTemplateRoom {
    public G1R9(int id, Log log, Player player) {
        super(id, log, player, "The road ahead is engulfed in a pool of mud.");
        try {
            actions.put("put", this.getClass().getMethod("put", String.class));
            actions.put("lay", this.getClass().getMethod("lay", String.class));
            actions.put("place", this.getClass().getMethod("place", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    boolean mudSearched= false;
    boolean plankPlaced = false;


    @Override
    public void move(String where) {
        State next = exits.get(where);

        if(next != null ){
            JukeBox.playMP3(JukeBox.WALK_WOOD);
            next.enter();
            super.move(where);
        }

    }

    public void put(String what) {
        place(what);
    }

    public void place(String what) {

        System.out.println(what);

        switch (what) {

            case "plank on mud":
            case "plank on pool":
            case "plank on pool of mud":
            case "plank":
                Item item = player.getInventory().getItemByIndexOrName("plank");

                System.out.println(what);
                if (!plankPlaced && (item instanceof Plank)) {
                    log.slowPrintln("You place the plank over the pool of mud. Lets hope it holds.");
                    searchText="The road ahead is engulfed in a pool of mud. Your plank is holding up, but it's definitely uncertain for how long.";
                    if(item != null){
                        JukeBox.playMP3(JukeBox.DROP);
                        player.getInventory().silentRemove(item);
                    }
                    plankPlaced = true;
                    exits.put("plank", MapManager.getTile(10));
                    break;
                }
            default: log.slowPrintln("Place what?");

        }
    }

    public void lay (String what) {
        place(what);
    }

    @Override
    public void use (String what) {

        switch (what) {
            case "plank":
                log.slowPrintln("And do what?");
                break;
            default: log.slowPrintln("Use what?");
        }

    }


    @Override
    public void take(String what) {

        switch (what) {
            case "legs":
            case "legplates":
            case "leg armor":
                log.slowPrintln("They are just out of reach.");
                break;
            case "plank":
                log.slowPrintln("And do what?.");
                break;
            default:
                log.slowPrintln("Take what?");
        }
    }

        @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

        switch (where) {
            case "mud":
            case "pool":
            case "pool of mud":
                log.slowPrintln("The distance is just a bit longer than you've ever jumped before, and you get cold feet.");
                break;
            case "legplates":
                log.slowPrintln("You could probably land on the legplates from here, but undoubtedly they would sink.");
                break;
            default:
                log.slowPrintln("Jump what?");
        }

    }

    @Override
    public void search(String what) {

        switch (what) {
            case "pool":
            case "mud":
                if (!mudSearched) {
                    log.slowPrintln("You spot pair of legplates in the center of the pool. They appear to be slowly sinking.\nThey should soon reach the sunken fate that their previous wearer presumably suffered.");
                    mudSearched = true;
                    break;
                }
                else {
                    log.slowPrintln("Don't fall in. Or you will have to choose between your leg armor and your life.");
                    break;
                }
            case "legplates":
            case "legs":
            case "leg armor":
                log.slowPrintln("They're too far away to search.");
                break;
        }

    }


    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("north", MapManager.getTile(8));
        return map;
    }
}
