package uk.zebington.cinemaenterpriso.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import uk.zebington.cinemaenterpriso.controllers.Controller;

import java.util.Collection;

/**
 * @author Aspen Thompson
 */
public abstract class AdminTabController<T> extends Controller {
    @FXML
    public Group editor;
    @FXML
    public Button addButton;
    @FXML
    public Button removeButton;
    @FXML
    public Button resetButton;
    @FXML
    public Button saveButton;
    @FXML
    public ListView<T> elements;

    private Boolean changesMade;
    private EditorController<T> editorController;

    public AdminTabController(String fxmlName, Integer depth, Collection<T> elements, EditorController<T> editorController) {
        super(fxmlName, depth);
        editorController.setChangesMadeConsumer(changeMade -> {
            if (changeMade) setChangesMade(true);
        });
        this.editorController = editorController;
        this.editor.getChildren().setAll(this.editorController.getParent());
        this.elements.getItems().setAll(elements);
        this.elements.getSelectionModel().selectedItemProperty().addListener(o -> resetChanges());
        this.elements.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.elements.getSelectionModel().selectFirst();
        if (!elements.isEmpty()) {
            resetChanges();
        }
    }

    public Boolean getChangesMade() {
        return changesMade;
    }

    public void setChangesMade(Boolean changesMade) {
        if (changesMade == this.changesMade) return;
        this.changesMade = changesMade;
        addButton.setDisable(changesMade);
        removeButton.setDisable(changesMade);
        resetButton.setDisable(!changesMade);
        saveButton.setDisable(!changesMade);
        elements.setDisable(changesMade);
        elements.refresh();
    }

    public EditorController<T> getEditorController() {
        return this.editorController;
    }

    @FXML
    public abstract void addElement();

    @FXML
    public abstract void removeElement();

    @FXML
    public void resetChanges() {
        editorController.updateFields(elements.getSelectionModel().getSelectedItem());
        setChangesMade(false);
    }

    @FXML
    public abstract void commitChanges();
}
