package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.entities.AddOn;
import uk.zebington.cinemaenterpriso.entities.Catalogue;
import uk.zebington.cinemaenterpriso.entities.Price;
import uk.zebington.cinemaenterpriso.entities.Purchasable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class ViewBasketController extends PageController {
    @FXML
    public VBox basketItems;
    @FXML
    public Label totalPriceLabel;

    private ArrayList<Purchasable> basket;
    private Price totalPrice;

    public ViewBasketController(ArrayList<Purchasable> basket) {
        super("viewBasket");
        this.basket = new ArrayList<>();
        this.totalPrice = new Price();
        basket.forEach(this::addBasketItem);
    }

    private void addBasketItem(Purchasable purchasable) {
        basket.add(purchasable);
        totalPrice.addPrice(purchasable.getPrice());
        basketItems.getChildren().add((new ViewBasketItemController(purchasable)).getParent());
        totalPriceLabel.setText(totalPrice.toString());
    }

    @FXML
    public void addDrink() {
        PageContainerController.getInstance().loadNewPage(
                new BuyAddOnController(Catalogue.getInstance().getDrinks(), new AddOnConsumer())
        );
    }

    @FXML
    public void addSnack() {
        PageContainerController.getInstance().loadNewPage(
                new BuyAddOnController(Catalogue.getInstance().getSnacks(), new AddOnConsumer())
        );
    }

    @FXML
    public void checkout() {
        PageContainerController.getInstance().loadNewPage(new CheckoutController());
    }

    @Override
    public String getTitle() {
        return "Basket";
    }

    private class AddOnConsumer implements Consumer<Collection<AddOn>> {
        @Override
        public void accept(Collection<AddOn> addOns) {
            addOns.forEach(ViewBasketController.this::addBasketItem);
        }
    }
}
