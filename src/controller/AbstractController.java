package controller;
import model.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	protected ModelOthello model;

	public AbstractController(ModelOthello model) {
		this.model = model;
	}

}
