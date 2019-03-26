package uk.zebington.cinemaenterpriso.controllers.admin.addon;

import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.controllers.admin.AdminTabController;
import uk.zebington.cinemaenterpriso.entities.*;
import uk.zebington.cinemaenterpriso.entities.singletons.AddOnList;
import uk.zebington.cinemaenterpriso.entities.singletons.Persistable;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;
import uk.zebington.cinemaenterpriso.boundaries.modals.WarningModal;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class AdminAddOnTabController extends AdminTabController<AddOn> {
    private AddOnList addOns;

    public AdminAddOnTabController(AddOnList addOns) {
        super("admin/adminTab", 3, addOns, new AdminAddOnEditorController());
        this.addOns = addOns;
    }

    @Override
    public void addElement() {
        try {
            AddOn addOn = new AddOn("New Add On", new Price(100));
            addOns.add(addOn);
            PersistenceManager.writeInstance(addOns);
            elements.getItems().add(addOn);
            elements.getSelectionModel().select(addOn);
            setChangesMade(true);
            resetButton.setDisable(true);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeElement() {
        AddOn addOn = elements.getSelectionModel().getSelectedItem();
        addOns.remove(addOn);
        elements.getItems().remove(addOn);
        PersistenceManager.writeInstance(addOns);
    }

    @Override
    public void commitChanges() {
        AddOn addOn = elements.getSelectionModel().getSelectedItem();

        ArrayList<String> errors = this.getEditorController().updateObject(addOn);
        if (!errors.isEmpty()) {
            WarningModal warning = new WarningModal(
                    "Editor contains errors.",
                    "The following fields contain errors: " + String.join(", ", errors) + "."
            );
            warning.show();
            return;
        }

        PersistenceManager.writeInstance(addOns);
        setChangesMade(false);
    }
}
