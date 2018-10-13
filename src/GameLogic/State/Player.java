package GameLogic.State;

import GameLogic.Inventory.Inventory;
import GameLogic.Inventory.Items.UsableItem;
import GameLogic.State.UI.Log;

public abstract class Player extends Unit {

    private Inventory inventory;

    public Player(String name, int maxHP, int baseAttack, int baseDamageRange, Inventory inventory, Log log) {
        super(name, maxHP, baseAttack, baseDamageRange, log);
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int calculateAttackValue(){
        return (int)(baseAttack+Math.random()*baseDamageRange)+inventory.getAttackStat();
    }

    protected int calculateDefenseValue(){
        return inventory.getArmorStat();
    }

    public abstract void useItem(UsableItem item);

    @Override
    public int calculateDamageDealt() {
        return calculateAttackValue();
    }

    @Override
    public int calculateDamageTaken(int dmg, String target) {
        return dmg-calculateDefenseValue()<0?0:dmg-calculateDefenseValue();
    }
}
