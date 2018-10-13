package GameLogic.State;

import GameLogic.State.UI.Log;

public abstract class Enemy extends Unit{
    protected transient Player player;

    public Enemy(String name, int maxHP, int baseAttack, int baseDamageRange, Player player, Log log) {
        super(name, maxHP, baseAttack, baseDamageRange, log);
        this.player = player;
    }

    protected abstract void performAction(Unit target) throws YouDied;
}
