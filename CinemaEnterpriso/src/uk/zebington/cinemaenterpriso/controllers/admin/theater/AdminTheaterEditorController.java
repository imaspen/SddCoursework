package uk.zebington.cinemaenterpriso.controllers.admin.theater;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uk.zebington.cinemaenterpriso.controllers.admin.EditorController;
import uk.zebington.cinemaenterpriso.entities.*;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;
import uk.zebington.cinemaenterpriso.exceptions.PriceFormatException;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class AdminTheaterEditorController extends EditorController<Theater> {
    @FXML
    public TextField theaterId;
    @FXML
    public TextField theaterSeats;
    @FXML
    public TextField theaterPrice;
    @FXML
    public TextField movieTitle;
    @FXML
    public ComboBox<AgeRating> movieRating;
    @FXML
    public TextField movieGenre;
    @FXML
    public TextArea movieDescription;

    AdminTheaterEditorController() {
        super("admin/adminTheaterEditor", 2);

        movieRating.getItems().setAll(AgeRating.values());

        this.theaterId.textProperty().addListener(observable -> changeMade());
        this.theaterSeats.textProperty().addListener(observable -> changeMade());
        this.theaterPrice.textProperty().addListener(observable -> changeMade());
        this.movieTitle.textProperty().addListener(observable -> changeMade());
        this.movieRating.getSelectionModel().selectedItemProperty().addListener(observable -> changeMade());
        this.movieGenre.textProperty().addListener(observable -> changeMade());
        this.movieDescription.textProperty().addListener(observable -> changeMade());
    }

    @Override
    public void updateFields(Theater theater) {
        theaterId.setText(theater.getId());
        theaterSeats.setText(theater.getSeats().toString());
        theaterPrice.setText(theater.getPrice().toString());
        Movie movie = theater.getShowingMovie();
        movieTitle.setText(movie.getTitle());
        movieRating.getSelectionModel().select(movie.getAgeRating());
        movieGenre.setText(movie.getGenre());
        movieDescription.setText(movie.getDescription());
    }

    @Override
    public ArrayList<String> updateObject(Theater theater) {
        ArrayList<String> erroredFields = new ArrayList<>();
        String id = theaterId.getText();
        if (id.length() == 0) {
            erroredFields.add("ID");
        }
        Integer seats = 0;
        try {
            seats = Integer.valueOf(theaterSeats.getText());
            if (seats < theater.getSeats() - theater.getTicketsAvailable()) {
                erroredFields.add("Seats - more tickets sold than seats");
            }
        } catch (NumberFormatException e) {
            erroredFields.add("Seats");
        }
        Price price = null;
        try {
            System.out.println(theaterPrice.getText());
            price = Price.fromString(theaterPrice.getText());
        } catch (NegativePriceException | PriceFormatException e) {
            erroredFields.add("Price");
        }
        String title = movieTitle.getText();
        if (title.length() == 0) {
            erroredFields.add("Title");
        }
        AgeRating ageRating = movieRating.getValue();
        String genre = movieGenre.getText();
        if (genre.length() == 0) {
            erroredFields.add("Genre");
        }
        String description = movieDescription.getText();
        if (description.length() == 0) {
            erroredFields.add("Description");
        }

        if (erroredFields.isEmpty()) {
            theater.setId(id);
            theater.setSeats(seats);
            theater.setPrice(price);
            Movie movie = theater.getShowingMovie();
            movie.setTitle(title);
            movie.setAgeRating(ageRating);
            movie.setGenre(genre);
            movie.setDescription(description);
        }
        return erroredFields;
    }
}
