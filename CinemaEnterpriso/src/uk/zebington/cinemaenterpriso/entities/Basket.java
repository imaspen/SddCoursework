package uk.zebington.cinemaenterpriso.entities;

import java.util.ArrayList;

public class Basket extends ArrayList<Purchasable> {
    public Price getTotalCost() {
        Price price = new Price();
        this.forEach(purchasable -> price.addPrice(purchasable.getPrice()));
        return price;
    }
}
