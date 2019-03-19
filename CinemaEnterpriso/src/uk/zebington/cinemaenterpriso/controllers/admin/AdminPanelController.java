package uk.zebington.cinemaenterpriso.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.entities.Movie;
import uk.zebington.cinemaenterpriso.entities.Theater;
import uk.zebington.cinemaenterpriso.entities.TheaterList;

/**
 * @author Aspen Thompson
 */
public class AdminPanelController extends PageController {
    @FXML
    public ListView<Theater> theaters;
    @FXML
    public TextField theaterId;
    @FXML
    public TextField theaterSeats;
    @FXML
    public TextField theaterPrice;
    @FXML
    public TextField movieTitle;
    @FXML
    public TextField movieRating;
    @FXML
    public TextField movieGenre;
    @FXML
    public TextArea movieDescription;

    public AdminPanelController() {
        super("admin/adminPanel", 2);
        theaters.getItems().setAll(TheaterList.getInstance());
        theaters.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFields(newValue));
    }

    private void updateFields(Theater theater) {
        theaterId.setText(theater.getId());
        theaterSeats.setText(theater.getSeats().toString());
        theaterPrice.setText(theater.getPrice().toString());
        Movie movie = theater.getShowingMovie();
        movieTitle.setText(movie.getTitle());
        movieRating.setText(movie.getAgeRating().toString());
        movieGenre.setText(movie.getGenre());
        movieDescription.setText(movie.getDescription());
    }

    @Override
    public String getTitle() {
        return "Admin Panel";
    }
}
