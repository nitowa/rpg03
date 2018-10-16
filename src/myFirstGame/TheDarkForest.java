package myFirstGame;

import GameLogic.State.Enemy;
import GameLogic.State.Player;
import GameLogic.UI.Log;
import GameLogic.State.Unit;
import GameLogic.State.YouDied;

public class TheDarkForest extends Enemy {
    public TheDarkForest(Player p, Log log) {
        super("The Dark Forest",100,100, 1, p, log);
    }


    @Override
    public int calculateDamageDealt() {
        return 0;
    }

    @Override
    public int calculateDamageTaken(int dmg, String attackTarget) throws YouDied {
        return 0;
    }

    @Override
    protected void performAction(Unit target) throws YouDied {

    }
}
