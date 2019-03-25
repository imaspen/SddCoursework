package uk.zebington.cinemaenterpriso.modals;

import javafx.scene.control.Alert;

/**
 * @author Aspen Thompson
 */
public class WarningModal extends Alert {
    public WarningModal(String header, String content) {
        super(AlertType.WARNING);
        this.getDialogPane().getStylesheets().add(getClass().getResource("../CinemaEnterpriso.css").toExternalForm());
        this.setHeaderText(header);
        this.setContentText(content);
    }
}
