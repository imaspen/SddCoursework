package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uk.zebington.cinemaenterpriso.entities.Movie;

public class MovieDetailsController extends PageController {
    private Movie movie;

    @FXML
    public Label movieDescription;
    @FXML
    public ImageView movieRating;

    public MovieDetailsController(Movie movie) {
        super("movieDetails");
        this.movie = movie;
        this.movieDescription.setText(movie.getDescription());
        this.movieRating.setImage(new Image("/" + movie.getAgeRating().toString() + ".png"));
    }

    @Override
    public String getTitle() {
        return this.movie.getTitle();
    }
}
