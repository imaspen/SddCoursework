package uk.zebington.cinemaenterpriso.exceptions;

public class NegativePriceException extends Exception {
    public NegativePriceException() {
        super();
    }

    public NegativePriceException(String message) {
        super(message);
    }

    public NegativePriceException(String message, Throwable cause) {
        super(message, cause);
    }
}
