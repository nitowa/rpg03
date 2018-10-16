package myFirstGame.Items;

import GameLogic.Inventory.Items.Weapon;
import GameLogic.UI.UIColors;

public class Frostmourne extends Weapon {
    public Frostmourne() {
        super("Frostmourne", "Blade of the Icking", 50, UIColors.RARITY_LEGENDARY);
    }

    @Override
    public String toString() {
        return "Frostmourne";
    }
}
