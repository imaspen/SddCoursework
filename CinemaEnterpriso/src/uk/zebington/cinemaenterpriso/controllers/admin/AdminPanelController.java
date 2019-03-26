package uk.zebington.cinemaenterpriso.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.controllers.admin.addon.AdminAddOnTabController;
import uk.zebington.cinemaenterpriso.controllers.admin.theater.AdminTheaterTabController;
import uk.zebington.cinemaenterpriso.boundaries.modals.WarningModal;
import uk.zebington.cinemaenterpriso.entities.singletons.DrinksList;
import uk.zebington.cinemaenterpriso.entities.singletons.SnacksList;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class AdminPanelController extends PageController {
    @FXML
    public Tab theaterTab;
    @FXML
    public Tab foodTab;
    @FXML
    public Tab drinkTab;

    private ArrayList<AdminTabController> tabControllers;

    public AdminPanelController() {
        super("admin/adminPanel", 2);
        tabControllers = new ArrayList<>();

        AdminTabController theaterTabController = new AdminTheaterTabController();
        theaterTab.setContent(theaterTabController.getParent());
        tabControllers.add(theaterTabController);

        AdminAddOnTabController foodTabController = new AdminAddOnTabController(SnacksList.getInstance());
        foodTab.setContent(foodTabController.getParent());
        tabControllers.add(foodTabController);

        AdminAddOnTabController drinkTabController = new AdminAddOnTabController(DrinksList.getInstance());
        drinkTab.setContent(drinkTabController.getParent());
        tabControllers.add(drinkTabController);
    }

    @Override
    public boolean beforeBack() {
        for (AdminTabController tab : tabControllers) {
            if (tab.getChangesMade()) {
                new WarningModal("Unsaved Changes", "Please either save or reset your changes.").show();
                return false;
            }
        }
        return true;
    }

    @Override
    public String getTitle() {
        return "Admin Panel";
    }
}
