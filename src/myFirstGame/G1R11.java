package myFirstGame;

import GameLogic.State.CombatState;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.JukeBox;
import GameLogic.State.UI.Log;
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
            JukeBox.playMP3Times(JukeBox.MOVINGTREES, 5);
                Thread.sleep(4000);
            log.slowerPrintln("There is nowhere to go.");
            Thread.sleep(650);
            log.slowerPrintln("You hear a deep voice echo through the trees.");
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"What brings you here, Child?",50);
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"Have you come here for the same reason as all the others?",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"You are surrounded by my limbs and veins, foolish Child. I will never let you leave if you have come to claim the last relic.",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"No?...Then why have you come here? Why enter the dark forest?",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"...",100 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"I sense...no evil in you.",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"For thousands of years people have come, and for thousands of years people have stolen from me what I promised to protect.",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"...",100 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"Maybe...You are the one..",60 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"I accept your words, Child. And thus, I must ask you a favour.",50);
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"You see Child, a long time ago, there was a legend of four men.",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"The Legends said they had fought the very gods themselves, and used their fountains to infuse their armour with holy magic.",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"I was a young tree then, and did not know anything about such tales.",50 );
            Thread.sleep(650);
            log.unitSay(TheDarkForest,"but as fate would have it..One day...",60 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"The men from these legends came to me.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"They were old, and had all come to the conclusion that these relics could not be trusted to people who had not seen the land of the gods for themselves.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"And so they chose the least obvious place they could think of.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"Me.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"You see, I was once but a small tree in a big forest.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"They buried the relics under my roots, and hoped that this tree would hide and protect their relics forever.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"And so I did.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"For thousands of years I hid these relics, and I could feel the magic of the gods emanating into my veins.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"With the help of the magic buried under me, I grew vast and strong.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"But then one day, Evil came.",80 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"And I failed to protect what they desired.",60 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"Then years later they came again. And I failed again.",50 );
            Thread.sleep(850);
            log.unitSay(TheDarkForest,"Now I have only one relic left. I have grown weak, and my forest dark.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"If I give up the last relic, I will wither.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"I have heard through my roots, many years ago, that the relics have been separated from each other.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"If that is the case, a hero with one relic in their possession would hold the power to gather them all.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"Perhaps...",80 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"You will be that hero.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"Very well.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"If this destiny calls you, touch the roots of my tree. And I shall grant you the last relic.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"Take it, and use its power to claim the others. And when you are done, bring them back here.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"If you do not, your fate will be the same as the ones who have tried to control these relics before you.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"But you are not like them.",50 );
            Thread.sleep(900);
            log.unitSay(TheDarkForest,"Now, touch the roots, Child. I am ready.",50 );


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
                    log.unitSay(TheDarkForest,"Before you lies the last relic, Child.",70 );
                    Thread.sleep(650);
                    log.unitSay(TheDarkForest,"Niglu's Great Helm.",70 );
                    Thread.sleep(900);
                    log.unitSay(TheDarkForest,"It holds many powers, which im sure will be revealed to you in time.",70 );
                    Thread.sleep(900);
                    log.unitSay(TheDarkForest,"Once you take it, I will no longer have enough magic to keep myself alive. But I was not meant to ever be more than a tree, so to have a life at all has been a great gift.",60);
                   addTakeable(new NiglusGreatHelm());
                   break;
            }
            else {
                log.slowPrintln("The tree feels cold now, and absent of life.");
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
    public void duck(String under) {

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
    public void jump(String where) {

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
