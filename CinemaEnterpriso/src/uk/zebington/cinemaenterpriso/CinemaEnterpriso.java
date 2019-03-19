package uk.zebington.cinemaenterpriso;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uk.zebington.cinemaenterpriso.controllers.PageContainerController;
import uk.zebington.cinemaenterpriso.controllers.TheaterListController;
import uk.zebington.cinemaenterpriso.entities.PersistenceManager;
import uk.zebington.cinemaenterpriso.entities.Theater;
import uk.zebington.cinemaenterpriso.entities.TheaterList;
import uk.zebington.cinemaenterpriso.entities.TicketList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CinemaEnterpriso extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.setProperty("prism.allowhidpi", "true");
        primaryStage.setTitle("Cinema Enterpiso");
        addIcons(primaryStage);
        primaryStage.setScene(new Scene(PageContainerController.getInstance().getParent()));
        primaryStage.setMaximized(true);
        PageContainerController.getInstance().loadNewPage(new TheaterListController(TheaterList.getInstance()));
        primaryStage.show();
    }

    private void addIcons(Stage stage) {
        String[] resolutions = {"16", "32", "48", "64", "96", "128", "256"};
        for (String resolution : resolutions) {
            stage.getIcons().add(new Image("icons/CinemaEnterpriso" + resolution + ".png"));
        }
    }
}
