package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import uk.zebington.cinemaenterpriso.entities.Movie;
import uk.zebington.cinemaenterpriso.entities.Purchasable;
import uk.zebington.cinemaenterpriso.entities.Theater;
import uk.zebington.cinemaenterpriso.entities.Ticket;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class TheaterListItemController extends Controller {
    private Theater theater;

    @FXML
    public Label movieName;
    @FXML
    public Label movieGenre;
    @FXML
    public Label movieRating;
    @FXML
    public Label ticketsAvailable;
    @FXML
    public Spinner<Integer> ticketsAmountSelector;

    TheaterListItemController(Theater theater) {
        super("theaterListItem");
        this.theater = theater;
        Movie movie = this.theater.getShowingMovie();
        this.movieName.setText(movie.getTitle());
        this.movieGenre.setText(movie.getGenre());
        this.movieRating.setText(movie.getAgeRating());
        Integer ticketsAvailable = theater.getTicketsAvailable();
        this.ticketsAvailable.setText("" + ticketsAvailable);
        ((SpinnerValueFactory.IntegerSpinnerValueFactory)this.ticketsAmountSelector.getValueFactory()).setMax(Math.min(ticketsAvailable, 10));
    }

    @FXML
    public void showDetails() {
        PageContainerController.MAIN_PAGE.loadNewPage(new MovieDetailsController(theater.getShowingMovie()));
    }

    @FXML
    public void bookTickets() {
        ArrayList<Purchasable> basket = new ArrayList<>();
        for (int i = 0; i < ticketsAmountSelector.getValue(); i++) {
            basket.add(new Ticket(theater));
        }
        PageContainerController.MAIN_PAGE.loadNewPage(new ViewBasketController(basket));
    }
}
