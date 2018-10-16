package GameLogic.State;

import GameLogic.UI.Log;
import GameLogic.UI.UIColors;

public abstract class Unit {


    protected String name;
    protected int maxHP;
    protected int currHP;
    protected transient Log log;
    protected int baseAttack;
    protected int baseDamageRange;

    public Unit(String name, int maxHP, int baseAttack, int baseDamageRange, Log log){
        this.name = name;
        this.maxHP = maxHP;
        this.currHP = maxHP;
        this.baseAttack = baseAttack;
        this.baseDamageRange = baseDamageRange;
        this.log = log;
    }

    public void takeDamage(int dmg) throws YouDied{
        takeDamage(dmg, "");
    }

    public void takeDamage(int dmg, String attackTarget) throws YouDied{
        int taken = calculateDamageTaken(dmg, attackTarget);
        this.currHP -= taken;
        log.print(name + " took ");
        log.printlnColored(taken + " damage", UIColors.DAMAGE_RED);
        if(currHP <= 0) {
            log.println(name+" was slain");
            throw new YouDied(this);
        }
    }


    protected void restoreHP(int amount){
        currHP+=amount;
        if(currHP > maxHP)
            currHP = maxHP;
    }

    public void reportStats(){

        int colorPick = (int)(((double)currHP)/((double)maxHP) * 10)-1;
        System.out.println(colorPick);

        log.printlnColored("HP: "+currHP+"/"+maxHP, UIColors.HEALTH_GRADIENT[colorPick]);
        log.println("Weapon: "+calculateDamageDealt()+" attack");
        if(this instanceof Player)
            log.println("Armor: "+((Player)this).calculateDefenseValue());
    }
    public String getName() {
        return name;
    }

    public abstract int calculateDamageDealt();
    public abstract int calculateDamageTaken(int dmg, String attackTarget) throws YouDied;

}
