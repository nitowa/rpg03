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

        State first = new G1R0(0, log, player);
        new RockyRoom(-1, log, player);
        new G1R1(1, log, player);
        new G1R2(2, log, player);
        new G1R3(3, log, player);
        new G1R4(4, log, player);
        new G1R5(5, log, player);
        new G1R6(6, log, player);

        try {
            Thread.sleep(550);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.slowPrintln("Test started.");

       //log.unitSay(player, "To remove this message delete it from myFirstGame. One of the only major cryptos with an actual use case. >Predicted to be $18k within 10 years by a major financial research paper So the use case is very real, but that use case is just to trade BTC into it and then trade it back for BTC after a short time frame to wash it and hide shady/criminal activity. Nobody wants to actually buy, trade, or hodl XRP long-term for speculative reasons and the eventual max supply is supposed to be about the same as BTC's, but without half the coins being lost like BTCs were. Somebody shill me on why it would ever pump to fall 2017 BTC levels or would be a better long-term investment than BTC or Holo?");

        first.enter();

    }
}
