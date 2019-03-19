package uk.zebington.cinemaenterpriso.entities;

import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;
import uk.zebington.cinemaenterpriso.exceptions.PriceFormatException;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return (NumberFormat.getCurrencyInstance()).format(amount / 100 + (double) (amount % 100) / 100);
    }

    public static Price fromString(String price) throws PriceFormatException, NegativePriceException {
        Pattern pricePattern = Pattern.compile("^(?:£)?(?<pounds>[0-9]+)?(?:\\.(?<pence>[0-9]{1,2}))?$");
        Matcher priceMatcher = pricePattern.matcher(price);
        if (priceMatcher.matches()) {
            return new Price((Integer.valueOf(priceMatcher.group("pounds")) * 100) + Integer.valueOf(priceMatcher.group("pence")));
        } else {
            throw new PriceFormatException();
        }
    }
}
