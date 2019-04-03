package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import uk.zebington.cinemaenterpriso.CinemaEnterpriso;

import java.io.IOException;

public abstract class Controller {
    private Parent parent;

    public Controller(String fxmlName) {
        try {
            String path = "boundaries/" + fxmlName + ".fxml";
            FXMLLoader loader = new FXMLLoader(CinemaEnterpriso.class.getResource(path));
            loader.setController(this);
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent getParent() {
        return parent;
    }
}
