package model;

import view.*;
import java.util.ArrayList;

abstract public class Model {

	private ArrayList<GridView> views=new ArrayList<GridView>();

	public void addView(GridView view) {
		views.add(view);
	}

	public void notifyViews() {
		for(GridView v: views) v.updateView();
	}

}
