package myFirstGame;


import java.util.HashMap;
import java.util.Map;

import GameLogic.State.State;
import GameLogic.UI.*;
import GameLogic.State.MapManager;
import GameLogic.State.Player;
import myFirstGame.RoomTemplates.StartingAreaTemplateRoom;


public class G1R4 extends StartingAreaTemplateRoom {
    public G1R4(int id, Log log, Player player) {
        super(id, log, player, "Watch your step. Blocking the path is a big crack in ground.");
        try {
            actions.put("jump", this.getClass().getMethod("jump", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private boolean riddle1Complete = false;

    public void jump(String where) {
        switch (where) {

            case "big hole":
            case "hole":
            case "crack":
            case "big crack":
                JukeBox.playMP3(JukeBox.JUMP);
                JukeBox.playMP3(JukeBox.PICKUP);
              if((int)(Math.random()*3) >0 ) {
                  riddle1Complete = true;
                  log.slowPrintln("Puh! You successfully jump over the crack in the ground. That could have gone worse.");
                  if (exits.containsValue(MapManager.getTile(3))) {
                      exits.remove("west");
                      exits.put("east", MapManager.getTile(5));
                  }
                  else {
                      exits.remove("east");
                      exits.put("west", MapManager.getTile(3));
                  }
                  break;
            }
               else {
                  log.slowPrintln("You slip and hit the ground when trying to make the jump. Hope nobody saw that.");
                  break;
              }
            default:
                log.slowPrintln("Jump over what?");

        }

    }

    @Override
    public void search(String what) {

        switch (what) {
            case "big hole":
            case "hole":
            case "crack":
            case "big crack":
                log.slowPrintln("You get nauseous searching the crack so close to the abyss. It almost seems like it has been caused by the aggressive growth of tree roots.");
                break;
            case "roots":
            case "tree roots":
                log.slowPrintln("Both sides of the crack are covered in tree roots.");
                break;
            default:
                log.slowPrintln("Search what?");

        }
    }
    @Override
    public void look(String where) {
        switch (where) {
            case "big hole":
            case "hole":
            case "crack":
            case "big crack":
                log.slowPrintln("A large crack that continues downward a long distance.");
                break;
            case "roots":
            case "tree roots":
                log.slowPrintln("They appear to just be normal, but very large, tree roots.");
                break;
        }
        super.look(where);
    }

    public void move(String where) {

       if (!exits.containsKey(where)) {
           if (where.equals("west") || where.equals("east")) {
           log.slowPrintln("There is a crack in the ground in the way.");
           return;
        }
       }
       super.move(where);
    }

    @Override
    public Map<String, State> exits() {


        Map<String, State> map = new HashMap<>();
        map.put("west", MapManager.getTile(3));
        return map;
    }
}
