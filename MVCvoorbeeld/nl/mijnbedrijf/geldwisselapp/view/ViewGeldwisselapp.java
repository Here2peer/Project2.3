package nl.mijnbedrijf.geldwisselapp.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nl.mijnbedrijf.geldwisselapp.controller.ControllerGeldwisselapp;
import nl.mijnbedrijf.geldwisselapp.model.ModelGeldwisselapp;

public class ViewGeldwisselapp extends View<ControllerGeldwisselapp, ModelGeldwisselapp> { // JPanel

	private Label resultaat;

	public ViewGeldwisselapp(ControllerGeldwisselapp controller, ModelGeldwisselapp model) {
		super(controller, model);
		Label label = new Label("Bedrag in euro's:");
		TextField invoer = new TextField();
		Button knop = new Button("Omrekenen");
		resultaat = new Label();

		this.getChildren().add(label);
		this.getChildren().add(invoer);
		this.getChildren().add(knop);
		this.getChildren().add(resultaat);

		// Extra (eenvoudige view)
		ExtraView ev = new ExtraView(null, model);
		this.getChildren().add(ev);

		knop.setOnAction(e -> {
			controller.berekenen_knop(invoer.getText());
		});

		model.addView(this);
	}

	public void updateView() {
		resultaat.setText("Resultaat: "+model.getBedragresult());
	}
}
