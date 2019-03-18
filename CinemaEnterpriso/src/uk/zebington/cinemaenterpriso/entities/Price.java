package uk.zebington.cinemaenterpriso.entities;

import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

import java.io.Serializable;

public class Price implements Serializable {
    private Integer amount;

    public Price() {
        this.amount = 0;
    }

    public Price(Integer amount) throws NegativePriceException {
        if (amount < 0) throw new NegativePriceException();
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) throws NegativePriceException {
        if (amount < 0) throw new NegativePriceException();
        this.amount = amount;
    }

    public void addPrice(Price price) {
        try {
            this.setAmount(this.amount + price.amount);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("%01d.%02d £", amount / 100, amount % 100);
    }
}
