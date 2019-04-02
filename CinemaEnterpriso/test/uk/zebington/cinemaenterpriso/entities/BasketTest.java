package uk.zebington.cinemaenterpriso.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {
    private Price price1;
    private Price price2;
    private Price price3;
    private Purchasable purchasable1;
    private Purchasable purchasable2;
    private Purchasable purchasable3;
    private Basket basket1;
    private Basket basket2;

    @BeforeEach
    void setUp() {
        try {
            price1 = new Price(100);
            price2 = new Price(200);
            price3 = new Price(300);
            purchasable1 = new AddOn("", price1);
            purchasable2 = new AddOn("", price2);
            purchasable3 = new AddOn("", price3);
            basket1 = new Basket() {
                {add(purchasable1);}
                {add(purchasable2);}
            };
            basket2 = new Basket() {
                {add(purchasable2);}
                {add(purchasable3);}
            };
        } catch (NegativePriceException e) {
            Assertions.fail("Unexpected NegativePriceException");
            e.printStackTrace();
        }
    }

    @Test
    void getTotalCost() {
        Assertions.assertEquals(300, basket1.getTotalCost().getAmount());
        Assertions.assertEquals(500, basket2.getTotalCost().getAmount());
    }
}