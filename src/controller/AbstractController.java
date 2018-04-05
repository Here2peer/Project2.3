package controller;
import model.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	protected ModelTicTacToe model;

	public AbstractController(ModelTicTacToe model) {
		this.model = model;
	}
}
