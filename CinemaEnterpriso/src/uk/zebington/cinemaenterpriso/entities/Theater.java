package uk.zebington.cinemaenterpriso.entities;

/**
 * Created by u1862679 on 18/02/2019.
 */
public class Theater {
    private Movie showingMovie;
    private Integer seats;
    private Price price;

    public Theater(Movie showingMovie, Integer seats, Price price) {
        this.showingMovie = showingMovie;
        this.seats = seats;
        this.price = price;
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
            if (ticket.getTheater() == this) {
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
}
