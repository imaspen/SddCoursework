package uk.zebington.cinemaenterpriso.controllers.theaterlist;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.controllers.PageContainerController;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.controllers.admin.AdminPanelController;
import uk.zebington.cinemaenterpriso.entities.Theater;
import uk.zebington.cinemaenterpriso.entities.singletons.TheaterList;

import java.util.Collection;

/**
 * @author Aspen Thompson
 */
public class TheaterListController extends PageController {
    @FXML
    public VBox theaterContainer;

    public TheaterListController() {
        super("theaterlist/theaterList", 2);
        addTheatersToList(TheaterList.getInstance());
    }

    private void addTheatersToList(Collection<Theater> theaters) {
        for (Theater theater : theaters) {
            theaterContainer.getChildren().add(makeListItem(theater));
        }
    }

    private Parent makeListItem(Theater theater) {
        return (new TheaterListItemController(theater)).getParent();
    }

    @Override
    public void onBack() {
        theaterContainer.getChildren().clear();
        addTheatersToList(TheaterList.getInstance());
    }

    @FXML
    public void loadAdminPanel() {
        PageContainerController.getInstance().loadNewPage(new AdminPanelController());
    }

    @Override
    public String getTitle() {
        return "Movies";
    }
}