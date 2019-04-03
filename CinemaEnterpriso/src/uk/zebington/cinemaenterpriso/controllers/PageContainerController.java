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

public class PageContainerController extends Controller {
    private static PageContainerController instance = new PageContainerController();

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

    private Deque<PageController> history = new ArrayDeque<>();
    private PageController active;

    private PageContainerController() {
        super("pageContainer");
        centeringPane.minWidthProperty().bind(Bindings.createDoubleBinding(
                () -> scrollingPane.getViewportBounds().getWidth(), scrollingPane.viewportBoundsProperty())
        );
    }

    public static PageContainerController getInstance() {
        return instance;
    }

    public void loadNewPage(PageController controller) {
        if (active != null) {
            backButton.setDisable(false);
            history.addFirst(active);
        }
        loadPage(controller);
        active.getParent().getStyleClass().add("page-content");
    }

    public void resetHistory() {
        history.clear();
        backButton.setDisable(true);
    }

    @FXML
    public void loadPreviousPage() {
        if (!history.isEmpty()) {
            if (active.beforeBack()) {
                loadPage(history.removeFirst());
                active.onBack();
                if (history.isEmpty()) {
                    backButton.setDisable(true);
                }
            }
        }
    }

    private void loadPage(PageController controller) {
        pageContent.getChildren().clear();
        pageContent.getChildren().add(controller.getParent());
        pageTitle.setText(controller.getTitle());
        active = controller;
    }
}
