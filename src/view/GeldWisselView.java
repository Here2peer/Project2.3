package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import controller.*;
import model.*;

public class GeldWisselView extends AbstractView<GeldWisselController, GeldWisselModel> { // JPanel

    private Label resultaat;



    public GeldWisselView(GeldWisselController controller, GeldWisselModel model) {
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
