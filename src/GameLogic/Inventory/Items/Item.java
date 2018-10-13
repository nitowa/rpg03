package GameLogic.Inventory.Items;

import java.awt.*;
import java.io.Serializable;

import GameLogic.State.UI.UIColors;

public abstract class Item{
    protected String name;
    protected String description;
    protected Color color;

    public Item(String name, String description){
        this(name, description, UIColors.RARITY_ITEM_DEFAULT);
    }

    public Item(String name, String description, Color c){
        this.color = c;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public Color getColor(){
        return color;
    }

}
