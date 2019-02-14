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
public class MovieListController extends AbstractController implements HasTitle {
    private ArrayList<Movie> movies;
    public VBox movieContainer;

    public MovieListController(ArrayList<Movie> movies) {
        super("movieList");
        this.movies = movies;
        addMoviesToList();
    }

    public MovieListController(Movie... movies) {
        super("movieList");
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
        return (new MovieListItemController(movie)).getPage();
    }

    @Override
    public String getTitle() {
        return "Movies";
    }
}