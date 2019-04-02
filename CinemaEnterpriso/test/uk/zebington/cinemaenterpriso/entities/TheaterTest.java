package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.zebington.cinemaenterpriso.entities.singletons.TicketList;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

class TheaterTest {
    private Movie movie1;
    private Movie movie2;
    private Price price1;
    private Price price2;
    private Theater theater1;
    private Theater theater2;

    @BeforeEach
    void setUp() {
        movie1 = new Movie("A Film", AgeRating.FIFTEEN, "A description", "A genre");
        movie2 = new Movie("Another Film", AgeRating.U, "Another description", "Another genre");
        try {
            price1 = new Price(100);
            price2 = new Price(500);
            theater1 = new Theater("SJG/38", movie1, 100, price1);
            theater2 = new Theater("BLG/11", movie2, 75, price2);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getId() {
        Assertions.assertEquals("SJG/38", theater1.getId());
        Assertions.assertEquals("BLG/11", theater2.getId());
    }

    @Test
    void setId() {
        theater1.setId("BLG/11");
        theater2.setId("SJG/38");
        Assertions.assertEquals("BLG/11", theater1.getId());
        Assertions.assertEquals("SJG/38", theater2.getId());
    }

    @Test
    void getShowingMovie() {
        Assertions.assertEquals(movie1, theater1.getShowingMovie());
        Assertions.assertEquals(movie2, theater2.getShowingMovie());
    }

    @Test
    void setShowingMovie() {
        theater1.setShowingMovie(movie2);
        theater2.setShowingMovie(movie1);
        Assertions.assertEquals(movie2, theater1.getShowingMovie());
        Assertions.assertEquals(movie1, theater2.getShowingMovie());
    }

    @Test
    void getSeats() {
        Assertions.assertEquals(100, theater1.getSeats());
        Assertions.assertEquals(75, theater2.getSeats());
    }

    @Test
    void setSeats() {
        theater1.setSeats(75);
        theater2.setSeats(100);
        Assertions.assertEquals(75, theater1.getSeats());
        Assertions.assertEquals(100, theater2.getSeats());
    }

    @Test
    void getTicketsAvailable() {
        for (int i = 0; i < 10; i++) {
            TicketList.getInstance().add(new Ticket(theater1));
        }
        for (int i = 0; i < 15; i++) {
            TicketList.getInstance().add(new Ticket(theater2));
        }
        Assertions.assertEquals(theater1.getSeats() - 10, theater1.getTicketsAvailable());
        Assertions.assertEquals(theater2.getSeats() - 15, theater2.getTicketsAvailable());
    }

    @Test
    void getPrice() {
        Assertions.assertEquals(price1, theater1.getPrice());
        Assertions.assertEquals(price2, theater2.getPrice());
    }

    @Test
    void setPrice() {
        theater1.setPrice(price2);
        theater2.setPrice(price1);
        Assertions.assertEquals(price2, theater1.getPrice());
        Assertions.assertEquals(price1, theater2.getPrice());
    }

    @Test
    void equals() {
        Assertions.assertEquals(theater1, theater1);
        Assertions.assertEquals(theater2, theater2);
        Assertions.assertNotEquals(theater1, theater2);
        try {
            Theater theater1Clone = new Theater("SJG/38",
                    new Movie(
                            "A Film", AgeRating.FIFTEEN, "A description", "A genre"
                    ), 100, new Price(100)
            );
            Assertions.assertEquals(theater1, theater1Clone);
        } catch (NegativePriceException e) {
            Assertions.fail("Unexpected NegativePriceException");
            e.printStackTrace();
        }
    }

    @Test
    void asString() {
        Assertions.assertEquals("SJG/38 - A Film", theater1.toString());
        Assertions.assertEquals("BLG/11 - Another Film", theater2.toString());
    }
}