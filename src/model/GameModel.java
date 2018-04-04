package model;

import controller.Controller;

public class GameModel{
    private ModelTicTacToe tictactoe;
    private ModelOthello othello;
    private Controller controller;
    public GameModel(){
    }

    public ModelTicTacToe getTicTacToe(){
        tictactoe = new ModelTicTacToe();
        return tictactoe;
    }

    public ModelOthello getOthello() {
        othello = new ModelOthello();
        return othello;
    }

    public void initiateController(){

    }
}
