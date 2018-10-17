package myFirstGame;



import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.UI.JukeBox;
import GameLogic.UI.Log;
import myFirstGame.RoomTemplates.ForestTemplateRoom;

import java.util.HashMap;
import java.util.Map;

public class G1R7 extends ForestTemplateRoom {
    private int roomsLooped = 0;

    public G1R7(int id, Log log, Player player) {
        super(id, log, player, "The dark forest splits up in every direction. You can feel the temperature slowly dropping.");
    }

    @Override
    public void onEnter(){
        if (roomsLooped < 8) {
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
                        log.slowerPrintln("You start to feel dizzy.");
                        break;
                    case 4:
                        log.slowerPrintln("..What is going on?");
                        break;
                    case 5:
                        log.slowerPrintln("The wind is almost unbearable.");
                        break;
                    case 6:
                        log.slowerPrintln("You fight through the nausea and the harsh winds. ");
                        break;
                    default :
                        log.slowerPrintln("For some reason the wind starts to subside. You feel the temperature start to go back to normal.\nWhat just happened?");

                }
            }
            roomsLooped++;
        } else {
            searchText = "Upon inspection, the forest now appears completely normal again..";
            exits.clear();
            exits.put("south", MapManager.getTile(8));
            exits.put("west", MapManager.getTile(6));
        }

        super.onEnter();
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
