package uk.zebington.cinemaenterpriso.entities;

import java.io.Serializable;

/**
 * @author Aspen Thompson
 */
public class Movie implements Serializable {
    public transient static final Movie THE_MATRIX = new Movie("The Matrix", AgeRating.FIFTEEN, "Thomas A. Anderson is a man living two lives. By day he is an average computer programmer and by night a hacker known as Neo. Neo has always questioned his reality, but the truth is far beyond his imagination. Neo finds himself targeted by the police when he is contacted by Morpheus, a legendary computer hacker branded a terrorist by the government. Morpheus awakens Neo to the real world, a ravaged wasteland where most of humanity have been captured by a race of machines that live off of the humans' body heat and electrochemical energy and who imprison their minds within an artificial reality known as the Matrix. As a rebel against the machines, Neo must return to the Matrix and confront the agents: super-powerful computer programs devoted to snuffing out Neo and the entire human rebellion.", "Action");
    public transient static final Movie PAUL_BLART_2 = new Movie("Paul Blart: Mall Cop 2", AgeRating.TWELVE, "This movie is bad. Don't watch it.", "\"Comedy\"");
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
