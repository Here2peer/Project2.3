package controller;
import model.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	protected Model model;

	public AbstractController(Model model) {
		this.model = model;
	}

}
