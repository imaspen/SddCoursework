package uk.zebington.cinemaenterpriso.controllers.admin.theater;

import uk.zebington.cinemaenterpriso.PersistenceManager;
import uk.zebington.cinemaenterpriso.controllers.admin.AdminTabController;
import uk.zebington.cinemaenterpriso.entities.*;
import uk.zebington.cinemaenterpriso.entities.singletons.TheaterList;
import uk.zebington.cinemaenterpriso.entities.singletons.TicketList;
import uk.zebington.cinemaenterpriso.exceptions.NegativePriceException;
import uk.zebington.cinemaenterpriso.boundaries.modals.WarningModal;

import java.util.ArrayList;

/**
 * @author Aspen Thompson
 */
public class AdminTheaterTabController extends AdminTabController<Theater> {
    public AdminTheaterTabController() {
        super("admin/adminTab", 3, TheaterList.getInstance(), new AdminTheaterEditorController());
    }

    @Override
    public void addElement() {
        try {
            Theater theater = new Theater(
                    "New Theater",
                    new Movie("New Movie", AgeRating.U, "", ""),
                    200,
                    new Price(0)
            );
            TheaterList.getInstance().add(theater);
            PersistenceManager.writeInstance(TheaterList.getInstance(), "TheaterList.ser");
            elements.getItems().add(theater);
            elements.getSelectionModel().select(theater);
            setChangesMade(true);
            resetButton.setDisable(true);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeElement() {
        Theater theater = elements.getSelectionModel().getSelectedItem();
        TheaterList.getInstance().remove(theater);
        elements.getItems().remove(theater);
        PersistenceManager.writeInstance(TheaterList.getInstance(), "TheaterList.ser");
    }

    @Override
    public void commitChanges() {
        Theater theater = elements.getSelectionModel().getSelectedItem();
        ArrayList<Ticket> toUpdate = new ArrayList<>();
        for (Ticket ticket : TicketList.getInstance()) {
            if (ticket.getTheater().equals(theater)) toUpdate.add(ticket);
        }

        ArrayList<String> errors = this.getEditorController().updateObject(theater);
        if (!errors.isEmpty()) {
            WarningModal warning = new WarningModal(
                    "Editor contains errors.",
                    "The following fields contain errors: " + String.join(", ", errors) + "."
            );
            warning.show();
            return;
        }

        PersistenceManager.writeInstance(TheaterList.getInstance(), "TheaterList.ser");
        toUpdate.forEach(ticket -> ticket.setTheater(theater));
        PersistenceManager.writeInstance(TicketList.getInstance(), "TicketList.ser");
        setChangesMade(false);
    }
}
