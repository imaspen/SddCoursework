package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgeRatingTest {

    @Test
    void asString() {
        Assertions.assertEquals(AgeRating.U.toString(), "U");
        Assertions.assertEquals(AgeRating.PG.toString(), "PG");
        Assertions.assertEquals(AgeRating.TWELVE_A.toString(), "12A");
        Assertions.assertEquals(AgeRating.TWELVE.toString(), "12");
        Assertions.assertEquals(AgeRating.FIFTEEN.toString(), "15");
        Assertions.assertEquals(AgeRating.EIGHTEEN.toString(), "18");
    }
}