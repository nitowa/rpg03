package myFirstGame;

import GameLogic.State.*;
import GameLogic.UI.JukeBox;
import GameLogic.UI.Log;
import GameLogic.UI.UIColors;

public class YoungTrogg extends Enemy {

    private boolean helmetOff = false;

    public YoungTrogg(Player p, Log log) {
        super("Trogg", 3, 1, 1, p, log);
    }


    boolean troggDead = false;
    boolean notfirstLine = false;

    public boolean getHelmetoff() {
        return helmetOff;
    }

    public boolean troggDone () {
        return troggDead;

    }

    @Override
    protected void performAction(Unit target) throws YouDied {

        if (!notfirstLine) {
            log.unitSay(this, "You try to take HELM?! Gruahhaa!");
            log.slowPrintln("The Trogg swings his club!");
            player.takeDamage(1);
            JukeBox.playMP3(JukeBox.WOODHIT);
            notfirstLine = true;
        } else {
            log.unitSay(this, "Gruahhaa!");
            log.slowPrintln("The Trogg swings his club!");
            player.takeDamage(1);
            JukeBox.playMP3(JukeBox.WOODHIT);
        }

    }
    @Override
    public int calculateDamageDealt() {
        return 0;
    }

    @Override
    public int calculateDamageTaken(int dmg, String attackTarget) throws YouDied {

        if(currHP <= 1 && (attackTarget.equals("head"))) {
            JukeBox.playMP3(JukeBox.UNARMEDHIT);
            log.slowerPrintln("You land a blow on the putrid creatures head! In its dazed state, the Trogg drops its club and stumbles away.");
            troggDead = true;
            throw new YouDied(this);

        }

        if (!helmetOff && (attackTarget.equals("helmet") || attackTarget.equals("helm") || attackTarget.equals("head"))){
            helmetOff = true;
            JukeBox.playMP3(JukeBox.HITMETAL);
            log.slowPrintln("You swing and land a critical hit on the Troggs head, successfully knocking of its helmet!");
            return 1;
        }
        else if (helmetOff && (attackTarget.equals("helm") || attackTarget.equals("helmet"))) {
            JukeBox.playMP3(JukeBox.HITMETAL);
            log.slowPrintln("The helmet already lies lifeless on the ground.");
            return 0;
        }
        else if (helmetOff && attackTarget.equals("head")) {
            JukeBox.playMP3(JukeBox.UNARMEDHIT);
            log.slowerPrintln("You land a blow on the putrid creatures head! In its dazed state, the Trogg drops its club and stumbles away.");
            throw new YouDied(this);

        }else{
            switch (0) {
                case 0:
                    JukeBox.playMP3(JukeBox.UNARMEDHIT);
                    log.slowerPrintln("You swing and punch the Trogg on its hardened body. Your hand hurts. ");
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

    }
}
