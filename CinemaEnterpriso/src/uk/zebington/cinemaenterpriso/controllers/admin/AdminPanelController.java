package uk.zebington.cinemaenterpriso.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.entities.*;
import uk.zebington.cinemaenterpriso.entities.singletons.TheaterList;
import uk.zebington.cinemaenterpriso.entities.singletons.TicketList;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;
import uk.zebington.cinemaenterpriso.exceptions.PriceFormatException;

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
    public ComboBox<AgeRating> movieRating;
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

        System.out.println(movieRating);
        movieRating.getItems().setAll(AgeRating.values());

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
        movieRating.getSelectionModel().selectedItemProperty().addListener(o -> changeMade());
        movieGenre.textProperty().addListener(o -> changeMade());
        movieDescription.textProperty().addListener(o -> changeMade());
    }

    private void updateFields(Theater theater) {
        theaterId.setText(theater.getId());
        theaterSeats.setText(theater.getSeats().toString());
        theaterPrice.setText(theater.getPrice().toString());
        Movie movie = theater.getShowingMovie();
        movieTitle.setText(movie.getTitle());
        movieRating.getSelectionModel().select(movie.getAgeRating());
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
        ArrayList<Ticket> toUpdate = new ArrayList<>();
        for (Ticket ticket : TicketList.getInstance()) {
            if (ticket.getTheater().equals(theater)) toUpdate.add(ticket);
        }

        ArrayList<String> erroredFields = new ArrayList<>();
        String id = theaterId.getText();
        if (id.length() == 0) {
            erroredFields.add("ID");
        }
        Integer seats = 0;
        try {
            seats = Integer.valueOf(theaterSeats.getText());
        } catch (NumberFormatException e) {
            erroredFields.add("Seats");
        }
        Price price = null;
        try {
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

        if (!erroredFields.isEmpty()) {
            createThemedWarning(
                    "Invalid Values",
                    "The following fields are invalid: " + String.join(", ", erroredFields) + "."
            );
            return;
        }
        theater.setId(id);
        theater.setSeats(seats);
        theater.setPrice(price);
        Movie movie = theater.getShowingMovie();
        movie.setTitle(title);
        movie.setAgeRating(ageRating);
        movie.setGenre(genre);
        movie.setDescription(description);
        PersistenceManager.writeInstance(TheaterList.getInstance(), "TheaterList.ser");
        toUpdate.forEach(ticket -> ticket.setTheater(theater));
        PersistenceManager.writeInstance(TicketList.getInstance(), "TicketList.ser");
        resetChangesMade();
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
        createThemedWarning("Unsaved Changes", "Please either save or reset your changes.");
        return false;
    }

    private void createThemedWarning(String headerText, String contentText) {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.getDialogPane().getStylesheets().add(this.getParent().getScene().getStylesheets().get(0));
        warning.setHeaderText(headerText);
        warning.setContentText(contentText);
        warning.show();
    }

    @Override
    public String getTitle() {
        return "Admin Panel";
    }
}
