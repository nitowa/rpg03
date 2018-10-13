package GameLogic.State;

public class YouDied extends Throwable {

    private Unit unit;

    public YouDied(Unit unit){
        this.unit = unit;
    }
}
