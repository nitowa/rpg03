package myFirstGame.Items;

import GameLogic.Inventory.Items.Item;
import GameLogic.UI.UIColors;

public class Rock extends Item {
    public Rock() {
        super("Rock", "A plain old rock.", UIColors.DEFAULT_TEXT_COLOR);
    }
}
