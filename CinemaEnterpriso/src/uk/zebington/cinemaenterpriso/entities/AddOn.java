package uk.zebington.cinemaenterpriso.entities;

public abstract class AddOn implements Purchasable {
    private String name;
    private Price price;

    public AddOn(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
