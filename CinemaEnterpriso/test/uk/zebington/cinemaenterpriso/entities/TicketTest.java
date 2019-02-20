package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

class TicketTest {
    private Movie movie1;
    private Movie movie2;
    private Price price1;
    private Price price2;
    private Ticket ticket1;
    private Ticket ticket2;

    @BeforeEach
    void setUp() {
        movie1 = new Movie("A Film", AgeRating.FIFTEEN, "A description", "A genre");
        movie2 = new Movie("Another Film", AgeRating.U, "Another description", "Another genre");
        try {
            price1 = new Price(100);
            price2 = new Price(500);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
        ticket1 = new Ticket(new Theater(movie1, 100, price1));
        ticket2 = new Ticket(new Theater(movie2, 75, price2));
    }

    @Test
    void getPrice() {
        Assertions.assertEquals(price1, ticket1.getPrice());
        Assertions.assertEquals(price2, ticket2.getPrice());
    }

    @Test
    void getName() {
        Assertions.assertEquals(movie1.getTitle() + " Ticket", ticket1.getName());
        Assertions.assertEquals(movie2.getTitle() + " Ticket", ticket2.getName());
    }
}