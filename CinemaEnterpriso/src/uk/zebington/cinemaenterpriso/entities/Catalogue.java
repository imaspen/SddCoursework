package uk.zebington.cinemaenterpriso.entities;

import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import java.util.ArrayList;

public class Catalogue {
    private static Catalogue instance = new Catalogue();

    public static Catalogue getInstance() {
        return instance;
    }

    private ArrayList<AddOn> drinks;
    private ArrayList<AddOn> snacks;

    private Catalogue() {
        drinks = new ArrayList<>();
        snacks = new ArrayList<>();
        try {
            drinks.add(new AddOn("Pepsi", 112));
            drinks.add(new AddOn("Pepsi Max", 100));
            drinks.add(new AddOn("Coke", 112));
            drinks.add(new AddOn("Coke Zero", 100));
            drinks.add(new AddOn("Fanta Zero", 100));
            drinks.add(new AddOn("Water", 50));

            snacks.add(new AddOn("Twix", 75));
            snacks.add(new AddOn("M&M's", 150));
            snacks.add(new AddOn("Salted Popcorn", 200));
            snacks.add(new AddOn("Sweet Popcorn", 200));
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AddOn> getDrinks() {
        return drinks;
    }

    public ArrayList<AddOn> getSnacks() {
        return snacks;
    }
}
