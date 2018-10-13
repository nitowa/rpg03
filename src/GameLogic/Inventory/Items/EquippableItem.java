package GameLogic.Inventory.Items;

import java.awt.*;

public abstract class EquippableItem extends Item  {
    public EquippableItem(String name, String description, Color c) {
        super(name, description, c);
    }

    public EquippableItem(String name, String description) {
        super(name, description);
    }
}
