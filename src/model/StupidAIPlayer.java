package model;

import java.util.LinkedList;

/**
 * Created by Rick Huizing on 5-4-2018.
 */
public class StupidAIPlayer extends AbstractPlayer {

    private LinkedList<String> moves = new LinkedList<>();

    public StupidAIPlayer(){

    }

    @Override
    public void makeMove(){
        makeMove(moves.pop());
    }


    public void firstToMove(boolean firstPlayerToMove) {
        if (firstPlayerToMove) {
            System.out.println("you start the game");
            moves.addLast("MOVE 19");
            moves.addLast("MOVE 17");
        } else {
            System.out.println("you do not start the game");
            moves.addLast("MOVE 18");
            moves.addLast("MOVE 19");
        }
    }
}
