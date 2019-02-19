package uk.zebington.cinemaenterpriso;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.zebington.cinemaenterpriso.controllers.PageContainerController;
import uk.zebington.cinemaenterpriso.controllers.TheaterListController;
import uk.zebington.cinemaenterpriso.entities.Theater;

public class CinemaEnterpriso extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.setProperty("prism.allowhidpi", "true");
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(PageContainerController.MAIN_PAGE.getParent()));
        primaryStage.setMaximized(true);
        PageContainerController.MAIN_PAGE.loadNewPage(new TheaterListController(Theater.OA4_01, Theater.SJG_38));
        primaryStage.show();
    }
}
