package myFirstGame.Items;

import GameLogic.Inventory.Items.UsableItem;

public class HealthPotion extends UsableItem {

    public HealthPotion() {
        super("Health Potion", "A health pot lol");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
