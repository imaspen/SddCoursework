package uk.zebington.cinemaenterpriso.controllers.admin.addon;

import uk.zebington.cinemaenterpriso.controllers.admin.AdminTabController;
import uk.zebington.cinemaenterpriso.entities.*;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;
import uk.zebington.cinemaenterpriso.modals.WarningModal;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class AdminAddOnTabController extends AdminTabController<AddOn> {
    private ArrayList<AddOn> addOns;

    public AdminAddOnTabController(ArrayList<AddOn> addOns) {
        super("admin/adminTab", 3, addOns, new AdminAddOnEditorController());
        this.addOns = addOns;
    }

    @Override
    public void addElement() {
        try {
            AddOn addOn = new AddOn("New Add On", new Price(100));
            addOns.add(addOn);
//            PersistenceManager.writeInstance(TheaterList.getInstance(), "TheaterList.ser");
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
//        PersistenceManager.writeInstance(TheaterList.getInstance(), "TheaterList.ser");
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

//        PersistenceManager.writeInstance(TicketList.getInstance(), "TicketList.ser");
        setChangesMade(false);
    }
}
