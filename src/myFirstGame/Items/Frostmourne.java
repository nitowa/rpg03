package myFirstGame.Items;

import java.awt.*;

import GameLogic.Inventory.Items.Weapon;
import GameLogic.State.UI.UIColors;

public class Frostmourne extends Weapon {
    public Frostmourne() {
        super("Frostmourne", "Blade of the Icking", 50, UIColors.RARITY_LEGENDARY);
    }

    @Override
    public String toString() {
        return "Frostmourne";
    }
}
