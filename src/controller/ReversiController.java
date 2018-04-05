package controller;

import model.ReversiModel;

public class ReversiController extends GameController {

    public ReversiController(ReversiModel model) {
        super(model);
    }

    @Override
    public void clickedButton(int col, int row) {
        super.clickedButton(col, row);
        model.attemptMove(col, row);
    }
}
