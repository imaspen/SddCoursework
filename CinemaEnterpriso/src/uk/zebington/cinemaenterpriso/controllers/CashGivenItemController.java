package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import uk.zebington.cinemaenterpriso.entities.Price;

/**
 * @author Aspen Thompson
 */
public class CashGivenItemController extends Controller {
    @FXML
    public Label denominationLabel;
    @FXML
    public Spinner<Integer> amountGiven;

    private Price denomination;

    public CashGivenItemController(Price denomination) {
        super("cashGivenItem");
        denominationLabel.setText(denomination.toString());
    }
}
