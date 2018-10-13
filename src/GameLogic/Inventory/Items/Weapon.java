package GameLogic.Inventory.Items;

import java.awt.*;

public class Weapon extends EquippableItem{

    private int attack;

    public Weapon(String name, String description, int attack, Color c){
        super(name, description, c);
        this.attack = attack;
    }

    public Weapon(String name, String description, int attack){
        super(name, description);
        this.attack = attack;
    }


    public int getAttack() {
        return attack;
    }
}
