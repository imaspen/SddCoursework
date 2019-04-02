package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import static org.junit.jupiter.api.Assertions.*;

class AddOnTest {
    private Price price1;
    private Price price2;
    private AddOn addOn1;
    private AddOn addOn2;

    @BeforeEach
    void setUp() {
        try {
            price1 = new Price(100);
            price2 = new Price(300);
            addOn1 = new AddOn("Add On 1", price1);
            addOn2 = new AddOn("Add On 2", price2);
        } catch (NegativePriceException e) {
            Assertions.fail("Unexpected NegativePriceError");
            e.printStackTrace();
        }
    }

    @Test
    void getName() {
        Assertions.assertEquals("Add On 1", addOn1.getName());
        Assertions.assertEquals("Add On 2", addOn2.getName());
    }

    @Test
    void setName() {
        addOn1.setName("Test Add On 1");
        addOn2.setName("Test Add On 2");
        Assertions.assertEquals("Test Add On 1", addOn1.getName());
        Assertions.assertEquals("Test Add On 2", addOn2.getName());
    }

    @Test
    void getPrice() {
        Assertions.assertEquals(price1, addOn1.getPrice());
        Assertions.assertEquals(price2, addOn2.getPrice());
    }

    @Test
    void setPrice() {
        addOn1.setPrice(price2);
        addOn2.setPrice(price1);
        Assertions.assertEquals(price2, addOn1.getPrice());
        Assertions.assertEquals(price1, addOn2.getPrice());
    }

    @Test
    void asString() {
        Assertions.assertEquals("Add On 1", addOn1.toString());
        Assertions.assertEquals("Add On 2", addOn2.toString());
    }
}