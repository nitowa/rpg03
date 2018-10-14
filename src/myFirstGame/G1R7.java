package myFirstGame;



import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.Log;
import myFirstGame.RoomTemplates.ForestTemplateRoom;

import java.util.HashMap;
import java.util.Map;

public class G1R7 extends ForestTemplateRoom {
    public G1R7(int id, Log log, Player player) {
        super(id, log, player, "The dark forest splits up in every direction. It's constantly getting colder.");
    }

int roomsLooped = 0;
    @Override
    public void onEnter(){

        if (roomsLooped < 7 ) {
            if (roomsLooped !=0){
            switch (roomsLooped) {

                case 1:
                    log.slowerPrintln("The forest splits up..again?");
                    break;
                case 2:
                    log.slowerPrintln("..Have I not been here before?");
                    break;
                case 3:
                    log.slowerPrintln("..I'm sure I was just here.");
                    break;
                case 5:
                    log.slowerPrintln("..What is going on?");
                    break;
                case 4:
                    log.slowerPrintln("You start to feel dizzy.");
                    break;
                case 6:
                    log.slowerPrintln("You feel the temperature start to go back to normal.");
                    break;
                case 7:
                    log.slowPrintln("You feel your dizziness start to wear off.");


            } }
            roomsLooped++;
        }
            else {
                searchText = "Upon inspection, the forest appears completely normal..";
                exits.clear();
            System.out.println("HEY GEY GEY BITCONNECT");
            exits.put("west", MapManager.getTile(6));
            exits.put("south", MapManager.getTile(8));

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
