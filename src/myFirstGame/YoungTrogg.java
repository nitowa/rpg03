package myFirstGame;

import GameLogic.State.*;
import GameLogic.State.UI.Log;
import GameLogic.State.UI.UIColors;

import java.awt.*;

public class YoungTrogg extends Enemy {

    private boolean helmetOff = false;

    public YoungTrogg(Player p, Log log) {
        super("Trogg", 3, 1, 1, p, log);
    }

    @Override
    protected void performAction(Unit target) throws YouDied {


        log.slowPrintln("The Trogg swings his club!");
        log.slowerPrintln("Grhrauaa!");
        player.takeDamage(1);

    }


    @Override
    public int calculateDamageDealt() {

        return 0;
    }

    @Override
    public int calculateDamageTaken(int dmg, String attackTarget) {

        if (attackTarget.equals("helmet") || attackTarget.equals("helm") || attackTarget.equals("head")){
            helmetOff = true;
            log.slowPrintln("You swing and land a critical hit on the Troggs head, successfully knocking of its helmet!");
            return 1;
        }else if (helmetOff) {
            return maxHP-1;
        }else{
            switch ((int) (Math.random() * 3)) {
                case 0:
                    log.slowPrintln("You randomly swing and miss the Trogg completely!");
                    return 0;
                case 1:
                    log.slowPrintln("You randomly swing and hit the trogg in the shoulder. Your hand hurts.");
                    return 0;
                case 2:
                    log.slowPrintln("You randomly swing and land a hit on the Troggs hardened belly.");
                    return 0;
            }
        }
        return 0;


    }
    public void takeDamage(int dmg, String damageTarget) throws YouDied{
        System.out.println(damageTarget);


        int taken = calculateDamageTaken(dmg, damageTarget);
        this.currHP -= taken;
        log.print(name + " took ");
        log.printlnColored(taken + " damage", UIColors.DAMAGE_RED);
        if(currHP <= 1) {
            log.slowerPrintln("You finally land a blow on the putrid creatures head! In its dazed state, the Trogg runs away.");

            throw new YouDied(this);
        }
    }
}
