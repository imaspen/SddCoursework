package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import uk.zebington.cinemaenterpriso.entities.PersistenceManager;
import uk.zebington.cinemaenterpriso.entities.Purchasable;
import uk.zebington.cinemaenterpriso.entities.Ticket;
import uk.zebington.cinemaenterpriso.entities.TicketList;

import java.util.ArrayList;

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

    private ArrayList<Purchasable> basket;
    private CardDetailsController cardDetails;
    private CashGivenController cashGiven;

    public CheckoutController(ArrayList<Purchasable> basket) {
        super("checkout");
        this.basket = basket;

        cardDetails = new CardDetailsController();
        cashGiven = new CashGivenController();

        ToggleGroup toggleGroup = new ToggleGroup();
        cashRadio.setToggleGroup(toggleGroup);
        cardRadio.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == cashRadio) {
                cashSelected();
            } else if (newValue == cardRadio) {
                cardSelected();
            }
        });
        cashRadio.setSelected(true);
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

    @FXML
    public void confirmPurchase() {
        for (Purchasable purchasable : basket) {
            if (purchasable instanceof Ticket) {
                TicketList.getInstance().add((Ticket) purchasable);
            }
        }
        PersistenceManager.writeInstance(TicketList.getInstance(), "TicketList.ser");
        PageContainerController.getInstance().loadNewPage(new ReceiptController(basket));
        PageContainerController.getInstance().resetHistory();
    }
}
