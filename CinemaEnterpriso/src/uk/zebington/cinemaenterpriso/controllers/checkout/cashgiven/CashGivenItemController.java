package uk.zebington.cinemaenterpriso.controllers.checkout.cashgiven;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import uk.zebington.cinemaenterpriso.controllers.Controller;
import uk.zebington.cinemaenterpriso.entities.Price;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

/**
 * @author Aspen Thompson
 */
public class CashGivenItemController extends Controller {
    @FXML
    public Label denominationLabel;
    @FXML
    public Spinner<Integer> amountGiven;
    @FXML
    public Label changeAmount;

    private Price denomination;
    private CashGivenController parent;

    public CashGivenItemController(Price denomination, CashGivenController parent) {
        super("checkout/cashgiven/cashGivenItem", 3);
        this.denomination = denomination;
        this.parent = parent;
        denominationLabel.setText(denomination.toString());
        amountGiven.valueProperty().addListener(observable -> givenChanged());
    }

    private void givenChanged() {
        parent.updateTotal();
    }

    public Price getDenomination() {
        return denomination;
    }

    public Price getTotal() {
        try {
            return denomination.times(amountGiven.getValue());
        } catch (NegativePriceException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Price getChangeTotal() {
        try {
            return denomination.times(Integer.valueOf(changeAmount.getText()));
        } catch (NegativePriceException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setChangeAmount(Integer changeAmount) {
        this.changeAmount.setText(changeAmount.toString());
    }
}
