package GameLogic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameLogic.State.State;
import GameLogic.State.UI.Log;

public abstract class Game {


    public void printMap(Log log, State start){
        Map<Integer, Map<Integer, State>> map = new HashMap<>();
        traverseMap(map, log, start, 0, 0);
    }

    public void traverseMap(Map<Integer, Map<Integer, State>> map, Log log, State start, int row, int col){
        putMap(log, map, row, col, start);
        Map<String, State> exits = start.getCurrentExits();
        exits.entrySet().stream().forEach((entry)->{
            switch (entry.getKey()){
                case "north":
                    putMap(log, map, (row-1), col, entry.getValue());
                    traverseMap(map, log, entry.getValue(), row-1, col);
                    break;
                case "east":
                    putMap(log, map, (row), col+1, entry.getValue());
                    traverseMap(map, log, entry.getValue(), row, col+1);
                    break;
                case "west":
                    putMap(log, map, (row), col+1, entry.getValue());
                    traverseMap(map, log, entry.getValue(), row+1, col);
                    break;
                case "south":
                    putMap(log, map, (row+1), col, entry.getValue());
                    traverseMap(map, log, entry.getValue(), row+1, col);
                    break;
                default:
                    System.out.println("Found unusual exit string >> "+entry.getKey()+" << in "+start);
            }

        });
    }

    private void putMap(Log log, Map<Integer, Map<Integer, State>> map, int x, int y, State s){

        map.computeIfAbsent(y, (key)->map.put(key, new HashMap<>()));
        Map<Integer, State> row = map.get(y);
        row.computeIfAbsent(x, (key)->row.put(key, s));
        if(!row.get(x).equals(s)){
            log.println("ERROR WHILE GENERATING MAP");
            log.println("TILE MISMATCH ON INDEX "+x+"/"+y);
        }

    }

}
