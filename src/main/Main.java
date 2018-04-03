package main;

import model.ModelTicTacToe;
import controller.*;
import view.MainFrame;


public class Main{

	public Main() {
		ModelTicTacToe model = new ModelTicTacToe();
		Controller controller = new Controller(model);
		MainFrame mainFrame = new MainFrame(controller, model);
	}
}
