package main;

import model.*;
import controller.*;
import view.MainFrame;


public class Main{

	public Main() {
		ModelTicTacToe model = new ModelTicTacToe();

		ModelOthelloBot modelOthelloBot = new ModelOthelloBot();
		ModelOthello modelOthello = new ModelOthello(modelOthelloBot);

		Controller controller = new Controller(modelOthello);

		MainFrame mainFrame = new MainFrame(controller, model);
	}
}
