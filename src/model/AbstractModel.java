package model;

import view.AbstractView;
import java.util.ArrayList;

abstract public class AbstractModel {

    private ArrayList<AbstractView> views = new ArrayList<AbstractView>();

    public void addView(AbstractView view) {
        views.add(view);
    }

    public void notifyViews() {
        for(AbstractView v: views) v.updateView();
    }

}
