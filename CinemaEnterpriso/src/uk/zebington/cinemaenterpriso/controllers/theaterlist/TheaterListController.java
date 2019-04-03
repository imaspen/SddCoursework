package uk.zebington.cinemaenterpriso.controllers.theaterlist;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.controllers.PageContainerController;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.controllers.admin.AdminPanelController;
import uk.zebington.cinemaenterpriso.entities.Theater;
import uk.zebington.cinemaenterpriso.entities.singletons.TheaterList;
import uk.zebington.cinemaenterpriso.boundaries.modals.PasswordModal;

import java.util.Collection;

/**
 * @author Aspen Thompson
 */
public class TheaterListController extends PageController {
    @FXML
    public VBox theaterContainer;

    private boolean highlightNext;

    public TheaterListController() {
        super("theaterlist/theaterList");
        highlightNext = false;
        addTheatersToList(TheaterList.getInstance());
    }

    private void addTheatersToList(Collection<Theater> theaters) {
        for (Theater theater : theaters) {
            Parent next = makeListItem(theater);
            if (highlightNext) next.getStyleClass().add("highlight");
            highlightNext = !highlightNext;
            theaterContainer.getChildren().add(next);
        }
    }

    private Parent makeListItem(Theater theater) {
        return (new TheaterListItemController(theater)).getParent();
    }

    @Override
    public void onBack() {
        highlightNext = false;
        theaterContainer.getChildren().clear();
        addTheatersToList(TheaterList.getInstance());
    }

    @FXML
    public void loadAdminPanel() {
        PasswordModal passwordModal = new PasswordModal("admin");
        passwordModal.showAndWait();
        if (passwordModal.matching()) {
            PageContainerController.getInstance().loadNewPage(new AdminPanelController());
        }
    }

    @Override
    public String getTitle() {
        return "Movies";
    }
}