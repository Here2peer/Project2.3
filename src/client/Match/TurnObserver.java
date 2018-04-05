package client.Match;

/**
 * Created by Rick Huizing on 4-4-2018.
 */
public abstract class TurnObserver {

    public abstract void changeInTurn();

    public abstract void move(String move);

}
