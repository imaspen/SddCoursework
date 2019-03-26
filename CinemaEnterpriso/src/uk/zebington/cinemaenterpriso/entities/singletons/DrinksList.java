package uk.zebington.cinemaenterpriso.entities.singletons;

import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.entities.AddOn;

import java.util.ArrayList;

public class DrinksList extends AddOnList {
    private static final String SAVE_PATH = "DrinksList.ser";
    private static DrinksList ourInstance;

    private DrinksList() {
        super();
    }

    public static DrinksList getInstance() {
        if (ourInstance == null) {
            loadInstance();
        }
        return ourInstance;
    }

    public static void loadInstance() {
        ourInstance = (DrinksList) PersistenceManager.loadInstance(SAVE_PATH);
        if (ourInstance == null) {
            ourInstance = new DrinksList();
            PersistenceManager.writeInstance(ourInstance);
        }
    }

    @Override
    public String getSavePath() {
        return SAVE_PATH;
    }
}
