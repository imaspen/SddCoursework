package uk.zebington.cinemaenterpriso.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.entities.*;

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
    @FXML
    public HBox editButtons;

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

    @FXML
    public void resetChanges() {
        updateFields(theaters.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void commitChanges() {
        Theater theater = theaters.getSelectionModel().getSelectedItem();
        try {
            Integer seats = Integer.valueOf(theaterSeats.getText());
            Price price = Price.fromString(theaterPrice.getText());
            AgeRating ageRating = AgeRating.fromString(movieRating.getText());
            theater.setId(theaterId.getText());
            theater.setSeats(seats);
            theater.setPrice(price);
            Movie movie = theater.getShowingMovie();
            movie.setTitle(movieTitle.getText());
            movie.setAgeRating(ageRating);
            movie.setGenre(movieGenre.getText());
            movie.setDescription(movieDescription.getText());
            PersistenceManager.writeInstance(TheaterList.getInstance(), "TheaterList.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTitle() {
        return "Admin Panel";
    }
}
