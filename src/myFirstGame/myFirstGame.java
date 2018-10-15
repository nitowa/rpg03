package myFirstGame;

import GameLogic.Game;
import GameLogic.Inventory.Inventory;


import GameLogic.State.Player;
import GameLogic.State.State;
import GameLogic.State.UI.Log;
import GameLogic.State.UI.*;
public class myFirstGame extends Game {


    public myFirstGame(){
        Log log = new WindowLog(1500,UIColors.DEFAULT_BACKGROUND_COLOR, UIColors.DEFAULT_TEXT_COLOR);//new ConsoleLog();
        //Log log = new BadWindowLog();//new ConsoleLog();
        Inventory inventory = new Inventory(log, 10);
        Player player = new rpgPlayer("Player", 10,0,1, inventory, log);

        new G1R0(0, log, player);
        new RockyRoom(-1, log, player);
        new G1R1(1, log, player);
        new G1R2(2, log, player);
        new G1R3(3, log, player);
        new G1R4(4, log, player);
        new G1R5(5, log, player);
        State first =  new G1RDFE(6,log,player);
        new G1R7(7, log, player);
        new G1R8(8, log, player);
        new G1R9(9, log, player);
        new G1R10(10, log, player);
        new G1R11(11, log, player);

        try {
            Thread.sleep(550);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        JukeBox.playMP3Times(JukeBox.WALK, 5);

        log.slowPrintln("Test started.");
        log.printAscii("/cat.jpg");

        first.enter();
        //printMap(log, first);

    }
}
