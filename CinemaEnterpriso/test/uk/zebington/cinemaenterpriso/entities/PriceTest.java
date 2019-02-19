package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

class PriceTest {
    private Price price180;
    private Price price108;
    private Price price10008;
    private Price price8;

    @BeforeEach
    void setUp() {
        try {
            price180 = new Price(180);
            price108 = new Price(108);
            price10008 = new Price(10008);
            price8 = new Price(8);
        } catch (NegativePriceException e) {
            Assertions.fail("Unexpected NegativePriceException");
            e.printStackTrace();
        }
    }

    @Test
    void getAmountAsString() {
        Assertions.assertEquals("1.80 £", price180.getAmountAsString(), "Default behaviour");
        Assertions.assertEquals("1.08 £", price108.getAmountAsString(), "Penny padding");
        Assertions.assertEquals("100.08 £", price10008.getAmountAsString(), "Larger number");
        Assertions.assertEquals("0.08 £", price8.getAmountAsString(), "Pound padding");
    }

    @Test
    void getAmount() {
        Assertions.assertEquals(180, price180.getAmount());
        Assertions.assertEquals(108, price108.getAmount());
        Assertions.assertEquals(10008, price10008.getAmount());
        Assertions.assertEquals(8, price8.getAmount());
    }

    @Test
    void setAmount() {
        try {
            Price price = new Price(100);
            try {
                price.setAmount(10000);
                Assertions.assertEquals(10000, price.getAmount());
            } catch (NegativePriceException e) {
                Assertions.fail("Unexpected NegativePriceException");
            }
            try {
                price.setAmount(-1000);
                Assertions.fail("Expected a NegativePriceException");
            } catch (NegativePriceException e) {
                Assertions.assertEquals(10000, price.getAmount());
            }
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }
}