package GameLogic.State.UI;


import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JukeBox extends JFXPanel {

    public static final String TEXT_BLIP    = "assets/textType.mp3";
    public static final String WALK         = "assets/Walk.mp3";
    public static final String PICKUP       = "assets/pickUp.mp3";
    public static final String DROP         = "assets/drop.mp3";
    public static final String EQUIP        = "assets/equip.mp3";
    public static final String UNEQUIP      = "assets/unequip.mp3";
    public static final String CONSUME      = "assets/drink.mp3";
    public static final String WOODHIT      = "assets/woodHit.mp3";
    public static final String SWORDHIT     = "assets/swordHit.mp3";
    public static final String HITMETAL     = "assets/hitmetal.mp3";
    public static final String UNARMEDHIT   = "assets/hit.mp3";
    public static final String WATERDIP     = "assets/waterDip.mp3";
    public static final String WATERJUMP    = "assets/waterJump.mp3";
    public static final String JUMP         = "assets/jump.mp3";
    public static final String WALK_WOOD    = "assets/walkWood.mp3";
    public static final String WOODCRACK    = "assets/woodCrack.mp3";
    public static final String MOVINGTREES  = "assets/movingTrees.mp3";
    public static final String PICKUP_EPIC= "assets/epicPickUp.mp3";
    public static final String WALK_DIZZY= "assets/dizzyWalk.mp3";

    //atmosphere tracks.
    public static final String BACKGROUND_WIND= "assets/wind.mp3";
    public static final String TENSION= "assets/tension.mp3";
    public static final String BACKGROUND_FOREST = "assets/Forest.mp3";

    private static Map<String, MediaPlayer> looping = new HashMap<>();

    private static JFXPanel panel = new JFXPanel();

    public static final void playMP3(String file) {
        playMP3Times(file, 1);
    }

    public static final void stopAllLoopsExcept(String file){
        for(String key : looping.keySet()){
            if(!key.equals(file)) {
                stopLoop(key);
            }
        }
    }

    public static final void stopLoop(){
        for(String file : looping.keySet()){
            stopLoop(file);
        }
    }

    public static final void stopLoop(String file){
        if(looping.containsKey(file)){
            looping.get(file).setCycleCount(0);
            looping.get(file).stop();
            looping.remove(file);
        }
    }


    public static final void playMP3(String file, boolean loop){
        if(loop)
            playMP3Times(file, -1);
        else
            playMP3Times(file, 1);
    }
    public static final void playMP3Times(String file, int count){

        if(!looping.containsKey(file)){
            Media hit = new Media(new File(file).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.setCycleCount(count);
            if(count == -1)
                looping.put(file, mediaPlayer);
            mediaPlayer.play();
            mediaPlayer.setVolume(1);
        }


    }

}
