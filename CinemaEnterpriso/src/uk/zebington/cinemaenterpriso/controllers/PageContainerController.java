package uk.zebington.cinemaenterpriso.controllers;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayDeque;
import java.util.Deque;

public class PageContainerController extends AbstractController {
    public static final PageContainerController MAIN_PAGE = new PageContainerController();

    @FXML
    public Button backButton;
    @FXML
    public Label pageTitle;
    @FXML
    public StackPane pageContent;
    @FXML
    public StackPane centeringPane;
    @FXML
    public ScrollPane scrollingPane;

    private Deque<AbstractController> history = new ArrayDeque<>();
    private AbstractController active;

    public PageContainerController() {
        super("pageContainer");
        centeringPane.minWidthProperty().bind(Bindings.createDoubleBinding(
                () -> scrollingPane.getViewportBounds().getWidth(), scrollingPane.viewportBoundsProperty())
        );
    }

    public void loadNewPage(AbstractController controller) {
        if (active != null) {
            backButton.setDisable(false);
            history.addFirst(active);
        }
        loadPage(controller);
    }

    @FXML
    public void loadPreviousPage(ActionEvent actionEvent) {
        if (!history.isEmpty()) {
            loadPage(history.removeFirst());
            if (history.isEmpty()) {
                backButton.setDisable(true);
            }
        }
    }

    private void loadPage(AbstractController controller) {
        pageContent.getChildren().clear();
        pageContent.getChildren().add(controller.getParent());
        if (controller instanceof HasTitle) {
            pageTitle.setText(((HasTitle) controller).getTitle());
        } else {
            pageTitle.setText("");
        }
        active = controller;
    }
}
