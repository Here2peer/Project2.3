package controller;
import Game.AbstractGame;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	protected AbstractGame model;

	public AbstractController(AbstractGame model) {
		this.model = model;
	}

}
