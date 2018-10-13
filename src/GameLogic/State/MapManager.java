package GameLogic.State;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class MapManager{

    private static final Map<Integer, State> tiles = new HashMap<>();

    public static void addTile(State s){
        tiles.put(s.getId(), s);
    }

    public static State getTile(int id){
        return tiles.get(id);
    }

    public static void exportMap(String file){
        Gson gson = new Gson();

        System.out.println(gson.toJson(tiles));

    }


}
