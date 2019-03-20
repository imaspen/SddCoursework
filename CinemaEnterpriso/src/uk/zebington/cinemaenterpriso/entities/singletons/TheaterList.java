package uk.zebington.cinemaenterpriso.entities.singletons;

import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.entities.Movie;
import uk.zebington.cinemaenterpriso.entities.Price;
import uk.zebington.cinemaenterpriso.entities.Theater;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import java.util.ArrayList;

public class TheaterList extends ArrayList<Theater> {
    private static TheaterList ourInstance;

    private TheaterList() {
        super();
        try {
            this.add(new Theater("SJG/03", Movie.THE_MATRIX, 150, new Price(1000)));
            this.add(new Theater("BLG/11", Movie.PAUL_BLART_2, 200, new Price(50)));
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    public static TheaterList getInstance() {
        if (ourInstance == null) {
            loadInstance();
        }
        return ourInstance;
    }

    public static void loadInstance() {
        ourInstance = PersistenceManager.loadInstance("TheaterList.ser");
        if (ourInstance == null) {
            ourInstance = new TheaterList();
            PersistenceManager.writeInstance(ourInstance, "TheaterList.ser");
        }
    }
}
