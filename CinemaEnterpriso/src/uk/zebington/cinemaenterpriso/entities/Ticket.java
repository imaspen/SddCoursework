package uk.zebington.cinemaenterpriso.entities;

/**
 * Created by u1862679 on 18/02/2019.
 */
public class Ticket implements Purchasable {
    private Theater theater;

    public Ticket(Theater theater) {
        this.theater = theater;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    @Override
    public Price getPrice() {
        return theater.getPrice();
    }

    @Override
    public String getName() {
        return theater.getShowingMovie().getTitle() + " Ticket";
    }
}
