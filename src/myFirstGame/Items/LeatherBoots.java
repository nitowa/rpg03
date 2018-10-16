package myFirstGame.Items;

import GameLogic.Inventory.Items.BootsArmor;
import GameLogic.UI.UIColors;

public class LeatherBoots extends BootsArmor {
    public LeatherBoots() {
        super("Leather Boots", "Some rather old leather boots.", 1, UIColors.RARITY_SHITTY);
    }
}
