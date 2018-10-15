package GameLogic.State;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import GameLogic.Inventory.BagFullThrowable;
import GameLogic.Inventory.Items.ConsumableItem;
import GameLogic.Inventory.Items.EquippableItem;
import GameLogic.Inventory.Items.Item;
import GameLogic.Inventory.Items.UsableItem;
import GameLogic.State.UI.JukeBox;
import GameLogic.State.UI.Log;
import GameLogic.State.UI.UIColors;

public abstract class State{

    protected String searchText;
    protected transient Log log;
    protected transient Player player;
    protected int id;

    protected transient Map<String, Method> actions = new HashMap<String, java.lang.reflect.Method>();
    protected Map<String, State> exits = null;
    protected Map<String, Item> takeableItems = new HashMap<>();

    public State(int id, Log log, Player player, String searchText){
        this(id, log, player);
        this.searchText = searchText;
    }

    private State(int id, Log log, Player player){
        this.id = id;
        this.log = log;
        this.player = player;
        setDefaultActions();
        MapManager.addTile(this);
    }

    private final void setDefaultActions(){
        try {
            actions.put("look", this.getClass().getMethod("look", String.class));
            actions.put("use", this.getClass().getMethod("use", String.class));
            actions.put("drop", this.getClass().getMethod("drop", String.class));
            actions.put("search", this.getClass().getMethod("search", String.class));
            actions.put("move", this.getClass().getMethod("move", String.class));
            actions.put("help", this.getClass().getMethod("help", String.class));
            actions.put("take", this.getClass().getMethod("take", String.class));
            actions.put("equip", this.getClass().getMethod("equip", String.class));
            actions.put("unequip", this.getClass().getMethod("unequip", String.class));
            actions.put("takeable", this.getClass().getMethod("takeable", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    protected final String readAndExecuteAction(){
        String identifier = log.read().trim().toLowerCase();
        String[] splitArgs = identifier.split(" ", 2);
        try {
            if(splitArgs.length > 1)
                actions.get(splitArgs[0]).invoke(this, splitArgs[1]);
            else
                actions.get(splitArgs[0]).invoke(this, "");
        } catch (Exception e) {
            //e.printStackTrace();
           log.println("Unknown command \'"+identifier+"\'");
        }
        if(splitArgs[0].equals("search") ||
           identifier.equals("look inventory") ||
           identifier.equals("look stats") ||
           splitArgs[0].equals("help"))
        {
           return readAndExecuteAction();
        }
        return identifier;
    }


    public void enter(){
        if(exits == null)
            this.exits = exits();

        //print introText and call play mechanic of this state
        play();
    }

    private final void play(){
        while (true)
            readAndExecuteAction();
    }

    public void use(String what){
        Item item = player.getInventory().getItemByIndexOrName(what);
        if (item instanceof ConsumableItem) {
            JukeBox.playMP3(JukeBox.CONSUME);
            log.slowPrint("You drink ");
            log.printlnItemColored(item);
            player.consumeItem((ConsumableItem) item);
            player.getInventory().silentRemove(item);
            return;
        }
        if(item instanceof UsableItem){
            log.print("You use ");
            log.printlnItemColored(item);
            player.useItem((UsableItem) item);
            player.getInventory().silentRemove(item);
        }else if(item == null) {
            log.slowPrintln("Unknown item");
        }else{
            log.print("Could not use ");
            log.printlnItemColored(item);
        }
    }

    public final void drop(String what){
        Item item = player.getInventory().getItemByIndexOrName(what);

        if(item != null){
            log.print("You dropped ");
            JukeBox.playMP3(JukeBox.DROP);
            log.printlnItemColored(item);
            addTakeable(item);
            player.getInventory().silentRemove(item);
        }
    }

    protected final void addTakeable(Item item){
        if(takeableItems.containsKey(item.toString().toLowerCase())){
            addTakeable(item, 1);
        }else
            takeableItems.put(item.toString().toLowerCase(), item);
    }

    private final void addTakeable(Item item, int index){
        if(takeableItems.containsKey(item.toString().toLowerCase()+" "+index)){
            addTakeable(item, index+1);
        }else
            takeableItems.put(item.toString().toLowerCase()+" "+index, item);
    }

    protected final void removeTakeable(String name){
        takeableItems.remove(name);
    }

    public final void equip(String what){
        Item item = player.getInventory().getItemByIndexOrName(what);

        if(item instanceof EquippableItem) {
            player.getInventory().equip((EquippableItem) item);
            JukeBox.playMP3(JukeBox.EQUIP);
        }else if(item == null){
            log.slowPrintln("Unknown item");
        }else{
            log.slowPrintln("Could not equip " + item);
        }
    }

    public final void unequip(String what){

        player.getInventory().unequip(what);
        JukeBox.playMP3(JukeBox.UNEQUIP);
    }

    public void help(String s){
        log.println("\n+-----------------------+"
                      + "\n|        Commands       |"
                      + "\n+-----------------------+"
                      + "\n|"
                      + "\n+------   General"
                      + "\n|"
                      + "\n|  look    [target]"
                      + "\n|  search  [target]"
                      + "\n|  take    [target]"
                      + "\n|  move    [direction]"
                      + "\n|  jump    [target]"
                      + "\n|  duck    [target]"
                      + "\n|  takeable"
                      + "\n|"
                      + "\n+------   Inventory"
                      + "\n|"
                      + "\n|  look inventory"
                      + "\n|  equip   [target/id]"
                      + "\n|  unequip [target/id]"
                      + "\n|  drop    [target/id]"
        );
    }

    public void look(String at){

        switch (at) {
            case "inventory":
                player.getInventory().listItems();
                break;
            case "stats":
                player.reportStats();
                break;
            case "":
                log.slowPrintln(searchText);
                if (exits != null && exits.size() > 0)
                    log.slowPrintColored(getExitTexts(), UIColors.DIRECTIONS);
                break;
        }
    }

    public final void takeable(String st){
        log.slowPrintln("Takeable items: ");
        for(Item i : takeableItems.values()){
            log.printlnItemColored(i);
        }

    }

    public void take(String what){
        if(!takeableItems.containsKey(what)){
            log.slowPrintln("Could not find any " + what);
            return;
        }
        Item item = takeableItems.get(what);
        try {
            player.getInventory().add(item);
            JukeBox.playMP3(JukeBox.PICKUP);
            removeTakeable(what);
        } catch (BagFullThrowable bagFullThrowable) {
            log.slowPrintln("Your inventory is full");
        }
    }

    private String getExitTexts(){
        String directions = "";
        for(String s : exits.keySet())
            directions += s+" ";

        return "Available exits: "+directions;
    }

    public void move(String where) {
        System.out.println(getExitTexts());
        System.out.println(where+" valid exit of "+this.getClass().getSimpleName()+"? "+exits.containsKey(where));

        State next = exits.get(where);
        System.out.println(this.getClass().getSimpleName()+" -["+where+"]-> "+next.getClass().getSimpleName());
        if(exits.containsKey(where)){
            JukeBox.playMP3(JukeBox.WALK);
            next.enter();
        }

    }

    public final int getId(){
        return id;
    }

    public abstract void search(String what);
    public abstract Map<String, State> exits();

}
