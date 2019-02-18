package uk.zebington.cinemaenterpriso.entities;

/**
 * Created by u1862679 on 18/02/2019.
 */
public class Theater {
    public static Theater SJG_38 = new Theater(Movie.THE_MATRIX, 150);
    public static Theater OA4_01 = new Theater(Movie.PAUL_BLART_2, 200);

    private Movie showingMovie;
    private Integer seats;

    public Theater(Movie showingMovie, Integer seats) {
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
}
