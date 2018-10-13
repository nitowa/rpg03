package myFirstGame.Items;

import GameLogic.Inventory.Items.Item;
import GameLogic.State.UI.UIColors;

import java.awt.*;

public class Charcoal extends Item {
    public Charcoal() {
        super("Charcoal", "An ashy piece of charcoal.", UIColors.DEFAULT_TEXT_COLOR);
    }
}
