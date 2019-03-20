package uk.zebington.cinemaenterpriso.controllers.buyaddon;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import uk.zebington.cinemaenterpriso.controllers.PageContainerController;
import uk.zebington.cinemaenterpriso.controllers.PageController;
import uk.zebington.cinemaenterpriso.entities.AddOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class BuyAddOnController extends PageController {
    @FXML
    public VBox itemsContainer;

    private ArrayList<BuyAddOnItemController> addOnControllers;
    private Consumer<Collection<AddOn>> consumer;

    public BuyAddOnController(Collection<AddOn> addOns, Consumer<Collection<AddOn>> consumer) {
        super("buyaddon/buyAddOn", 2);
        ObservableList<Node> items = itemsContainer.getChildren();
        addOnControllers = new ArrayList<>();
        for (AddOn addOn : addOns) {
            BuyAddOnItemController addOnController = new BuyAddOnItemController(addOn);
            addOnControllers.add(addOnController);
            items.add(addOnController.getParent());
        }
        this.consumer = consumer;
    }

    @Override
    public String getTitle() {
        return "Buy Add On";
    }

    @FXML
    public void cancel() {
        PageContainerController.getInstance().loadPreviousPage();
    }

    @FXML
    public void buyAddOns() {
        ArrayList<AddOn> addOns = new ArrayList<>();
        for (BuyAddOnItemController addOnController : addOnControllers) {
            for (int i = 0; i < addOnController.getCount(); i++) {
                addOns.add(addOnController.getAddOn());
            }
        }
        consumer.accept(addOns);
        PageContainerController.getInstance().loadPreviousPage();
    }
}
