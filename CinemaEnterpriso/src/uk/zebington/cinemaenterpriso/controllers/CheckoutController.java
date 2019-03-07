package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * @author Aspen Thompson
 */
public class CheckoutController extends PageController {
    @FXML
    public RadioButton cashRadio;
    @FXML
    public RadioButton cardRadio;

    public CheckoutController() {
        super("checkout");
        ToggleGroup toggleGroup = new ToggleGroup();
        cashRadio.setToggleGroup(toggleGroup);
        cardRadio.setToggleGroup(toggleGroup);
        cashRadio.setSelected(true);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == cashRadio) {
                cashSelected();
            } else if (newValue == cardRadio) {
                cardSelected();
            }
        });
    }

    @Override
    public String getTitle() {
        return "Checkout";
    }

    private void cashSelected() {
        //TODO
    }

    private void cardSelected() {
        //TODO
    }
}
