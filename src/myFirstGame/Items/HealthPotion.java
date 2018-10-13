package myFirstGame.Items;

import GameLogic.Inventory.Items.ConsumableItem;


public class HealthPotion extends ConsumableItem {

    public HealthPotion() {
        super("Health Potion", "A health potion");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
