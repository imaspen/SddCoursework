package uk.zebington.cinemaenterpriso.controllers.admin;

import uk.zebington.cinemaenterpriso.controllers.Controller;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @author Aspen Thompson
 */
public abstract class EditorController<T> extends Controller {
    private Consumer<Boolean> changesMadeConsumer;

    public EditorController(String fxmlName, Integer depth) {
        super(fxmlName, depth);
    }

    public void setChangesMadeConsumer(Consumer<Boolean> changesMadeConsumer) {
        this.changesMadeConsumer = changesMadeConsumer;
    }

    public abstract void updateFields(T obj);

    public void changeMade() {
        changesMadeConsumer.accept(true);
    }

    public abstract ArrayList<String> updateObject(T obj);
}
