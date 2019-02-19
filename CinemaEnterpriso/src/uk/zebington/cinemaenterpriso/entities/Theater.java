package uk.zebington.cinemaenterpriso.entities;

import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

/**
 * Created by u1862679 on 18/02/2019.
 */
public class Theater {
    public static Theater SJG_38;
    public static Theater OA4_01;
    static {
        Theater sjg38 = null;
        Theater oa401 = null;
        try {
            sjg38 = new Theater(Movie.THE_MATRIX, 150, new Price(1000));
            oa401 = new Theater(Movie.PAUL_BLART_2, 200, new Price(50));
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
        SJG_38 = sjg38;
        OA4_01 = oa401;
    }


    private Movie showingMovie;
    private Integer seats;
    private Price price;

    public Theater(Movie showingMovie, Integer seats, Price price) {
        this.showingMovie = showingMovie;
        this.seats = seats;
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
        for (Ticket ticket : Ticket.SOLD_TICKETS) {
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
