package uk.zebington.cinemaenterpriso.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.entities.Purchasable;

import java.util.ArrayList;

public class ViewBasketController extends PageController {
    @FXML
    public VBox basketItems;

    private ArrayList<Purchasable> basket;

    public ViewBasketController(ArrayList<Purchasable> basket) {
        super("viewBasket");
        this.basket = new ArrayList<>();
        basket.forEach(this::addBasketItem);
    }

    private void addBasketItem(Purchasable purchasable) {
        basket.add(purchasable);
        basketItems.getChildren().add((new ViewBasketItemController(purchasable)).getParent());
    }

    @Override
    public String getTitle() {
        return "Basket";
    }
}
