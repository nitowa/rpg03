package myFirstGame;

import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.UI.JukeBox;
import GameLogic.UI.Log;
import myFirstGame.Items.NiglusGreatHelm;
import myFirstGame.RoomTemplates.ForestTemplateRoom;

import java.util.HashMap;
import java.util.Map;

public class G1R11 extends ForestTemplateRoom {
    public G1R11(int id, Log log, Player player) {
            super(id, log, player, "");
        try {
            actions.put("touch", this.getClass().getMethod("touch", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public boolean darkForestComplete() {
        if (helmTaken) {
         return true;
            }
            else {
                return false;
    }}

    private TheDarkForest TheDarkForest = new TheDarkForest(player,log);

    private boolean treesMoved = false;
    private boolean treeTouched = false;
    private boolean helmTaken = false;

    @Override
    public void onEnter(){
        try {
        if (!treesMoved) {
           treesMoved = true;

            log.slowPrintln("The dark forest starts shifting around you, the trees twisting and growing to close off all possible escape routes.");
            JukeBox.playMP3Times(JukeBox.MOVINGTREES, 1);
                Thread.sleep(4000);
            log.slowerPrintln("There is nowhere to go.");
            Thread.sleep(650);
            log.slowerPrintln("You hear a deep voice echo through the trees.");
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"What brings you here, Child?",50);
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"Have you come here for the same reason as all the others?",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"You are surrounded by my limbs and veins, foolish Child. I will never let you leave if you have come to claim my treasure.",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"No?...Then why have you come here? Why enter the dark forest?",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"Wealth does not drive you.. And yet your mind is strong enough to pass my illusions.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"Very well.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"Touch my bark child, and I will show you what they could never see.",50 );

            searchText ="There is nowhere to go. In front of you lies the formidable tree of the dark forest.";
             }
        super.onEnter();
        }
    catch (InterruptedException e) {
        }
    }

    public void touch(String what) {
        try {
            switch (what) {
                case "tree":
                case "roots":
                case "tree roots":
                case "root":
                    if (!treeTouched) {
                    treeTouched = true;
                    log.slowerPrintln("You lay your hand on the roots of the old tree.");
                    Thread.sleep(900);
                    log.slowerPrintln("As your hand touches the bark, the roots start to fold open, revealing a glistening bright blue helmet.");
                    Thread.sleep(650);
                    log.unitSay(TheDarkForest,"Before you lies an ancient relic, Child.",70 );
                    Thread.sleep(650);
                    log.unitSay(TheDarkForest,"Niglu's Great Helm.",70 );
                    Thread.sleep(900);
                    log.unitSay(TheDarkForest,"Taking the helmet will allow you to leave my forest. Though it is capable of more than that. Powers which I am sure will be revealed to you in time.",70 );
                   addTakeable(new NiglusGreatHelm());
                   break;
            }
            else {
                log.slowPrintln("The tree feels old, and cold.");
                break;
            }
                default:
                    log.slowPrintln("Touch what?");
                }
            }
        catch (InterruptedException e) {

        }
    }

    @Override
    public void look(String where) {
        switch (where) {
            case "roots":
            case "tree roots":
            case "tree":
            case "root":
                log.slowPrintln("The tree of the dark forest stretches before you. Each root similar in size to an entire tree.");
                break;
            case "relic":
            case "old relic":
            case "helmet":
            case "helm":
            case "niglu's great helm":
            case "niglus great helm":
            case "great helm":
            case "niglus helm":
                log.slowPrintln("The helm shines bright blue. It has a magical glow to it.");
                break;
        }

        super.look(where);
    }

    @Override
    public void take(String what) {

        switch (what) {
            case "relic":
            case "old relic":
            case "helmet":
            case "helm":
            case "niglu's great helm":
            case "niglus great helm":
            case "great helm":
            case "niglus helm":
                what = "niglu's great helm";
                log.slowerPrintln("You pick up the heavy helm. It resonates as you grab hold of it.");
                JukeBox.playMP3(JukeBox.PICKUP_EPIC);
                helmTaken = true;
                exits.clear();
                exits.put("north", MapManager.getTile(6));
                exits.put("east", MapManager.getTile(6));
                exits.put("south", MapManager.getTile(6));
                exits.put("west", MapManager.getTile(10));
                searchText ="You're standing in front of the dark forest tree.";
                break;
            default:
                log.slowPrintln("Take what?");

        }
        super.take(what);

    }

    @Override
    public void search(String what) {
        switch (what) {
            case "roots":
            case "tree roots":
            case "tree":
            case "root":
            log.slowPrintln("The tree of the dark forest stretches before you. Each root similar in size to an entire tree.");
            break;

        }

    }

    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        return map;
    }


}
