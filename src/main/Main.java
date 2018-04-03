package main;

import model.Model;
import view.AbstractView;
import view.GridView;
import controller.*;
import view.TictactoeView;

import java.awt.Color;

import javax.swing.JFrame;

public class Main{
	
	private Model model;
	private JFrame mainFrame;
	private AbstractView gameView;
	private AbstractView tictactoeView;
	private AbstractController controller;

	private boolean mode = true;

	/*

	Boolean mode -> true -> Reversi
					false -> Tictactoe

	*/

	public Main() {
		model = new Model();

		if(mode) {
			gameView = new GridView(model);
		} else {
			gameView = new TictactoeView(model);
		}

		controller = new Controller(model);

		mainFrame=new JFrame("Reversi - ITV2G, Groep 2");
		mainFrame.setSize(800, 600);
		mainFrame.setResizable(true);
		mainFrame.setLayout(null);
		mainFrame.getContentPane().add(gameView);
        mainFrame.getContentPane().add(controller);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.white);

		gameView.setBounds(10,00,550,550);
		gameView.setBackground(Color.WHITE);
		gameView.updateView();

		controller.setBounds(570,0,200,550);
		controller.setBackground(Color.WHITE);
	}
}
