package uk.zebington.cinemaenterpriso.boundaries.modals;

import javafx.scene.control.Alert;
import uk.zebington.cinemaenterpriso.CinemaEnterpriso;

/**
 * @author Aspen Thompson
 */
public class WarningModal extends Alert {
    public WarningModal(String header, String content) {
        super(AlertType.WARNING);
        this.getDialogPane().getStylesheets().add(CinemaEnterpriso.class.getResource("CinemaEnterpriso.css").toExternalForm());
        this.setHeaderText(header);
        this.setContentText(content);
    }
}
