package myFirstGame;

import java.util.HashMap;
import java.util.Map;

import GameLogic.Inventory.Items.Item;
import GameLogic.Inventory.Items.SharpWeapon;
import GameLogic.State.State;
import GameLogic.State.UI.*;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import myFirstGame.Items.ShortSword;


public class G1R5 extends ForestTemplateRoom {
    public G1R5(int id, Log log, Player player) {
        super(id, log, player, "The forest appears to thicken. Large branches make it impossible to go any further.\nNext to the branches there lies an old half-rotten sign.");
        try {
            actions.put("cut", this.getClass().getMethod("cut", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    boolean swordSpawned = false;

    boolean cutComplete = false;

    @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

    }


    public void cut(String what) {

        if(!(player.getInventory().getWeapon() instanceof SharpWeapon)) {
        log.slowPrintln("You dont have a sharp weapon equipped.");
        return;
        }

        switch (what) {
            case "large branches":
            case "branches":
               log.slowerPrintln("Your sword cuts through the branches. They crack loudly and fall to the ground.\n A cold chill passes through the hole you made. You can now move further.");
               searchText= "You see an old murk-rotten sign and an entrance to the Dark Forest that you made.";
               exits.put("east", MapManager.getTile(6));
                cutComplete=true;
                break;
            case "half-rotten sign":
                case "sign":
                log.slowPrintln("The sign doesn't need to go through more than it already has.");

                break;

            default:
                log.slowPrintln("You cut into the air.");

        }

    }

    @Override
    public void search(String what) {

        switch (what) {
            case "half-rotten sign":
            case "sign":

                log.slowerPrintln("Do not e ter th Dark F rest.");
                break;
            case "large branches":
            case "branches":
                log.slowPrintln("The branches are thick and have grown to make it impossible to pass.");
                break;
            case "floor" :
                if (!swordSpawned) {
                    log.slowPrintln("There is a short sword on the floor.");
                    addTakeable(new ShortSword());

                }
            else {
                   log.slowPrintln("No more swords on this floor.");

                }
                break;
            default:
                log.slowPrintln("You find nothing.");

        }

    }

    public void move(String where) {

        State next = exits.get(where);

        if (where.equals("east")) {
            if (!cutComplete) {
                log.slowPrintln("There are branches in the way.");
            }
            if (cutComplete) {
                log.slowerPrintln("You crawl through the hole in the branches.");
            }
        }
    }


    @Override

    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("west", MapManager.getTile(4));
        return map;

    }

}
