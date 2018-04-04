package main;

import model.TicTacToe;
import controller.*;
import view.MainView;


public class Main{

	public Main() {
		TicTacToe model = new TicTacToe();
		Controller controller = new Controller(model);
		MainView mainView = new MainView(controller, model);
	}
}
