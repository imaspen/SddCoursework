package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 * @author Aspen Thompson
 */
public class CheckoutController extends PageController {
    @FXML
    public RadioButton cashRadio;
    @FXML
    public RadioButton cardRadio;
    @FXML
    public Pane checkoutPane;

    private CardDetailsController cardDetails;
    private CashGivenController cashGiven;

    public CheckoutController() {
        super("checkout");
        cardDetails = new CardDetailsController();
        cashGiven = new CashGivenController();

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
        checkoutPane.getChildren().clear();
        checkoutPane.getChildren().add(cashGiven.getParent());
    }

    private void cardSelected() {
        checkoutPane.getChildren().clear();
        checkoutPane.getChildren().add(cardDetails.getParent());
    }
}
