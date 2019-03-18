package uk.zebington.cinemaenterpriso.controllers;

/**
 * @author Aspen Thompson
 */
public class AdminPanelController extends PageController {
    public AdminPanelController() {
        super("adminPanel");
    }

    @Override
    public String getTitle() {
        return "Admin Panel";
    }
}
