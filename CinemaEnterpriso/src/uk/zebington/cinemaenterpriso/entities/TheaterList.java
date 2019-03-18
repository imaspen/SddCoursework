package uk.zebington.cinemaenterpriso.entities;

import java.util.ArrayList;

public class TheaterList extends ArrayList<Theater> {
    private static TheaterList ourInstance;

    private TheaterList() {
        super();
//        try {
//            this.add(new Theater(Movie.THE_MATRIX, 150, new Price(1000)));
//            this.add(new Theater(Movie.PAUL_BLART_2, 200, new Price(50)));
//        } catch (NegativePriceException e) {
//            e.printStackTrace();
//        }
    }

    public static TheaterList getInstance() {
        if (ourInstance == null) {
            ourInstance = PersistenceManager.loadInstance("TheaterList.ser");
            if (ourInstance == null) {
                ourInstance = new TheaterList();
                PersistenceManager.writeInstance(ourInstance, "TheaterList.ser");
            }
        }
        return ourInstance;
    }
}
