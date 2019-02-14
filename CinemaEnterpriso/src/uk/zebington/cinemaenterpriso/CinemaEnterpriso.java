package uk.zebington.cinemaenterpriso;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.zebington.cinemaenterpriso.controllers.MovieListController;
import uk.zebington.cinemaenterpriso.controllers.PageContainerController;
import uk.zebington.cinemaenterpriso.entities.Movie;

public class CinemaEnterpriso extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("prism.allowhidpi", "true");
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(PageContainerController.MAIN_PAGE.getPage()));
        PageContainerController.MAIN_PAGE.loadNewPage(new MovieListController(Movie.THE_MATRIX, Movie.PAUL_BLART_2));
        primaryStage.show();
    }
}
