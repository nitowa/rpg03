package myFirstGame.Items;

import GameLogic.Inventory.Items.Item;
import GameLogic.State.UI.UIColors;

import java.awt.*;

public class Plank extends Item {
    public Plank() {
        super("Plank", "A remarkably intact plank.", UIColors.DEFAULT_TEXT_COLOR);
    }
}
