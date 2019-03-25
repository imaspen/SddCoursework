package uk.zebington.cinemaenterpriso.boundaries.modals;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Aspen Thompson
 */
public class PasswordModal extends Stage {
    private String password;
    private PasswordField passwordField;

    public PasswordModal(String password) {
        super();
        this.password = password;
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER_RIGHT);
        container.setSpacing(5);
        container.setPadding(new Insets(5));

        HBox inputBox = new HBox();
        inputBox.setAlignment(Pos.CENTER);
        Label label = new Label("Password: ");
        this.passwordField = new PasswordField();
        this.passwordField.setOnAction(event -> this.close());
        inputBox.getChildren().addAll(label, this.passwordField);

        Button button = new Button("Confirm");
        button.setOnAction(event -> this.close());

        container.getChildren().addAll(inputBox, button);

        Scene scene = new Scene(container);
        scene.getStylesheets().add(getClass().getResource("../../CinemaEnterpriso.css").toExternalForm());
        this.setScene(scene);
    }

    public Boolean matching() {
        return password.equals(passwordField.getText());
    }
}
