package view;

import model.Model;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel {
	protected Model model;

	public AbstractView(Model model) {
		this.model = model;
		model.addView(this);
	}
	
	public Model getModel() {
		return model;
	}
	
	public void updateView() {
		repaint();
	}
}
