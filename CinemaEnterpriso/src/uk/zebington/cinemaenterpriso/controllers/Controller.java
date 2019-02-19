package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class Controller {
    private Parent parent;

    public Controller(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../boundaries/" + fxmlName + ".fxml"));
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
