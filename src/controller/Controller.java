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
        System.out.println("New Game!");
        int[][] multi = new int[3][3];
        model.setGridView(multi);
        model.setTurn(true);
        model.setGameState(true);
        updateLabel();
    }

    public JLabel createJLabel() {
        turnLabel = new JLabel();
        turnLabel.setBounds(280, 20, 300, 50);
        turnLabel.setText("-");
        turnLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 25));

        return turnLabel;
    }

    public void updateLabel() {
        if (this.model.drawState()) {
            turnLabel.setText("Game draw!");
        } else {
            if (!this.model.getGameState()) {
                if (this.model.getTurn()) {
                    turnLabel.setText("Orange won!");
                } else {
                    turnLabel.setText("Black won!");
                }
            } else {
                if (this.model.getTurn()) {
                    turnLabel.setText("It's black turn.");
                } else {
                    turnLabel.setText("It's orange turn.");
                }
            }
        }
    }
}

