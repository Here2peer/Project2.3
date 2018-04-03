package controller;
import view.*;
import main.*;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends AbstractController implements ActionListener {

    private JTextField textField;
    private JButton button;
    private JLabel whosTurn;

    public Controller(Model model) {
        super(model);
        setSize(200, 600);

        button = new JButton();
        textField = new JTextField();
        whosTurn = new JLabel();

        this.setLayout(null);

        button.setBounds(60,10,50,50);
        textField.setBounds(10,10,50,50);
        whosTurn.setBounds(10,60,100,100);

        add(button);
        button.addActionListener(this);

        add(textField);
        add(whosTurn);
        whosTurn.setText("Amoi eem");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button) {
            System.out.println("Button Pressed!");
            if(!textField.getText().isEmpty()) {
                String[] textFieldValues = textField.getText().split(",");
                System.out.println("y = " + textFieldValues[0] + " - x = " + textFieldValues[1]);
                int tempY = Integer.parseInt(textFieldValues[0]);
                int tempX = Integer.parseInt(textFieldValues[1]);
                //GridView.changeGrid(2, tempY, tempX);
                System.out.println("NOTHING");
            } else {
                System.out.println("Textfield is empty!");
            }

        }
    }
}
