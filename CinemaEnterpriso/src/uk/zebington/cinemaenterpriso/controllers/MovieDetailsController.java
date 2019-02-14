package uk.zebington.cinemaenterpriso.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uk.zebington.cinemaenterpriso.entities.Movie;

public class MovieDetailsController implements TakesData<Movie>, HasPrevious {
    private Movie movie;
    private Scene previousScene;

    public Label movieTitle;
    public Label movieDescription;
    public ImageView movieRating;

    public void initData(Movie movie) {
        this.movie = movie;
        this.movieTitle.setText(movie.getTitle());
        this.movieDescription.setText(movie.getDescription());
        this.movieRating.setImage(new Image("/" + movie.getAgeRating() + ".png"));
    }

    @Override
    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    @Override
    public void loadPreviousScene() {
        ((Stage)this.movieTitle.getScene().getWindow()).setScene(previousScene);
    }
}
