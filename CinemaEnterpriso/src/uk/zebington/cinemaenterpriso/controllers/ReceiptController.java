package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.controllers.theaterlist.TheaterListController;
import uk.zebington.cinemaenterpriso.controllers.viewbasket.ViewBasketItemController;
import uk.zebington.cinemaenterpriso.entities.Price;
import uk.zebington.cinemaenterpriso.entities.Purchasable;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class ReceiptController extends PageController {
    @FXML
    public Label totalPriceLabel;
    @FXML
    public VBox receiptItems;

    private ArrayList<Purchasable> basket;
    private Price totalPrice;

    public ReceiptController(ArrayList<Purchasable> basket) {
        super("receipt");
        this.basket = new ArrayList<>();
        try {
            this.totalPrice = new Price(0);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
        basket.forEach(this::addBasketItem);
    }

    private void addBasketItem(Purchasable purchasable) {
        basket.add(purchasable);
        totalPrice.addPrice(purchasable.getPrice());
        receiptItems.getChildren().add((new ViewBasketItemController(purchasable)).getParent());
        totalPriceLabel.setText(totalPrice.toString());
    }

    @FXML
    public void done() {
        PageContainerController.getInstance().loadNewPage(new TheaterListController());
        PageContainerController.getInstance().resetHistory();
    }

    @Override
    public String getTitle() {
        return "Receipt";
    }
}
