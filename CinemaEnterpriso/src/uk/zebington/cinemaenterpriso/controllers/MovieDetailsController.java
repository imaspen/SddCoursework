package uk.zebington.cinemaenterpriso.controllers;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uk.zebington.cinemaenterpriso.entities.Movie;

public class MovieDetailsController extends AbstractController implements HasTitle {
    public Label movieDescription;
    public ImageView movieRating;
    private Movie movie;

    public MovieDetailsController(Movie movie) {
        super("movieDetails");
        this.movie = movie;
        this.movieDescription.setText(movie.getDescription());
        this.movieRating.setImage(new Image("/" + movie.getAgeRating() + ".png"));
    }

    @Override
    public String getTitle() {
        return this.movie.getTitle();
    }
}
