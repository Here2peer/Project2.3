package controller;
import model.*;


public class Controller extends AbstractController{


    public Controller(ModelTicTacToe model) {
        super(model);
    }

    public void newGame(){
        System.out.println("New Game!");
        int[][] multi = new int[3][3];
        model.setGridView(multi);
        model.setTurn(true);
        model.setGameState(true);
    }
}
