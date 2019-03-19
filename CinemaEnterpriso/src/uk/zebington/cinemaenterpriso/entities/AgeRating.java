package uk.zebington.cinemaenterpriso.entities;

public enum AgeRating {
    U           ("U"),
    PG          ("PG"),
    TWELVE_A    ("12A"),
    TWELVE      ("12"),
    FIFTEEN     ("15"),
    EIGHTEEN    ("18");

    private final String title;

    AgeRating(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public static AgeRating fromString(String string) {
        for (AgeRating ageRating : AgeRating.values())  {
            if (ageRating.toString().equals(string)) return ageRating;
        }
        return null;
    }
}
