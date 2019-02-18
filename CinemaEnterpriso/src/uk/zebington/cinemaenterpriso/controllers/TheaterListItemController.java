package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uk.zebington.cinemaenterpriso.entities.Movie;
import uk.zebington.cinemaenterpriso.entities.Theater;

/**
 * @author Aspen Thompson
 */
public class TheaterListItemController extends AbstractController {
    private Theater theater;

    @FXML
    public Label movieName;
    @FXML
    public Label movieGenre;
    @FXML
    public Label movieRating;

    TheaterListItemController(Theater theater) {
        super("movieListItem");
        this.theater = theater;
        Movie movie = this.theater.getShowingMovie();
        this.movieName.setText(movie.getTitle());
        this.movieGenre.setText(movie.getGenre());
        this.movieRating.setText(movie.getAgeRating());
    }

    @FXML
    public void showDetails() {
        PageContainerController.MAIN_PAGE.loadNewPage(new MovieDetailsController(theater.getShowingMovie()));
    }
}
