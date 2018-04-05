package controller;

import model.*;

public class GeldWisselController extends AbstractController<GeldWisselModel> {

    public GeldWisselController(GeldWisselModel model) {
        super(model);
    }

    public void berekenen_knop(String invoer) {
        double d = Double.parseDouble(invoer);
        model.set_bedrag_in(d);
    }

}
