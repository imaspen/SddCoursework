package uk.zebington.cinemaenterpriso.controllers;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uk.zebington.cinemaenterpriso.entities.Movie;

public class MovieDetailsController implements TakesData<Movie> {
    private Movie movie;

    public Label movieTitle;
    public Label movieDescription;
    public ImageView movieRating;

    public void initData(Movie movie) {
        this.movie = movie;
        this.movieTitle.setText(movie.getTitle());
        this.movieDescription.setText(movie.getDescription());
        this.movieRating.setImage(new Image("/" + movie.getAgeRating() + ".png"));
    }
}
