package myFirstGame;



import GameLogic.State.MapManager;
import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.Log;

import java.util.HashMap;
import java.util.Map;

public class G1R7 extends ForestTemplateRoom {
    public G1R7(int id, Log log, Player player) {
        super(id, log, player, "The dark forest splits up in every direction. It's only getting colder.\nDev tip:here an event where whichever direction the user chooses, they will warp to a random room in the maze, until theyve done it 8 times, then they reach the room thats to the south.");

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
        map.put("north", MapManager.getTile(8));
        map.put("east", MapManager.getTile(8));
        map.put("south", MapManager.getTile(8));
        map.put("west", MapManager.getTile(8));
        return map;
    }
}
