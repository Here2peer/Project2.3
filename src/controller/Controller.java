package controller;
import Game.AbstractGame;

import javax.swing.*;


public class Controller extends AbstractController {
    public JLabel turnLabel;

    public Controller(AbstractGame model) {
        super(model);
    }

    public void newGame() {
        model.newGame();
    }
}
