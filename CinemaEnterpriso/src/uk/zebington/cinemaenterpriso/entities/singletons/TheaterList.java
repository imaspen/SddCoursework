package uk.zebington.cinemaenterpriso.entities.singletons;

import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.entities.AgeRating;
import uk.zebington.cinemaenterpriso.entities.Movie;
import uk.zebington.cinemaenterpriso.entities.Price;
import uk.zebington.cinemaenterpriso.entities.Theater;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import java.util.ArrayList;

public class TheaterList extends ArrayList<Theater> implements Persistable {
    private static TheaterList ourInstance;
    private static final String SAVE_PATH = "TheaterList.ser";

    private TheaterList() {
        super();
    }

    public static TheaterList getInstance() {
        if (ourInstance == null) {
            loadInstance();
        }
        return ourInstance;
    }

    public static void loadInstance() {
        ourInstance = (TheaterList) PersistenceManager.loadInstance(SAVE_PATH);
        if (ourInstance == null) {
            ourInstance = new TheaterList();
            PersistenceManager.writeInstance(ourInstance);
        }
    }

    @Override
    public String getSavePath() {
        return SAVE_PATH;
    }
}
