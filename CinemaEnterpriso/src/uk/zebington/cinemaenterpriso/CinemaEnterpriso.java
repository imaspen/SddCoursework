package uk.zebington.cinemaenterpriso;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.zebington.cinemaenterpriso.controllers.MovieDetailsController;
import uk.zebington.cinemaenterpriso.entities.Movie;

public class CinemaEnterpriso extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.setProperty("prism.allowhidpi", "true");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("boundaries/movieDetails.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        loader.<MovieDetailsController>getController().initData(Movie.THE_MATRIX);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
