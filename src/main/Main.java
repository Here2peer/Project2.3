package main;

import model.ModelTicTacToe;
import controller.*;
import view.MainView;


public class Main{

	public Main() {
		ModelTicTacToe model = new ModelTicTacToe();
		Controller controller = new Controller(model);
		MainView mainView = new MainView(controller, model);
	}
}
