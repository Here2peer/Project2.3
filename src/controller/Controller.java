package controller;
import model.*;

import javax.swing.*;
import java.awt.*;


public class Controller extends AbstractController {
    public JLabel turnLabel;

    public Controller(AbstractGameModel model) {
        super(model);
    }

    public void newGame() {
        System.out.println("New Game!");
        int[][] multi = new int[3][3];
        model.setGridView(multi);
        model.setTurn(true);
        model.setGameState(true);
    }
}

