package nl.mijnbedrijf.geldwisselapp.model;

public class ModelGeldwisselapp extends Model {

	private double bedrag;
	private double bedrag_result;

	// euro's - ponden
	public void set_bedrag_in(double bedrag) {
		this.bedrag=bedrag;
		bedrag_result=bedrag/1.12903569;
		System.out.println(bedrag_result);
		notifyViews();
	}

	public double getBedragresult() {
		return bedrag_result;
	}
}
