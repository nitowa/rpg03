package myFirstGame;

import GameLogic.Inventory.BagFullThrowable;
import GameLogic.Inventory.Items.Item;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.JukeBox;
import GameLogic.State.UI.Log;
import myFirstGame.Items.MuddyLegplates;
import myFirstGame.RoomTemplates.ForestTemplateRoom;

import java.util.HashMap;
import java.util.Map;

public class G1R10 extends ForestTemplateRoom {
    public G1R10(int id, Log log, Player player) {
        super(id, log, player, "You are not sure how long this plank is gonna hold. Maybe you should keep walking.");
        try {
            actions.put("pull", this.getClass().getMethod("pull", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    boolean legsPulled = false;

    @Override
    public void onEnter(){
        JukeBox.playMP3(JukeBox.WALK_WOOD);
        super.onEnter();
    }


    @Override
    public void move(String where) {
        State next = exits.get(where);

        if(next != null){
            JukeBox.playMP3(JukeBox.WALK_WOOD);
            next.enter();
            super.move(where);
        }

        if (!exits.containsKey(where)) {
            if (where.equals("east")) {
                log.slowPrintln("There is a pool of mud in the way.");
                return;
            }
        }
        super.move(where);
    }


    @Override
    public void duck(String under) {

    }

    public void pull(String what) {
        switch (what) {
            case "legs":
            case "legplates":
            case "leg armor":
                if (!legsPulled) {
                log.slowPrintln("You try to balance yourself on the plank while still pulling as hard as you can.\nEventually the mud starts to give in.");
                    Item item = takeableItems.get(what);
                    item = new MuddyLegplates();
                    try {
                        player.getInventory().add(item);
                        JukeBox.playMP3(JukeBox.PICKUP);
                    } catch (BagFullThrowable bagFullThrowable) {
                        log.slowPrintln("Your inventory is full, so you fumble and drop the legplates back in. Great.");
                        legsPulled = false;
                    }
                legsPulled = true;
                break; }
                else {
                    log.slowPrintln("Not a moment worth re-enacting.");
                    break;
                }
            default:
                log.slowPrintln("Pull what?");
        }

    }

    @Override
    public void jump(String where) {

        switch (where) {
            default: log.slowPrintln("You start to build up for a jump, but the plank starts shaking violently.\nMaybe not.");
        }

    }

    @Override
    public void search(String what) {

        switch (what) {
            case "legs":
            case "legplates":
            case "leg armor":
                log.slowPrintln("They're right under you now.\nMaybe if i pull real hard....");
                break;
            default:
                log.slowPrintln("Search what?");
        }
    }
        @Override
        public void take(String what) {

            switch (what) {
                case "legs":
                case "legplates":
                case "leg armor":
                    log.slowPrintln("They wouldn't come loose with just a grab.");
                    break;
                case "plank":
                    log.slowPrintln("I would'nt do that if I wanted to live.");
                    break;
                default:
                    log.slowPrintln("Take what?");
            }
    }

    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("west", MapManager.getTile(9));
        map.put("east", MapManager.getTile(11));
        return map;
    }
}
