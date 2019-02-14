package uk.zebington.cinemaenterpriso;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.zebington.cinemaenterpriso.controllers.MovieDetailsController;
import uk.zebington.cinemaenterpriso.controllers.MovieListController;
import uk.zebington.cinemaenterpriso.controllers.TakesData;
import uk.zebington.cinemaenterpriso.entities.Movie;

import java.util.ArrayList;

public class CinemaEnterpriso extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.setProperty("prism.allowhidpi", "true");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("boundaries/movieList.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        loader.<MovieListController>getController().initData(Movie.THE_MATRIX, Movie.PAUL_BLART_2);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
