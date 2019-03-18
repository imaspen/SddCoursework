package uk.zebington.cinemaenterpriso.entities;

import java.util.ArrayList;

public class TicketList extends ArrayList<Ticket> {
    private static TicketList ourInstance = new TicketList();

    public static TicketList getInstance() {
        return ourInstance;
    }

    private TicketList() {}
}
