package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void asString() {
        Assertions.assertEquals("1.80 £", price180.toString(), "Default behaviour");
        Assertions.assertEquals("1.08 £", price108.toString(), "Penny padding");
        Assertions.assertEquals("100.08 £", price10008.toString(), "Larger number");
        Assertions.assertEquals("0.08 £", price8.toString(), "Pound padding");
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

    @Test
    void addPrice() {
        price8.addPrice(price108);
        Assertions.assertEquals(116, price8.getAmount());
        price180.addPrice(price10008);
        Assertions.assertEquals(10188, price180.getAmount());
    }
}