package main;

import controller.Controller;
import model.ModelTicTacToe;
import view.GridView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    private JFrame mainFrame;
    private JButton resetButton;
    private JLabel turnLabel;

    private ModelTicTacToe model;
    private GridView view;
    private Controller controller;

    public Main(){

            ModelTicTacToe model = new ModelTicTacToe();
            Controller controller = new Controller(model);

            view = new GridView(controller, model);
            view.setBounds(80,100,550,550);

            mainFrame=new JFrame("Game - ITV2G, Groep 2");
            mainFrame.setSize(730, 800);
            mainFrame.setResizable(true);
            mainFrame.setLayout(null);
            mainFrame.getContentPane().add(view);
            mainFrame.getContentPane().add(controller);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainFrame.setVisible(true);
            mainFrame.getContentPane().setBackground(Color.WHITE);

            addResetButton();
            createStateLabel();

            view.updateView();
        }


    private void addResetButton(){
        resetButton = new JButton();
        resetButton.setBounds(520,40,100,40);
        resetButton.setText("New Game!");
        resetButton.addActionListener(this);
        mainFrame.getContentPane().add(resetButton);
    }

    private void createStateLabel() {
        mainFrame.getContentPane().add(view.createJLabel());
        view.updateLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetButton) { //Als de resetbutton wordt gebruikt
            this.controller.newGame();
            this.view.gameOver();
            this.view.updateView();
        }
    }
}
