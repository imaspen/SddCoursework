package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        movie1 = new Movie("Movie 1", AgeRating.U, "Description 1", "Genre 1");
        movie2 = new Movie("Movie 2", AgeRating.PG, "Description 2", "Genre 2");
    }

    @Test
    void getTitle() {
        Assertions.assertEquals("Movie 1", movie1.getTitle());
        Assertions.assertEquals("Movie 2", movie2.getTitle());
    }

    @Test
    void setTitle() {
        movie1.setTitle("Title 1");
        movie2.setTitle("Title 2");
        Assertions.assertEquals("Title 1", movie1.getTitle());
        Assertions.assertEquals("Title 2", movie2.getTitle());
    }

    @Test
    void getAgeRating() {
        Assertions.assertEquals(AgeRating.U, movie1.getAgeRating());
        Assertions.assertEquals(AgeRating.PG, movie2.getAgeRating());
    }

    @Test
    void setAgeRating() {
        movie1.setAgeRating(AgeRating.FIFTEEN);
        movie2.setAgeRating(AgeRating.EIGHTEEN);
        Assertions.assertEquals(AgeRating.FIFTEEN, movie1.getAgeRating());
        Assertions.assertEquals(AgeRating.EIGHTEEN, movie2.getAgeRating());
    }

    @Test
    void getDescription() {
        Assertions.assertEquals("Description 1", movie1.getDescription());
        Assertions.assertEquals("Description 2", movie2.getDescription());
    }

    @Test
    void setDescription() {
        movie1.setDescription("A Description 1");
        movie2.setDescription("A Description 2");
        Assertions.assertEquals("A Description 1", movie1.getDescription());
        Assertions.assertEquals("A Description 2", movie2.getDescription());
    }

    @Test
    void getGenre() {
        Assertions.assertEquals("Genre 1", movie1.getGenre());
        Assertions.assertEquals("Genre 2", movie2.getGenre());
    }

    @Test
    void setGenre() {
        movie1.setGenre("A Genre 1");
        movie2.setGenre("A Genre 2");
        Assertions.assertEquals("A Genre 1", movie1.getGenre());
        Assertions.assertEquals("A Genre 2", movie2.getGenre());
    }

    @Test
    void equals() {
        Assertions.assertEquals(movie1, movie1);
        Assertions.assertEquals(new Movie("Movie 1", AgeRating.U, "Description 1", "Genre 1"), movie1);
        Assertions.assertEquals(movie2, movie2);
        Assertions.assertNotEquals(movie1, movie2);
    }
}