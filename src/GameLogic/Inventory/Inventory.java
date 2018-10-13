package GameLogic.Inventory;

import java.util.ArrayList;
import java.util.List;

import GameLogic.Inventory.Items.ChestArmor;
import GameLogic.Inventory.Items.EquippableItem;
import GameLogic.Inventory.Items.HelmArmor;
import GameLogic.Inventory.Items.Item;
import GameLogic.Inventory.Items.LegArmor;
import GameLogic.Inventory.Items.Weapon;
import GameLogic.State.UI.Log;

public class Inventory {

    private int size;
    private Log log;
    private Weapon weapon;
    private HelmArmor helmet;
    private ChestArmor chest;
    private LegArmor legs;
    private List<Item> bag = new ArrayList<>();

    public Inventory(Log log, int size){
        this.size = size;
        this.log = log;
    }

    public void add(Item item) throws BagFullThrowable{
        if(bag.size() == size) {
            throw new BagFullThrowable();
        }
        log.print("You received " );
        log.printlnColored(item.toString(), item.getColor());
        bag.add(item);
    }

    public int getArmorStat(){
        int armorsum = 0;
        if(helmet != null)
            armorsum+=helmet.getArmorValue();
        if(chest != null)
            armorsum+=chest.getArmorValue();
        if(legs != null)
            armorsum+=legs.getArmorValue();

        return armorsum;
    }

    public int getAttackStat(){
        if(weapon != null)
           return weapon.getAttack();
        return 0;
    }

    public Item getItemByIndexOrName(String s){
        Item ret;
        try {
            int tryWhatAsInt = Integer.parseInt(s);
            ret = getItem(tryWhatAsInt);
        }catch (Exception e){
            ret = getItem(s);
        }
        return ret;
    }

    private Item getItem(int index){
        return bag.get(index-1);
    }

    private Item getItem(String name){
        for(Item i : bag){
            if(i.toString().toLowerCase().equals(name.toLowerCase()))
                return i;
        }
        return null;
    }

    public void listItems(){
        log.println("\n+------ EQUIPMENT ------+");
        if(weapon != null) {
            log.print("|  Weapon: ");
            log.printlnItemColored(weapon);
        }
        if(helmet != null) {
            log.print("|  Helmet: ");
            log.printlnItemColored(helmet);
        }
        if(chest != null) {
            log.print("|  Chest: ");
            log.printlnItemColored(chest);
        }
        if(legs != null) {
            log.print("|  Legs: ");
            log.printlnItemColored(legs);
        }
        log.println("+--------  BAG  --------+");

        if(bag.size() == 0) {
            log.println("|  (empty)");
        }else {
            for (int i = 0; i < bag.size(); i++) {
                log.print("|  " + (i + 1) + ") ");
                log.printlnItemColored(bag.get(i));
            }
        }
        log.println("+-----------------------+");
    }

    public void equip(EquippableItem item) {
        if(!bag.contains(item))
            return;

        EquippableItem tmp = null;
        if (item instanceof HelmArmor){
            if(this.helmet != null)
                tmp = this.helmet;
            this.helmet = (HelmArmor) item;
        }

        if (item instanceof ChestArmor){
            if(this.chest != null)
                tmp = this.chest;
            this.chest = (ChestArmor) item;
        }

        if(item instanceof LegArmor) {
            if(this.legs != null)
                tmp = this.legs;
            this.legs = (LegArmor) item;
        }

        if(item instanceof Weapon) {
            if(this.weapon != null)
                tmp = this.weapon;
            this.weapon = (Weapon) item;
        }
        bag.remove(item);
        if(tmp != null) {
            bag.add(tmp);
        }
        log.print("Equipped ");
        log.printlnItemColored(item);
    }

    public void unequip(String slot){
        if(bag.size() == size){
            log.print("The inventory is full. Can't unequip.");
            return;
        }

        EquippableItem item = null;

        switch (slot){
            case "legs":
                item = this.legs;
                this.legs = null;
                break;

            case "chest":
                item = this.chest;
                this.chest = null;
                break;

            case "helm":
                item = this.helmet;
                this.helmet = null;
                break;

            case "weapon":
                item = this.weapon;
                this.weapon = null;
                break;
            default:
                //check if maybe the names match
                if(this.weapon != null && this.weapon.toString().toLowerCase().equals(slot)){
                    item = this.weapon;
                    this.weapon = null;
                }else if (this.helmet != null && this.helmet.toString().toLowerCase().equals(slot)){
                    item = this.helmet;
                    this.helmet = null;
                }else if (this.legs != null && this.legs.toString().toLowerCase().equals(slot)){
                    item = this.legs;
                    this.legs = null;
                }else if (this.chest != null && this.chest.toString().toLowerCase().equals(slot)){
                    item = this.chest;
                    this.chest = null;
                }else{
                    return;
                }
        }
        try {
            this.add(item);
        } catch (BagFullThrowable bagFullThrowable) {
            //can't happen
        }
    }

    public void silentRemove(Item item){
        bag.remove(item);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public HelmArmor getHelmet() {
        return helmet;
    }

    public ChestArmor getChest() {
        return chest;
    }

    public LegArmor getLegs() {
        return legs;
    }
}
