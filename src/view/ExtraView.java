package view;

import javafx.scene.control.TextField;
import controller.*;
import model.*;


public class ExtraView extends AbstractView {

    private TextField t;
    public ExtraView(AbstractController controller, AbstractModel model) {
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
