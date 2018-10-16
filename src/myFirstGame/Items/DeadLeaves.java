package myFirstGame.Items;

import GameLogic.Inventory.Items.Item;
import GameLogic.UI.UIColors;

public class DeadLeaves extends Item {
    public DeadLeaves() {
        super("Dead Leaves", "Careful not to crush them.", UIColors.DEFAULT_TEXT_COLOR);
    }
}
