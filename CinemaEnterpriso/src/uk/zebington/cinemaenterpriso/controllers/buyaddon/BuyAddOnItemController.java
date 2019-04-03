package uk.zebington.cinemaenterpriso.controllers.buyaddon;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import uk.zebington.cinemaenterpriso.controllers.Controller;
import uk.zebington.cinemaenterpriso.controllers.viewbasket.ViewBasketItemController;
import uk.zebington.cinemaenterpriso.entities.AddOn;

public class BuyAddOnItemController extends Controller {
    @FXML
    public GridPane itemContainer;
    @FXML
    public Spinner<Integer> itemCount;

    private AddOn addOn;

    public BuyAddOnItemController(AddOn addOn) {
        super("buyaddon/buyAddOnItem");
        this.addOn = addOn;
        itemContainer.add(new ViewBasketItemController(addOn).getParent(), 0, 0);
    }

    public AddOn getAddOn() {
        return addOn;
    }

    public Integer getCount() {
        return itemCount.getValue();
    }
}
