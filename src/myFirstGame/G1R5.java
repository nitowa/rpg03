package myFirstGame;

import java.util.HashMap;
import java.util.Map;

import GameLogic.Inventory.Items.SharpWeapon;
import GameLogic.State.State;
import GameLogic.UI.*;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import myFirstGame.Items.ShortSword;
import myFirstGame.RoomTemplates.StartingAreaTemplateRoom;


public class G1R5 extends StartingAreaTemplateRoom {
    public G1R5(int id, Log log, Player player) {
        super(id, log, player,
              "The forest appears to thicken. Large branches make it impossible to go any further.\nNext to the branches there lies an old half-rotten sign.Dev tip: Short sword on ground");

        try {
            actions.put("cut", this.getClass().getMethod("cut", String.class));
            actions.put("read", this.getClass().getMethod("read", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    boolean swordSpawned = false;

    boolean cutComplete = false;



    @Override
    public void onEnter(){
        if (!swordSpawned) {
            addTakeable(new ShortSword());
            swordSpawned = true;
        }

        super.onEnter();
    }


    public void cut(String what) {

        if(!(player.getInventory().getWeapon() instanceof SharpWeapon)) {
            log.slowPrintln("You dont have a sharp weapon equipped.");
            return;
        }

        switch (what) {
            case "large branches":
            case "branches":
                JukeBox.playMP3(JukeBox.SWORDHIT);
                JukeBox.playMP3(JukeBox.WOODCRACK);
                log.slowerPrintln("Your sword cuts through the branches. They crack loudly and fall to the ground.");
                JukeBox.playMP3(JukeBox.BACKGROUND_WIND, true);
                log.slowerPrintln("A cold chill passes through the hole you made. You can now move further.");
                exits.put("hole",MapManager.getTile(6));
                searchText= "You see an old murk-rotten sign and an entrance to the Dark Forest that you made.";
                cutComplete=true;
                break;
            case "half-rotten sign":
                case "sign":
                log.slowPrintln("The sign doesn't need to go through more than it already has.");
                break;
            default:
                JukeBox.playMP3(JukeBox.SWORDHIT);
                log.slowPrintln("You cut into the air.");
        }

    }

    public void read(String what) {

        switch (what) {
            case "sign":
            case "half-rotten sign":
            case "rotten sign":
                log.slowerPrintln("Do not e ter th Dark F rest.");
                break;
            default:
                log.slowPrintln("Read what?");

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

    @Override
    public void move(String where) {
        switch (where) {
            case "hole":
            case "east":
                if (!cutComplete) {
                    log.slowPrintln("There are branches in the way.");
                    break;
                }else  {
                    log.slowerPrintln("You crawl through the hole in the branches.");
                }
                super.move("hole");
            break;
            default:
                super.move(where);
        }
    }


    @Override

    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("west", MapManager.getTile(4));
        return map;

    }

}
