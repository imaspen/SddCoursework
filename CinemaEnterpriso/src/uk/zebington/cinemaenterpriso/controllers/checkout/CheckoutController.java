package uk.zebington.cinemaenterpriso.controllers.checkout;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.controllers.PageContainerController;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.controllers.ReceiptController;
import uk.zebington.cinemaenterpriso.controllers.checkout.cashgiven.CashGivenController;
import uk.zebington.cinemaenterpriso.entities.Basket;
import uk.zebington.cinemaenterpriso.entities.Purchasable;
import uk.zebington.cinemaenterpriso.entities.Ticket;
import uk.zebington.cinemaenterpriso.entities.singletons.TicketList;

/**
 * @author Aspen Thompson
 */
public class CheckoutController extends PageController {
    @FXML
    public Label basketCost;
    @FXML
    public RadioButton cashRadio;
    @FXML
    public RadioButton cardRadio;
    @FXML
    public Pane checkoutPane;
    @FXML
    public Button confirmButton;

    private Basket basket;
    private CardDetailsController cardDetails;
    private CashGivenController cashGiven;

    public CheckoutController(Basket basket) {
        super("checkout/checkout");
        this.basket = basket;

        basketCost.setText(basket.getTotalCost().toString());

        cashGiven = new CashGivenController(basket.getTotalCost(), this::enableDoneButton);
        cardDetails = new CardDetailsController(this::enableDoneButton);

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
        if (basket.getTotalCost().getAmount() > 200000) {
            cashRadio.setDisable(true);
            cardRadio.setSelected(true);
        } else {
            cashRadio.setSelected(true);
        }
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
        PersistenceManager.writeInstance(TicketList.getInstance());
        PageContainerController.getInstance().loadNewPage(new ReceiptController(basket));
        PageContainerController.getInstance().resetHistory();
    }

    private void enableDoneButton(Boolean paymentValid) {
        this.confirmButton.setDisable(!paymentValid);
    }
}
