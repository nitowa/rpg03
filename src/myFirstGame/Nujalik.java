package myFirstGame;

import GameLogic.State.Enemy;
import GameLogic.UI.Log;
import GameLogic.State.Player;
import GameLogic.State.Unit;
import GameLogic.State.YouDied;

public class Nujalik extends Enemy {
    public Nujalik(Player p, Log log) {
        super("Nujalik", 15, 1, 3, p, log);
    }

    @Override
    public int calculateDamageDealt() {
        return 0;
    }

    @Override
    public int calculateDamageTaken(int dmg, String attackTarget) {
        return (int)(0.1*dmg);
    }

    @Override
    protected void performAction(Unit target) throws YouDied {
        switch ((int)(Math.random()*4)){
            case 0:
                log.unitSay(this, "You see Spektrum....", 20);
                player.takeDamage(2);
                break;
            case 1:
                log.unitSay(this, "Time for pet battles!", 20);
                player.takeDamage(1);
                break;
            case 2:
                log.unitSay(this, "Actually the real issue is...", 20);
                player.takeDamage(3);
                break;
            default:
                log.unitSay(this, "I will not be spoken to like a child!", 20);
                player.takeDamage(1);

        }
    }
}
