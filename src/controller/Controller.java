package controller;
import model.*;


public class Controller extends AbstractController{

    private int[][] multi;


    public Controller(ModelTicTacToe model) {
        super(model);
    }

    public void newGame(){
        System.out.println("New Game!");
        this.multi = new int[3][3];
        model.setGridView(this.multi);
        model.setTurn(true);
        model.setGameState(true);
    }
}
