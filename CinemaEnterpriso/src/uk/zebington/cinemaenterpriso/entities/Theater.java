package uk.zebington.cinemaenterpriso.entities;

import java.io.Serializable;

/**
 * Created by u1862679 on 18/02/2019.
 */
public class Theater implements Serializable {
    private String id;
    private Movie showingMovie;
    private Integer seats;
    private Price price;

    public Theater(String id, Movie showingMovie, Integer seats, Price price) {
        this.id = id;
        this.showingMovie = showingMovie;
        this.seats = seats;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movie getShowingMovie() {
        return showingMovie;
    }

    public void setShowingMovie(Movie showingMovie) {
        this.showingMovie = showingMovie;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getTicketsAvailable() {
        int sold = 0;
        for (Ticket ticket : TicketList.getInstance()) {
            if (ticket.getTheater().equals(this)) {
                sold++;
            }
        }
        return this.getSeats() - sold;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Theater)) return false;
        return id.equals(((Theater)obj).id);
    }
}
