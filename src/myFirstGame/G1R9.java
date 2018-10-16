package myFirstGame;

import GameLogic.Inventory.Items.Item;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.UI.JukeBox;
import GameLogic.UI.Log;
import myFirstGame.Items.Plank;
import myFirstGame.RoomTemplates.ForestTemplateRoom;

import java.util.HashMap;
import java.util.Map;

public class G1R9 extends ForestTemplateRoom {
    private boolean mudSearched= false;
    private boolean plankPlaced = false;

    public G1R9(int id, Log log, Player player) {
        super(id, log, player, "The road ahead is separated by an abyss.");
        try {
            actions.put("put", this.getClass().getMethod("put", String.class));
            actions.put("lay", this.getClass().getMethod("lay", String.class));
            actions.put("place", this.getClass().getMethod("place", String.class));
            actions.put("jump", this.getClass().getMethod("jump", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnter(){
        log.slowerPrintln("The trees start to shift and move. You stand back as the large branches pull the ground before you apart, leaving a gaping abyss.");
        JukeBox.playMP3Times(JukeBox.MOVINGTREES, 2);
        super.onEnter();
    }

    public void put(String what) {


        place(what);
    }

    public void place(String what) {

        System.out.println(what);

        switch (what) {
            case "plank hole":
            case "plank over hole":
            case "plank on hole":
            case "plank on abyss":
            case "plank over abyss":
            case "plank":
                Item item = player.getInventory().getItemByIndexOrName("plank");

                System.out.println(what);
                if (!plankPlaced && (item instanceof Plank)) {
                    log.slowPrintln("You place the plank over the abyss. Lets hope it holds.");
                    searchText="The road ahead is separated by an abyss. Your plank is acting as a very unstable bridge.";
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
        place(what);

    }
    @Override
    public void look (String where) {
    switch (where) {

        case "hole":
        case "abyss":
            log.slowPrintln("Don't fall in.");
        }
        super.look(where);

    }


  /*  @Override
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
*/

    public void jump(String where) {

        switch (where) {
            case "hole":
            case "abyss":
                log.slowPrintln("The distance is just a bit longer than you've ever jumped before, and you get cold feet.");
                break;
            default:
                log.slowPrintln("Jump what?");
        }

    }

    @Override
    public void search(String what) {

        switch (what) {
            case "hole":
            case "abyss":
                log.slowPrintln("Don't fall in.");
                break;
        }
    }


    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("west", MapManager.getTile(8));
        return map;
    }
}
