package uk.zebington.cinemaenterpriso.entities.singletons;

import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.entities.Ticket;

import java.util.ArrayList;

public class TicketList extends ArrayList<Ticket> {
    private static TicketList ourInstance;

    public static TicketList getInstance() {
        if (ourInstance == null) {
            ourInstance = PersistenceManager.loadInstance("TicketList.ser");
            if (ourInstance == null) {
                ourInstance = new TicketList();
                PersistenceManager.writeInstance(ourInstance, "TicketList.ser");
            }
        }
        return ourInstance;
    }

    private TicketList() {}
}