package model;

import controller.Controller;

public class GameModel{
    private TicTacToe tictactoe;
    private ModelOthello othello;
    private Controller controller;
    public GameModel(){
    }

    public TicTacToe getTicTacToe(){
        tictactoe = new TicTacToe();
        return tictactoe;
    }

    public ModelOthello getOthello() {
        othello = new ModelOthello();
        return othello;
    }

    public void initiateController(){

    }
}
