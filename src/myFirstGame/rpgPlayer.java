package myFirstGame;

import GameLogic.Inventory.Inventory;
import GameLogic.Inventory.Items.ConsumableItem;
import GameLogic.Inventory.Items.UsableItem;
import GameLogic.State.UI.Log;
import GameLogic.State.Player;
import myFirstGame.Items.HealthPotion;

public class rpgPlayer extends Player {
    public rpgPlayer(String name, int maxHP, int baseAttack, int baseDamageRange, Inventory inventory,
                     Log log) {
        super(name, maxHP, baseAttack, baseDamageRange, inventory, log);
    }

    @Override
    public void useItem(UsableItem item) {
    }

    @Override
    public void consumeItem(ConsumableItem item) {
        if(item instanceof HealthPotion) {
            this.restoreHP(5);
        }
    }

}
