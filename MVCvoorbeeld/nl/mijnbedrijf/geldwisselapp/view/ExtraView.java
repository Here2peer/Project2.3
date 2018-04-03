package nl.mijnbedrijf.geldwisselapp.view;

import javafx.scene.control.TextField;
import nl.mijnbedrijf.geldwisselapp.controller.Controller;
import nl.mijnbedrijf.geldwisselapp.model.Model;


public class ExtraView extends View {

	private TextField t;
	public ExtraView(Controller controller, Model model) {
		super(controller, model);
		t = new TextField("???");
		this.getChildren().add(t);
		model.addView(this);
	}

	@Override
	public void updateView() {
		t.setText("Er is een uitkomst!");
	}
}
