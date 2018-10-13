package GameLogic.State;

import GameLogic.State.UI.Log;

public abstract class CombatState extends State {

    public CombatState(int id, Log log, Player player, String searchText){
        super(id, log, player, searchText);

        try {
            actions.put("attack", this.getClass().getMethod("attack", String.class));
            actions.put("cast", this.getClass().getMethod("cast", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public abstract void attack(String who);
    public abstract void cast(String spell);

    @Override
    public void help(String s){
        super.help(null);
        log.println("|"
                      + "\n+-  Combat"
                      + "\n| "
                      + "\n|  attack  [target]"
                      + "\n|  cast    [spell]"
        );
    }
}
