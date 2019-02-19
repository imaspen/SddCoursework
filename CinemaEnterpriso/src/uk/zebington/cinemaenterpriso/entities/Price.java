package uk.zebington.cinemaenterpriso.entities;

import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;

public class Price {
    private Integer amount;

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

    @Override
    public String toString() {
        return String.format("%01d.%02d £", amount / 100, amount % 100);
    }
}
