package uk.zebington.cinemaenterpriso.controllers;

import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.entities.Purchasable;
import uk.zebington.cinemaenterpriso.entities.Ticket;

import java.util.ArrayList;

public class ViewBasketController extends PageController {
    public VBox basketItems;

    private ArrayList<Purchasable> basket;

    public ViewBasketController(Ticket ticket) {
        super("viewBasket");
        basket = new ArrayList<>();
        addBasketItem(ticket);
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
