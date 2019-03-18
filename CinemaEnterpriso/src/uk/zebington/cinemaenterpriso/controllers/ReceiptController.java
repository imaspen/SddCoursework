package uk.zebington.cinemaenterpriso.controllers;

import uk.zebington.cinemaenterpriso.entities.Purchasable;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class ReceiptController extends PageController {
    private ArrayList<Purchasable> basket;

    public ReceiptController(ArrayList<Purchasable> basket) {
        super("receipt");
        this.basket = basket;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
