package uk.zebington.cinemaenterpriso.controllers.checkout;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import uk.zebington.cinemaenterpriso.controllers.Controller;

import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * @author Aspen Thompson
 */
public class CardDetailsController extends Controller {
    @FXML
    public TextField cardNumber;
    @FXML
    public TextField expiry;
    @FXML
    public TextField cvc;

    private Consumer<Boolean> paymentValid;

    private Boolean cardNumberValid;
    private Boolean expiryValid;
    private Boolean cvcValid;

    public CardDetailsController(Consumer<Boolean> paymentValid) {
        super("checkout/cardDetails", 2);
        this.paymentValid = paymentValid;
        cardNumberValid = false;
        expiryValid = false;
        cvcValid = false;
        cardNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            cardNumberValid = checkValid("^([0-9]{4}[\\- ]?){4}$", newValue);
            updateClass(cardNumber, cardNumberValid);
            updateValidity();
        });
        expiry.textProperty().addListener((observable, oldValue, newValue) -> {
            expiryValid = checkValid("^(0[1-9]|1[0-2])[\\/\\-\\. ]?(19|[2-9][0-9])$", newValue);
            updateClass(expiry, expiryValid);
            updateValidity();
        });
        cvc.textProperty().addListener((observable, oldValue, newValue) -> {
            cvcValid = checkValid("^[0-9]{3}$", newValue);
            updateClass(cvc, cvcValid);
            updateValidity();
        });
    }

    private Boolean checkValid(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(value).matches();
    }

    private void updateClass(TextField field, Boolean valid) {
        ObservableList<String> styleClasses = field.getStyleClass();
        if (!valid) {
            if (!styleClasses.contains("invalid")) {
                styleClasses.add("invalid");
            }
        } else {
            styleClasses.remove("invalid");
        }
    }

    private void updateValidity() {
        paymentValid.accept(cardNumberValid && expiryValid && cvcValid);
    }
}
