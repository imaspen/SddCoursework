package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.entities.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Aspen Thompson
 */
public class MovieListController implements TakesData<ArrayList<Movie>> {
    private ArrayList<Movie> movies;
    public VBox movieContainer;

    @Override
    public void initData(ArrayList<Movie> movies) {
        this.movies = movies;
        addMoviesToList();
    }

    public void initData(Movie... movies) {
        this.movies = new ArrayList<>();
        this.movies.addAll(Arrays.asList(movies));
        addMoviesToList();
    }

    private void addMoviesToList() {
        for (Movie movie : movies) {
            movieContainer.getChildren().add(makeListItem(movie));
        }
    }

    private Parent makeListItem(Movie movie) {
        Parent movieListItem;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../boundaries/movieListItem.fxml"));
            movieListItem = loader.load();
            loader.<MovieListItemController>getController().initData(movie);
        } catch (IOException e) {
            e.printStackTrace();
            movieListItem = new Label("Error");
        }
        return movieListItem;
    }
}