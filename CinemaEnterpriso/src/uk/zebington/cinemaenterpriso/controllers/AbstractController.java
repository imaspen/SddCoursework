package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class AbstractController {
    private Parent page;

    public AbstractController(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../boundaries/" + fxmlName + ".fxml"));
            loader.setController(this);
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent getPage() {
        return page;
    }
}