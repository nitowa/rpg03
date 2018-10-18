package myFirstGame.Items;

import GameLogic.Inventory.Items.SharpWeapon;
import GameLogic.UI.UIColors;

public class RustySword extends SharpWeapon {

    public RustySword() {
        super("Short Sword", "A Small Sword", 1, UIColors.RARITY_SHITTY);
    }
}
