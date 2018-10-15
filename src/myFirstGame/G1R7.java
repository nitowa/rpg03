package myFirstGame;



import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.JukeBox;
import GameLogic.State.UI.Log;
import myFirstGame.RoomTemplates.ForestTemplateRoom;

import java.util.HashMap;
import java.util.Map;

public class G1R7 extends ForestTemplateRoom {
    public G1R7(int id, Log log, Player player) {
        super(id, log, player, "The dark forest splits up in every direction. You can feel the temperature slowly dropping.");
    }

int roomsLooped = 0;
    @Override
    public void onEnter(){
        if (roomsLooped < 7) {
            JukeBox.playMP3(JukeBox.WALK_DIZZY);
            if (roomsLooped !=0){
                switch (roomsLooped) {
                    case 1:
                        log.slowerPrintln("You end up at a very similar crossroads to the one you just passed.");
                        break;
                    case 2:
                        log.slowerPrintln("..Have you not been here before?");
                        break;
                    case 3:
                        log.slowerPrintln("..You're sure you were just here.");
                        break;
                    case 5:
                        log.slowerPrintln("..What is going on?");
                        break;
                    case 4:
                        log.slowerPrintln("The wind is almost unbearable.");
                        break;
                    case 6:
                        log.slowerPrintln("You start to feel dizzy.");
                        break;
                    default :
                        log.slowerPrintln("For some reason the wind starts to subside. You feel the temperature start to go back to normal.\nWhat just happened?");

                }
            }
            roomsLooped++;
        } else {
            searchText = "Upon inspection, the forest appears completely normal..";
            exits.clear();
            exits.put("south", MapManager.getTile(8));
            exits.put("west", MapManager.getTile(6));
        }

        super.onEnter();
    }

    @Override
    public void duck(String under) {

    }

    @Override
    public void jump(String where) {

    }

    @Override
    public void search(String what) {

    }

    @Override
    public Map<String, State> exits() {
        Map<String, State> map = new HashMap<>();
        map.put("north", this);
        map.put("east", this);
        map.put("south", this);
        map.put("west", this);

        return map;
    }
}
