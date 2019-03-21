package uk.zebington.cinemaenterpriso.controllers.checkout.cashgiven;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.controllers.Controller;
import uk.zebington.cinemaenterpriso.controllers.checkout.CheckoutController;
import uk.zebington.cinemaenterpriso.entities.Price;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @author Aspen Thompson
 */
public class CashGivenController extends Controller {
    @FXML
    public VBox denominationsContainer;
    @FXML
    public Label givenTotal;
    @FXML
    public Label changeTotal;

    private ArrayList<CashGivenItemController> denominations;
    private Price target;
    private Consumer<Boolean> paymentValid;

    public CashGivenController(Price target, Consumer<Boolean> paymentValid) {
        super("checkout/cashgiven/cashGiven", 3);
        this.target = target;
        this.paymentValid = paymentValid;
        try {
            ArrayList<Price> denoms = new ArrayList<Price>() {
                {
                    add(new Price(2000));
                    add(new Price(1000));
                    add(new Price(500));
                    add(new Price(200));
                    add(new Price(100));
                    add(new Price(50));
                    add(new Price(20));
                    add(new Price(10));
                    add(new Price(5));
                    add(new Price(2));
                    add(new Price(1));
                }
            };
            denominations = new ArrayList<>();
            denoms.forEach(denom -> denominations.add(new CashGivenItemController(denom, this)));
            denominations.forEach(denomination -> denominationsContainer.getChildren().add(denomination.getParent()));
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    public void updateTotal() {
        Price total = new Price();
        denominations.forEach(controller -> total.addPrice(controller.getTotal()));
        givenTotal.setText(total.toString());
        calculateChange(total);
        calculateChangeTotal();
    }

    private void calculateChange(Price total) {
        if (target.getAmount() > total.getAmount()) {
            denominations.forEach(denomination -> denomination.setChangeAmount(0));
            paymentValid.accept(false);
            return;
        }
        try {
            Price changeRemaining = total.minus(target);
            for (CashGivenItemController denomination : denominations) {
                Integer denominationNeeded = changeRemaining.getAmount() / denomination.getDenomination().getAmount();
                denomination.setChangeAmount(denominationNeeded);
                changeRemaining = changeRemaining.minus(denomination.getDenomination().times(denominationNeeded));
            }
            paymentValid.accept(true);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    private void calculateChangeTotal() {
        Price total = new Price();
        denominations.forEach(denomination -> {
            total.addPrice(denomination.getChangeTotal());
        });
        changeTotal.setText(total.toString());
    }
}
