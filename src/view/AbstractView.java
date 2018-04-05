package view;

import javafx.scene.layout.VBox;
import controller.AbstractController;
import model.AbstractModel;

abstract public class AbstractView<C extends AbstractController, M extends AbstractModel> extends VBox {

    protected C controller;
    protected M model;

    abstract public void updateView();

    public AbstractView(C controller, M model) {
        this.controller = controller;
        this.model = model;
    }
}
