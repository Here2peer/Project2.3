package nl.mijnbedrijf.geldwisselapp.view;

import javafx.scene.layout.VBox;
import nl.mijnbedrijf.geldwisselapp.controller.Controller;
import nl.mijnbedrijf.geldwisselapp.model.Model;

abstract public class View<C extends Controller, M extends Model> extends VBox {

	protected C controller;
	protected M model;

	abstract public void updateView();

	public View(C controller, M model) {
		this.controller = controller;
		this.model = model;
	}
}