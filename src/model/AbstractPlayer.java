package model;

/**
 * Created by Rick Huizing on 4-4-2018.
 */
public interface AbstractPlayer {

    /**
     * notifies the player that he must make his move.
     */
    void makeMove();

    /**
     * makes the player make a move
     * @param move the move
     */
    void makeMove(String move);

    void win();
    void loss();
}
