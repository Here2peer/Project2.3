package view;

import Game.AbstractGame;
import controller.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractView<C extends Controller, M extends AbstractGame> extends JPanel {
	protected C controller;
	protected M model;

	abstract public void updateView();

	public AbstractView(C controller, M model) {
		this.controller = controller;
		this.model = model;
	}
	
	public AbstractGame getModel() {
		return model;
	}
	

}
