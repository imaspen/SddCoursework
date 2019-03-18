package uk.zebington.cinemaenterpriso.entities;

import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import java.util.ArrayList;

public class TheaterList extends ArrayList<Theater> {
    private static TheaterList ourInstance = new TheaterList();

    public static TheaterList getInstance() {
        return ourInstance;
    }

    private TheaterList() {
        super();
        try {
            this.add(new Theater(Movie.THE_MATRIX, 150, new Price(1000)));
            this.add(new Theater(Movie.PAUL_BLART_2, 200, new Price(50)));
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }
}
