package GameLogic.Inventory.Items;

import java.awt.*;

public abstract class Armor extends EquippableItem{

    private int armorValue;

    public Armor(String name, String description, int armorValue, Color c){
        super(name, description, c);
        this.armorValue = armorValue;
    }

    public int getArmorValue() {
        return armorValue;
    }
}
