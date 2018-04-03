package main;

import model.Model;
import model.TicTacToe;
import view.AbstractView;
import view.GridView;
import controller.*;

import java.awt.Color;

import javax.swing.JFrame;

public class Main{
	
	private Model model;
	private JFrame mainFrame;
	private AbstractView gridView;
	private AbstractController controller;
	
	public Main() {
		model = new Model();
		GridView gridview = new GridView(model);
		controller = new Controller(model);

		mainFrame=new JFrame("Reversi - ITV2G, Groep 2");
		mainFrame.setSize(800, 600);
		mainFrame.setResizable(true);
		mainFrame.setLayout(null);
		mainFrame.getContentPane().add(gridview);
        mainFrame.getContentPane().add(controller);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.white);

		gridview.setBounds(10,00,550,550);
		gridview.setBackground(Color.WHITE);
		gridview.updateView();

		controller.setBounds(570,0,200,550);
		controller.setBackground(Color.WHITE);
		TicTacToe game = new TicTacToe(gridview.getGridView());
	}
}
