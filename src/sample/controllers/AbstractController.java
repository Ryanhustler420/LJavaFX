package sample.controllers;

public abstract class AbstractController {

    private ModelAccess access;

    public AbstractController(ModelAccess access) {
        this.access = access;
    }

    public ModelAccess getModelAccess() {
        return access;
    }

    public void setModelAccess(ModelAccess access) {
        this.access = access;
    }

}
