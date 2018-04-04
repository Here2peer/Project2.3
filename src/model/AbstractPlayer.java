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
    void makeMove(){
        String move = "0";
        makeMove(move);
    }

    /**
     * makes the player make a move.
     * Should block until move is made.
     * @param move the move
     */
    void makeMove(String move){
        this.move.offer(move);
    }

    String getMove(){
        try {
            return move.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
