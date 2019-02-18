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
public class TheaterListController extends AbstractController implements HasTitle {
    private ArrayList<Theater> theaters;

    @FXML
    public VBox movieContainer;

    public TheaterListController(ArrayList<Theater> theaters) {
        super("theaterList");
        this.theaters = theaters;
        addMoviesToList();
    }

    public TheaterListController(Theater... theaters) {
        super("theaterList");
        this.theaters = new ArrayList<>();
        this.theaters.addAll(Arrays.asList(theaters));
        addMoviesToList();
    }

    private void addMoviesToList() {
        for (Theater theater : this.theaters) {
            movieContainer.getChildren().add(makeListItem(theater));
        }
    }

    private Parent makeListItem(Theater theater) {
        return (new TheaterListItemController(theater)).getPage();
    }

    @Override
    public String getTitle() {
        return "Movies";
    }
}