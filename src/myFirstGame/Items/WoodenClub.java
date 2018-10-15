package myFirstGame.Items;

import GameLogic.Inventory.Items.Weapon;
import GameLogic.State.UI.UIColors;

public class WoodenClub extends Weapon {
    public WoodenClub() {
        super("Wooden Club", "A club belonging to a Forest Trogg", 1, UIColors.RARITY_SHITTY);
    }

    @Override
    public String toString() {
        return "Wooden Club";
    }
}


