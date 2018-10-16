package myFirstGame.Items;

import GameLogic.Inventory.Items.Item;
import GameLogic.UI.UIColors;

import java.awt.*;

public class EmptyVial extends Item {
    public EmptyVial() {
        super("Empty Vial", "A relatively clean empty vial.", UIColors.DEFAULT_TEXT_COLOR);
    }
}
