package main;

import model.Model;
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
		gridView = new GridView(model);
		controller = new Controller(model);

		mainFrame=new JFrame("Reversi - ITV2G, Groep 2");
		mainFrame.setSize(800, 600);
		mainFrame.setResizable(true);
		mainFrame.setLayout(null);
		mainFrame.getContentPane().add(gridView);
        mainFrame.getContentPane().add(controller);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.white);

		gridView.setBounds(10,00,550,550);
		gridView.setBackground(Color.RED);
		gridView.updateView();

		controller.setBounds(570,0,200,550);
		controller.setBackground(Color.WHITE);
	}
}
