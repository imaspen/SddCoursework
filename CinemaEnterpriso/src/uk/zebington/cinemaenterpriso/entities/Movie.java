package uk.zebington.cinemaenterpriso.entities;

import java.io.Serializable;

/**
 * @author Aspen Thompson
 */
public class Movie implements Serializable {
    private String title;
    private AgeRating ageRating;
    private String description;
    private String genre;

    public Movie(String title, AgeRating ageRating, String description, String genre) {
        this.title = title;
        this.ageRating = ageRating;
        this.description = description;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Movie)) return false;
        Movie movie = (Movie) obj;
        return title.equals(movie.getTitle()) && ageRating.equals(movie.getAgeRating())
                && description.equals(movie.getDescription()) && genre.equals(movie.getGenre());
    }
}
