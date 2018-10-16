package myFirstGame.Items;

import GameLogic.Inventory.Items.BootsArmor;
import GameLogic.State.UI.UIColors;

import java.awt.*;

public class LeatherBoots extends BootsArmor {
    public LeatherBoots() {
        super("Leather Boots", "Some rather old leather boots.", 1, UIColors.RARITY_SHITTY);
    }
}
