package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;
import uk.zebington.cinemaenterpriso.exceptions.PriceFormatException;

class PriceTest {
    private Price price0;
    private Price price180;
    private Price price108;
    private Price price10008;
    private Price price8;

    @BeforeEach
    void setUp() {
        try {
            price0 = new Price();
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
    void fromString() {
        try {
            Price price;
            price = Price.fromString("350");
            Assertions.assertEquals(35000, price.getAmount());
            price = Price.fromString("3.5");
            Assertions.assertEquals(305, price.getAmount());
            price = Price.fromString(".5");
            Assertions.assertEquals(5, price.getAmount());
            price = Price.fromString("£3");
            Assertions.assertEquals(300, price.getAmount());
            price = Price.fromString("£3.50");
            Assertions.assertEquals(350, price.getAmount());
            price = Price.fromString("£.50");
            Assertions.assertEquals(50, price.getAmount());
        } catch (NegativePriceException e) {
            Assertions.fail("Unexpected NegativePriceException");
            e.printStackTrace();
        } catch (PriceFormatException e) {
            Assertions.fail("Unexpected PriceFormatException");
            e.printStackTrace();
        }

        String[] negativePrices = {"-350", "-3.5", "£-3", "£-3.50"};
        for (String negativePrice : negativePrices) {
            try {
                Price.fromString(negativePrice);
                Assertions.fail("Expected NegativePriceException");
            } catch (NegativePriceException ignored) {
            } catch (PriceFormatException e) {
                Assertions.fail("Unexpected PriceFormatException, Expected NegativePriceException");
                e.printStackTrace();
            }
        }

        String[] malformedPrices = {"3..40", "££24", "2.49h"};
        for (String malformedPrice : malformedPrices) {
            try {
                Price.fromString(malformedPrice);
                Assertions.fail("Expected PriceFormatException");
            } catch (PriceFormatException ignored) {
            } catch (NegativePriceException e) {
                Assertions.fail("Unexpected NegativePriceException, Expected PriceFormatException");
                e.printStackTrace();
            }
        }
    }

    @Test
    void setAmount() {
        Price price = new Price();
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
    }

    @Test
    void addPrice() {
        price8.addPrice(price108);
        Assertions.assertEquals(116, price8.getAmount());
        price180.addPrice(price10008);
        Assertions.assertEquals(10188, price180.getAmount());
    }

    @Test
    void times() {
        try {
            Assertions.assertEquals(8 * 2, price8.times(2).getAmount());
            Assertions.assertEquals(108 * 3, price108.times(3).getAmount());
        } catch (NegativePriceException e) {
            Assertions.fail("Unexpected NegativePriceException");
            e.printStackTrace();
        }

        try {
            price108.times(-3);
            Assertions.fail("Expeced NegativePriceException");
        } catch (NegativePriceException ignored) {
        }
    }

    @Test
    void minus() {
        try {
            Assertions.assertEquals(0, price8.minus(price8).getAmount());
            Assertions.assertEquals(100, price108.minus(price8).getAmount());
        } catch (NegativePriceException e) {
            Assertions.fail("Unexpected NegativePriceException");
            e.printStackTrace();
        }

        try {
            price108.minus(price180);
            Assertions.fail("Expected NegativePriceException");
        } catch (NegativePriceException ignored) {
        }
    }

    @Test
    void asString() {
        Assertions.assertEquals("£0.00", price0.toString(), "Default behaviour");
        Assertions.assertEquals("£1.80", price180.toString(), "Default behaviour");
        Assertions.assertEquals("£1.08", price108.toString(), "Penny padding");
        Assertions.assertEquals("£100.08", price10008.toString(), "Larger number");
        Assertions.assertEquals("£0.08", price8.toString(), "Pound padding");
    }

    @Test
    void equals() {
        try {
            Assertions.assertEquals(price108, new Price(108));
            Assertions.assertEquals(price180, new Price(180));
        } catch (NegativePriceException e) {
            Assertions.fail("Unexpected NegativePriceException");
            e.printStackTrace();
        }
    }
}