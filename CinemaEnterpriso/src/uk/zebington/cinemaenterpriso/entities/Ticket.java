package uk.zebington.cinemaenterpriso.entities;

import java.util.ArrayList;

/**
 * Created by u1862679 on 18/02/2019.
 */
public class Ticket {
    public static final ArrayList<Ticket> SOLD_TICKETS = new ArrayList<Ticket>() {
        {
            add(new Ticket(Theater.OA4_01));
            add(new Ticket(Theater.OA4_01));
            add(new Ticket(Theater.OA4_01));
            add(new Ticket(Theater.OA4_01));
            add(new Ticket(Theater.OA4_01));
            add(new Ticket(Theater.OA4_01));
            add(new Ticket(Theater.SJG_38));
            add(new Ticket(Theater.SJG_38));
            add(new Ticket(Theater.SJG_38));
            add(new Ticket(Theater.SJG_38));
            add(new Ticket(Theater.SJG_38));
        }
    };

    private Theater theater;

    public Ticket() {
        this.theater = Theater.OA4_01;
    }

    public Ticket(Theater theater) {
        this.theater = theater;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
