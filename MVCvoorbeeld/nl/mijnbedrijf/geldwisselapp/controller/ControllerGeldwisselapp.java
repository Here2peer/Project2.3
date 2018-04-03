package nl.mijnbedrijf.geldwisselapp.controller;

import nl.mijnbedrijf.geldwisselapp.model.ModelGeldwisselapp;

public class ControllerGeldwisselapp extends Controller<ModelGeldwisselapp> {

	public ControllerGeldwisselapp(ModelGeldwisselapp model) {
		super(model);
	}

	public void berekenen_knop(String invoer) {
		double d = Double.parseDouble(invoer);
		model.set_bedrag_in(d);
	}

}
