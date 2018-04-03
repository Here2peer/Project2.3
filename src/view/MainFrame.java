package view;

import controller.Controller;
import model.ModelTicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame implements ActionListener {

    private JFrame mainFrame;
    private JButton button;
    private JLabel turnLabel;

    private ModelTicTacToe model;
    private GridView view;
    private Controller controller;

    public MainFrame(Controller controller, ModelTicTacToe model){
                this.model = model;
                this.controller = controller;
                this.view = new GridView(controller, this.model);

                mainFrame=new JFrame("Game - ITV2G, Groep 2");
                mainFrame.setSize(800, 800);
                mainFrame.setResizable(true);
                mainFrame.setLayout(null);
                mainFrame.getContentPane().add(view);
                mainFrame.getContentPane().add(controller);
                mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mainFrame.setVisible(true);
                mainFrame.getContentPane().setBackground(Color.white);
                buttonReset();
                TurnLabel();
                view.updateView();
            }


    private void buttonReset(){
        this.view.setBounds(80,80,550,550);
        button = new JButton();
        button.setBounds(650,80,100,50);
        button.setText("New Game!");
        button.addActionListener(this);
        mainFrame.getContentPane().add(button);
    }

    private void TurnLabel(){
        turnLabel = new JLabel();
        turnLabel.setBounds(300,20, 300, 50);
        turnLabel.setText("Black turn");
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        mainFrame.getContentPane().add(turnLabel);
    }

    private void UpdateLabels(){
        if(this.model.getTurn()) {
            turnLabel.setText("It's black turn.");
        }else{
            turnLabel.setText("It's orange turn.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button) {
            this.controller.newGame();
            this.view.gameOver();
            this.view.updateView();
        }

    }
}
