package main;

import model.ModelTicTacToe;
import view.GridView;
import controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.Color;


public class Main{
	private JFrame mainFrame;

	public Main() {
		ModelTicTacToe model = new ModelTicTacToe();
		Controller controller = new Controller(model);
		GridView view = new GridView(controller, model);

		mainFrame=new JFrame("Reversi - ITV2G, Groep 2");
		mainFrame.setSize(900, 800);
		mainFrame.setResizable(true);
		mainFrame.setLayout(null);
		mainFrame.getContentPane().add(view);
		mainFrame.getContentPane().add(controller);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.white);

		view.setBounds(80,80,550,550);
		//view.setBackground(Color.RED);
		view.updateView();

		controller.setBounds(740,80,200,550);
		//controller.setBackground(Color.BLUE);
	}
}
