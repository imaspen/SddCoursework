package uk.zebington.cinemaenterpriso.controllers.viewbasket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.controllers.PageContainerController;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.controllers.buyaddon.BuyAddOnController;
import uk.zebington.cinemaenterpriso.controllers.checkout.CheckoutController;
import uk.zebington.cinemaenterpriso.entities.AddOn;
import uk.zebington.cinemaenterpriso.entities.Basket;
import uk.zebington.cinemaenterpriso.entities.singletons.Catalogue;
import uk.zebington.cinemaenterpriso.entities.Price;
import uk.zebington.cinemaenterpriso.entities.Purchasable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class ViewBasketController extends PageController {
    @FXML
    public VBox basketItems;
    @FXML
    public Label totalPriceLabel;

    private Basket basket;

    public ViewBasketController(Basket basket) {
        super("viewbasket/viewBasket", 2);
        this.basket = new Basket();
        basket.forEach(this::addBasketItem);
    }

    private void addBasketItem(Purchasable purchasable) {
        basket.add(purchasable);
        basketItems.getChildren().add((new ViewBasketItemController(purchasable)).getParent());
        totalPriceLabel.setText(basket.getTotalCost().toString());
    }

    @FXML
    public void addDrink() {
        PageContainerController.getInstance().loadNewPage(
                new BuyAddOnController(Catalogue.getInstance().getDrinks(), new AddOnConsumer())
        );
    }

    @FXML
    public void addSnack() {
        PageContainerController.getInstance().loadNewPage(
                new BuyAddOnController(Catalogue.getInstance().getSnacks(), new AddOnConsumer())
        );
    }

    @FXML
    public void checkout() {
        PageContainerController.getInstance().loadNewPage(new CheckoutController(basket));
    }

    @Override
    public String getTitle() {
        return "Basket";
    }

    private class AddOnConsumer implements Consumer<Collection<AddOn>> {
        @Override
        public void accept(Collection<AddOn> addOns) {
            addOns.forEach(ViewBasketController.this::addBasketItem);
        }
    }
}
