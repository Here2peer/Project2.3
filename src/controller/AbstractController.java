package controller;
import model.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	protected AbstractGameModel model;

	public AbstractController(AbstractGameModel model) {
		this.model = model;
	}

}
