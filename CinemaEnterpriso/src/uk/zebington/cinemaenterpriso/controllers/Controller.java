package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class Controller {
    private Parent parent;

    public Controller(String fxmlName, Integer depth) {
        try {
            StringBuilder pathStringBuilder = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                pathStringBuilder.append("../");
            }
            pathStringBuilder.append("boundaries/").append(fxmlName).append(".fxml");

            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathStringBuilder.toString()));
            loader.setController(this);
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Controller(String fxmlName) {
        this(fxmlName, 1);
    }

    public Parent getParent() {
        return parent;
    }
}
