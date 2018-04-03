package nl.mijnbedrijf.geldwisselapp.controller;

import nl.mijnbedrijf.geldwisselapp.model.Model;

abstract public class Controller<M extends Model> {

	protected M model;

	public Controller(M model) {
		this.model = model;
	}
}
