package controller;

import model.AbstractModel;

abstract public class AbstractController<M extends AbstractModel> {
    protected M model;

    public AbstractController(M model) {
        this.model = model;
    }
}
