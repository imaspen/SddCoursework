package uk.zebington.cinemaenterpriso.controllers.admin.addon;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uk.zebington.cinemaenterpriso.controllers.admin.EditorController;
import uk.zebington.cinemaenterpriso.entities.*;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;
import uk.zebington.cinemaenterpriso.exceptions.PriceFormatException;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class AdminAddOnEditorController extends EditorController<AddOn> {
    @FXML
    public TextField addOnName;
    @FXML
    public TextField addOnPrice;

    AdminAddOnEditorController() {
        super("admin/adminAddOnEditor");

        this.addOnName.textProperty().addListener(o -> changeMade());
        this.addOnPrice.textProperty().addListener(o -> changeMade());
    }

    @Override
    public void updateFields(AddOn addOn) {
        addOnName.setText(addOn.getName());
        addOnPrice.setText(addOn.getPrice().toString());
    }

    @Override
    public ArrayList<String> updateObject(AddOn addOn) {
        ArrayList<String> erroredFields = new ArrayList<>();

        String name = addOnName.getText();
        if (name.length() == 0) {
            erroredFields.add("Name");
        }
        Price price = null;
        try {
            price = Price.fromString(addOnPrice.getText());
        } catch (NegativePriceException | PriceFormatException e) {
            erroredFields.add("Price");
        }

        if (erroredFields.isEmpty()) {
            addOn.setName(name);
            addOn.setPrice(price);
        }
        return erroredFields;
    }
}
