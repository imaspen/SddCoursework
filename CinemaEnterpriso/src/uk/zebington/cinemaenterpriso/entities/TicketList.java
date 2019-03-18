package uk.zebington.cinemaenterpriso.entities;

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
