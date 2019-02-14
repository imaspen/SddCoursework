package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uk.zebington.cinemaenterpriso.entities.Movie;

import java.io.IOException;

/**
 * @author Aspen Thompson
 */
public class MovieListItemController implements TakesData<Movie> {
    private Movie movie;
    public Label movieName;
    public Label movieGenre;
    public Label movieRating;
    public Button detailsButton;

    @Override
    public void initData(Movie movie) {
        this.movie = movie;
        this.movieName.setText(movie.getTitle());
        this.movieGenre.setText(movie.getGenre());
        this.movieRating.setText(movie.getAgeRating());
    }

    public void showDetails() {
        try {
            Stage stage = (Stage) this.detailsButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("boundaries/movieDetails.fxml"));
            Parent movieDetailsPage = loader.load();
            loader.<MovieDetailsController>getController().initData(this.movie);
            stage.setScene(new Scene(movieDetailsPage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
