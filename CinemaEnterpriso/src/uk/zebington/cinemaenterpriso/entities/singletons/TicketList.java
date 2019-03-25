package uk.zebington.cinemaenterpriso.entities.singletons;

import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.entities.Ticket;

import java.util.ArrayList;

public class TicketList extends ArrayList<Ticket> implements Persistable {
    private static TicketList ourInstance;
    private static final String SAVE_PATH = "TicketList.ser";

    public static TicketList getInstance() {
        if (ourInstance == null) {
            PersistenceManager.loadInstance(SAVE_PATH);
            if (ourInstance == null) {
                ourInstance = new TicketList();
                PersistenceManager.writeInstance(ourInstance);
            }
        }
        return ourInstance;
    }

    private TicketList() {
        super();
    }

    @Override
    public String getSavePath() {
        return SAVE_PATH;
    }
}
