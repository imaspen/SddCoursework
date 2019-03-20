package uk.zebington.cinemaenterpriso.controllers;

public abstract class PageController extends Controller {
    public PageController(String fxmlName) {
        super(fxmlName);
    }

    public PageController(String fxmlName, Integer depth) {
        super(fxmlName, depth);
    }

    public void onBack() {}

    public abstract String getTitle();
}
