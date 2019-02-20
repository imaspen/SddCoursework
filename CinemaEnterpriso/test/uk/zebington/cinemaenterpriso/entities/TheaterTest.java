package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

class TheaterTest {
    private Theater theater1;
    private Theater theater2;

    @BeforeEach
    void setUp() {
        Movie movie1 = new Movie("A Film", AgeRating.FIFTEEN, "A description", "A genre");
        Movie movie2 = new Movie("Another Film", AgeRating.U, "Another description", "Another genre");
        try {
            Price price1 = new Price(100);
            Price price2 = new Price(500);
            theater1 = new Theater(movie1,100, price1);
            theater2 = new Theater(movie2, 75, price2);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTicketsAvailable() {
        for (int i = 0; i < 10; i++) {
            Ticket.SOLD_TICKETS.add(new Ticket(theater1));
        }
        for (int i = 0; i < 15; i++) {
            Ticket.SOLD_TICKETS.add(new Ticket(theater2));
        }
        Assertions.assertEquals(theater1.getSeats() - 10, theater1.getTicketsAvailable());
        Assertions.assertEquals(theater2.getSeats() - 15, theater2.getTicketsAvailable());
    }
}