package uk.zebington.cinemaenterpriso.controllers;

public abstract class PageController extends Controller {
    public PageController(String fxmlName) {
        super(fxmlName);
    }

    public boolean beforeBack() {return true;}

    public void onBack() {}

    public abstract String getTitle();
}
