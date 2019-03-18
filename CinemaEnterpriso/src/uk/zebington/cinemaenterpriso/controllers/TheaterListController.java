package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.entities.Theater;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Aspen Thompson
 */
public class TheaterListController extends PageController {
    private ArrayList<Theater> theaters;

    @FXML
    public VBox theaterContainer;

    public TheaterListController(ArrayList<Theater> theaters) {
        super("theaterList");
        this.theaters = theaters;
        addTheatersToList();
    }

    public TheaterListController(Theater... theaters) {
        super("theaterList");
        this.theaters = new ArrayList<>();
        this.theaters.addAll(Arrays.asList(theaters));
        addTheatersToList();
    }

    private void addTheatersToList() {
        for (Theater theater : this.theaters) {
            theaterContainer.getChildren().add(makeListItem(theater));
        }
    }

    private Parent makeListItem(Theater theater) {
        return (new TheaterListItemController(theater)).getParent();
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