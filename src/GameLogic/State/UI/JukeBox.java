package GameLogic.State.UI;


import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;

public class JukeBox extends JFXPanel {

    public static final String TEXT_BLIP= "textType.mp3";
    public static final String WALK= "Walk.mp3";
    private static JFXPanel panel = new JFXPanel();


    public static final void playMP3(String file){

        Media hit = new Media(new File(file).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        mediaPlayer.setVolume(1);
    }

}
