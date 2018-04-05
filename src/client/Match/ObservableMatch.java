package client.Match;

import java.util.ArrayList;

/**
 * Created by Rick Huizing on 4-4-2018.
 */
public abstract class ObservableMatch {
    private ArrayList<TurnObserver> turnObservers = new ArrayList<>();
    private ArrayList<MoveObserver> moveObservers = new ArrayList<>();

    protected boolean yourTurn = false;

    public void registerObserver(TurnObserver turnObserver){
        if(!turnObservers.contains(turnObserver)){
            turnObservers.add(turnObserver);
        }
    }
    public void registerObserver(MoveObserver moveObserver){
        if(!moveObservers.contains(moveObserver)){
            moveObservers.add(moveObserver);
        }
    }

    public void removeObserver(TurnObserver turnObserver){
        turnObservers.remove(turnObserver);
    }
    public void removeObserver(MoveObserver moveObserver){
        moveObservers.remove(moveObserver);
    }

    private void notifyTurnObservers(){
        for(TurnObserver turnObserver:turnObservers){
            turnObserver.changeInTurn();
        }
    }

    private void notifyMoveObservers(String move){
        for(MoveObserver moveObserver: moveObservers){
            moveObserver.move(move);
        }
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
        notifyTurnObservers();
    }

    public void makeMove(String move) {
        notifyMoveObservers(move);
    }

}
