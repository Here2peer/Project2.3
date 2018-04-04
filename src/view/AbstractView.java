package view;

import model.Model;
import model.ModelTicTacToe;
import controller.*;
import model.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractView<C extends Controller, M extends ModelOthello> extends JPanel {
	protected C controller;
	protected M model;

	abstract public void updateView();

	public AbstractView(C controller, M model) {
		this.controller = controller;
		this.model = model;
	}
	
	public ModelOthello getModel() {
		return model;
	}
	

}
