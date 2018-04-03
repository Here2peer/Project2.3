package model;

import java.util.ArrayList;

public class Model extends AbstractModel {

	//constructor van class
	public Model() {
		super();
	}
	
	//update het scherm
	public void tick() {
		notifyViews();
	}
}
