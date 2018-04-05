package controller;
import model.*;

import javax.swing.*;
import java.awt.*;


public class Controller extends AbstractController {
    public JLabel turnLabel;

    public Controller(ModelTicTacToe model) {
        super(model);
    }

    public void newGame() {
        model.newGame();
    }
}

