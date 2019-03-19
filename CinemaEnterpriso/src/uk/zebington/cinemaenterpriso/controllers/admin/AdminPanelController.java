package uk.zebington.cinemaenterpriso.controllers.admin;

import uk.zebington.cinemaenterpriso.controllers.PageController;

/**
 * @author Aspen Thompson
 */
public class AdminPanelController extends PageController {
    public AdminPanelController() {
        super("admin/adminPanel", 2);
    }

    @Override
    public String getTitle() {
        return "Admin Panel";
    }
}
