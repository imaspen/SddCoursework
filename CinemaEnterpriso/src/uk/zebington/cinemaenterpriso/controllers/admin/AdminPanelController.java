package uk.zebington.cinemaenterpriso.controllers.admin;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.entities.*;
import uk.zebington.cinemaenterpriso.entities.singletons.TheaterList;
import uk.zebington.cinemaenterpriso.entities.singletons.TicketList;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import java.util.ArrayList;

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
    @FXML
    public Button addButton;
    @FXML
    public Button removeButton;
    @FXML
    public Button resetButton;
    @FXML
    public Button saveButton;

    private boolean changesMade;

    public AdminPanelController() {
        super("admin/adminPanel", 2);
        theaters.getItems().setAll(TheaterList.getInstance());
        theaters.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFields(newValue));
        theaters.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        theaters.getSelectionModel().selectFirst();

        addChangeListeners();

        changesMade = false;
    }

    private void addChangeListeners() {
        theaterId.textProperty().addListener(o -> changeMade());
        theaterSeats.textProperty().addListener(o -> changeMade());
        theaterPrice.textProperty().addListener(o -> changeMade());
        movieTitle.textProperty().addListener(o -> changeMade());
        movieRating.textProperty().addListener(o -> changeMade());
        movieGenre.textProperty().addListener(o -> changeMade());
        movieDescription.textProperty().addListener(o -> changeMade());
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
        resetChangesMade();
    }

    @FXML
    public void resetChanges() {
        updateFields(theaters.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void commitChanges() {
        Theater theater = theaters.getSelectionModel().getSelectedItem();
        try {
            ArrayList<Ticket> toUpdate = new ArrayList<>();
            for (Ticket ticket : TicketList.getInstance()) {
                if (ticket.getTheater().equals(theater)) toUpdate.add(ticket);
            }
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
            toUpdate.forEach(ticket -> ticket.setTheater(theater));
            PersistenceManager.writeInstance(TicketList.getInstance(), "TicketList.ser");
            resetChangesMade();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addTheater() {
        try {
            Theater theater = new Theater(
                    "New Theater",
                    new Movie("New Movie", AgeRating.U, "", ""),
                    200,
                    new Price(0)
            );
            TheaterList.getInstance().add(theater);
            PersistenceManager.writeInstance(TheaterList.getInstance(), "TheaterList.ser");
            theaters.getItems().add(theater);
            theaters.getSelectionModel().select(theater);
            changeMade();
            resetButton.setDisable(true);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void removeTheater() {
        Theater theater = theaters.getSelectionModel().getSelectedItem();
        TheaterList.getInstance().remove(theater);
        theaters.getItems().remove(theater);
        PersistenceManager.writeInstance(TheaterList.getInstance(), "TheaterList.ser");
    }

    @FXML
    public void changeMade() {
        if (!changesMade) {
            changesMade = true;
            addButton.setDisable(true);
            removeButton.setDisable(true);
            resetButton.setDisable(false);
            saveButton.setDisable(false);
            theaters.setDisable(true);
        }
    }

    private void resetChangesMade() {
        changesMade = false;
        addButton.setDisable(false);
        removeButton.setDisable(false);
        resetButton.setDisable(true);
        saveButton.setDisable(true);
        theaters.setDisable(false);
        theaters.refresh();
    }

    @Override
    public boolean beforeBack() {
        if (!changesMade) return true;
        Alert saveWarning = new Alert(Alert.AlertType.WARNING);
        saveWarning.getDialogPane().getStylesheets().add(this.getParent().getScene().getStylesheets().get(0));
        saveWarning.setHeaderText("Unsaved Changes");
        saveWarning.setContentText("Please either save or reset your changes.");
        saveWarning.show();
        return false;
    }

    @Override
    public String getTitle() {
        return "Admin Panel";
    }
}
