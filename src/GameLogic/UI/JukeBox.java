package GameLogic.UI;


import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class JukeBox extends JFXPanel {

    public static final String TEXT_BLIP    = "/textType.mp3";
    public static final String WALK         = "/Walk.mp3";
    public static final String PICKUP       = "/pickUp.mp3";
    public static final String DROP         = "/drop.mp3";
    public static final String EQUIP        = "/equip.mp3";
    public static final String UNEQUIP      = "/unequip.mp3";
    public static final String CONSUME      = "/drink.mp3";
    public static final String WOODHIT      = "/woodHit.mp3";
    public static final String SWORDHIT     = "/swordHit.mp3";
    public static final String HITMETAL     = "/hitmetal.mp3";
    public static final String UNARMEDHIT   = "/hit.mp3";
    public static final String WATERDIP     = "/waterDip.mp3";
    public static final String WATERJUMP    = "/waterJump.mp3";
    public static final String JUMP         = "/jump.mp3";
    public static final String WALK_WOOD    = "/walkWood.mp3";
    public static final String WOODCRACK    = "/woodCrack.mp3";
    public static final String MOVINGTREES  = "/movingTrees.mp3";
    public static final String PICKUP_EPIC  = "/epicPickUp.mp3";
    public static final String WALK_DIZZY   = "/dizzyWalk.mp3";

    //atmosphere tracks.
    public static final String BACKGROUND_WIND = "/wind.mp3";
    public static final String BACKGROUND_FOREST = "/Forest.mp3";
    public static final String TENSION      = "/tension.mp3";

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

            Media hit = null;
            try {
                hit = new Media(JukeBox.class.getResource(file).toURI().toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.setCycleCount(count);
            if(count == -1)
                looping.put(file, mediaPlayer);
            mediaPlayer.play();
            mediaPlayer.setVolume(1);
        }


    }

}
