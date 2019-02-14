package uk.zebington.cinemaenterpriso.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayDeque;
import java.util.Deque;

public class PageContainerController extends AbstractController {
    public static final PageContainerController MAIN_PAGE = new PageContainerController();

    public BorderPane pageContainer;
    public Button backButton;
    public Label pageTitle;
    public StackPane pageContent;

    private Deque<AbstractController> history = new ArrayDeque<>();
    private AbstractController active;

    public PageContainerController() {
        super("pageContainer");
    }

    public void loadNewPage(AbstractController controller) {
        if (active != null) {
            backButton.setDisable(false);
            history.addFirst(active);
        }
        loadPage(controller);
    }

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
        pageContent.getChildren().add(controller.getPage());
        if (controller instanceof HasTitle) {
            pageTitle.setText(((HasTitle) controller).getTitle());
        } else {
            pageTitle.setText("");
        }
        active = controller;
    }
}
