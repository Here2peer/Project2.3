package controller;

import model.AbstractModel;
import model.GameModel;

public class GameController extends AbstractController<GameModel> {

    public GameController(GameModel model) {
        super(model);
    }

    //TODO: implement controller
    public void clickedButton(int col, int row) {
        System.out.println("Location [" + row + ", " + col + "] was clicked. Notifying model.");
    }


}
