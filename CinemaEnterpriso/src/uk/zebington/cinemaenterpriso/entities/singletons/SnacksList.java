package uk.zebington.cinemaenterpriso.entities.singletons;

import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.entities.AddOn;

import java.util.ArrayList;

public class SnacksList extends AddOnList {
    private static final String SAVE_PATH = "SnacksList.ser";
    private static SnacksList ourInstance;

    private SnacksList() {
        super();
    }

    public static SnacksList getInstance() {
        if (ourInstance == null) {
            loadInstance();
        }
        return ourInstance;
    }

    public static void loadInstance() {
        ourInstance = (SnacksList) PersistenceManager.loadInstance(SAVE_PATH);
        if (ourInstance == null) {
            ourInstance = new SnacksList();
            PersistenceManager.writeInstance(ourInstance);
        }
    }

    @Override
    public String getSavePath() {
        return SAVE_PATH;
    }
}
