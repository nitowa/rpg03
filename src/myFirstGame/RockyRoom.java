package myFirstGame;

import java.util.HashMap;
import java.util.Map;

import GameLogic.Inventory.BagFullThrowable;
import GameLogic.State.CombatState;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.Log;
import GameLogic.State.YouDied;
import myFirstGame.Items.Frostmourne;


public class RockyRoom extends CombatState {
    private boolean nujalikDown = false;
    private boolean rocksCrashed = false;
    private boolean rocksSmashed = false;
    private Nujalik nujalik = new Nujalik(player, log);

    public RockyRoom(int id, Log log, Player player) {
        super(id, log, player, "A mundane room with a bunch of rocks");

        try {
            actions.put("smash", this.getClass().getMethod("smash", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    
    public void smash(String what){
        switch (what){
            case "rocks":
                if(rocksCrashed && !rocksSmashed) {
                    log.slowPrintln("You smash the rocks with your bare fists. Wow!");
                    try {
                        player.getInventory().add(new Frostmourne());
                    } catch (BagFullThrowable bagFullThrowable) {
                        addTakeable(new Frostmourne());
                    }
                    log.unitSay(nujalik,  "W h o   w a k e s   m e.", 100);
                    searchText = "The fearsome Nujalik blocks your path.";
                    while (!nujalikDown) {
                        try {
                            nujalik.performAction(player);
                        } catch (YouDied youDied) {
                            log.slowPrintln("R I P you ded.");
                        }
                        readAndExecuteAction();
                    }
                    log.slowPrintln("The path is clear.");
                    exits.put("oblivion", MapManager.getTile(0));
                    rocksSmashed = true;
                    break;
                }
            case "debris":
                if(rocksCrashed && rocksSmashed){
                    log.slowPrintln("It is already smashed, what are you doing with your life?");
                }
                break;
            default:
                log.slowPrintln("You find nothing.");
        }
    }

    @Override
    public void search(String what) {

        switch (what){
            case "rocks":
                if(!rocksCrashed) {
                    log.slowPrintln("The rocks comes crashing down, blocking the oblivion exit");
                    exits.remove("oblivion");
                    rocksCrashed = true;
                    searchText = "The exit is blocked by rocks. You trapped yourself.";
                    break;
                }else {
                    if(!rocksSmashed) {
                        log.delayPrintln("R o c k s.", 400);
                        break;
                    }
                }

            case "debris":
                if(rocksCrashed){
                    log.slowPrintln("You behold the consequences of your actions. Good job.");
                    break;
                }
            default:
                log.slowPrintln("You find nothing.");
        }

    }

    @Override
    public Map<String, State> exits() {
        Map<String, State> exits = new HashMap<>();
        exits.put("oblivion", MapManager.getTile(0));
        return exits;
    }

    @Override
    public void attack(String who) {
        try {
            nujalik.takeDamage(player.calculateDamageDealt());
        } catch (YouDied youDied) {
            log.slowPrintln("Nujalik growls as he takes his last breath");
            log.unitSay(nujalik, "P E T    B A T T L E S . . . .      .", 100);
            nujalikDown = true;
            searchText = "Nujalik's stinking corpse lies at your feet. A lot of debris is scattered around.";
        }
    }

    @Override
    public void cast(String spell){

    }

    @Override
    protected void onEnter() {

    }
}
