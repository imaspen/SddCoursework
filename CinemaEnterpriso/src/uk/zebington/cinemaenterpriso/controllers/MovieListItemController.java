package uk.zebington.cinemaenterpriso.controllers;

import javafx.scene.control.Label;
import uk.zebington.cinemaenterpriso.entities.Movie;

/**
 * @author Aspen Thompson
 */
public class MovieListItemController extends AbstractController {
    private Movie movie;
    public Label movieName;
    public Label movieGenre;
    public Label movieRating;

    public MovieListItemController(Movie movie) {
        super("movieListItem");
        this.movie = movie;
        this.movieName.setText(movie.getTitle());
        this.movieGenre.setText(movie.getGenre());
        this.movieRating.setText(movie.getAgeRating());
    }

    public void showDetails() {
        System.out.println(movie.getTitle());
        PageContainerController.MAIN_PAGE.loadNewPage(new MovieDetailsController(movie));
    }
}
