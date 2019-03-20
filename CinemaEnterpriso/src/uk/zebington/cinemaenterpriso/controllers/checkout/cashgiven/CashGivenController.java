package uk.zebington.cinemaenterpriso.controllers.checkout.cashgiven;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.controllers.Controller;
import uk.zebington.cinemaenterpriso.entities.Price;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class CashGivenController extends Controller {
    @FXML
    public VBox denominationsContainer;

    private ArrayList<CashGivenItemController> denominations;

    public CashGivenController() {
        super("checkout/cashgiven/cashGiven", 3);
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
            denoms.forEach(denom -> denominations.add(new CashGivenItemController(denom)));
            denominations.forEach(denomination -> denominationsContainer.getChildren().add(denomination.getParent()));
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }
}
