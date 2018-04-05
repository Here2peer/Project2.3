package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Rick Huizing on 4-4-2018.
 */
public abstract class AbstractPlayer {
    BlockingQueue<String> move = new ArrayBlockingQueue<String>(1);
    /**
     * notifies the player that he must make his move.
     * the default implementation won't get you far...
     */
    public void makeMove(){
        String move = "0";
        makeMove(move);
    }

    /**
     * makes the player make a move.
     * @param move the move
     */
    void makeMove(String move){
        this.move.offer(move);
    }

    /**
     * retrieves player move
     * blocks the thread until a move is submitted
     * @return the player's move
     */
    public String getMove(){
        try {
            return move.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public abstract void firstToMove(boolean firstPlayerToMove);
}
