package myFirstGame.Items;

import GameLogic.Inventory.Items.SharpWeapon;
import GameLogic.UI.UIColors;

public class ShortSword extends SharpWeapon {

    public ShortSword() {
        super("Short Sword", "A Small Sword", 1, UIColors.RARITY_SHITTY);
    }
}
