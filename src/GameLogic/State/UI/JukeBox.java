package GameLogic.State.UI;


import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;

public class JukeBox extends JFXPanel {

    public static final String TEXT_BLIP= "textType.mp3";
    public static final String WALK= "Walk.mp3";
    public static final String PICKUP= "pickUp.mp3";
    public static final String DROP= "drop.mp3";
    public static final String EQUIP= "equip.mp3";
    public static final String UNEQUIP= "unequip.mp3";
    public static final String CONSUME= "drink.mp3";
    public static final String WOODHIT= "woodHit.mp3";
    public static final String SWORDHIT= "swordHit.mp3";
    public static final String HITMETAL= "hitmetal.mp3";
    public static final String UNARMEDHIT= "hit.mp3";
    public static final String WATERDIP= "waterDip.mp3";
    public static final String WATERJUMP= "waterJump.mp3";
    public static final String JUMP= "jump.mp3";
    public static final String WALK_WOOD= "walkWood.mp3";
    public static final String WOODCRACK= "woodCrack.mp3";
    public static final String MOVINGTREES= "movingTrees.mp3";

    //atmosphere tracks.
    public static final String BACKGROUND_WIND= "wind.mp3";


    private static JFXPanel panel = new JFXPanel();


    public static final void playMP3(String file){

        Media hit = new Media(new File(file).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        mediaPlayer.setVolume(1);
    }

}
