import java.io.File;
import java.net.URL;

import GameLogic.State.UI.Img2Ascii;
import GameLogic.State.UI.JukeBox;
import myFirstGame.myFirstGame;

public class Launcher {

    public static void main(String[] args) {

        for(String s : System.getProperty("java.class.path").split(";")){
            System.out.println(s);
        }
        new myFirstGame();
    }
}
