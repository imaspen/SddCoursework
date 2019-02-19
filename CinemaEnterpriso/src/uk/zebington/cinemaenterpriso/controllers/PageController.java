package uk.zebington.cinemaenterpriso.controllers;

public abstract class PageController extends Controller {
    public PageController(String fxmlName) {
        super(fxmlName);
    }

    public abstract String getTitle();
}
