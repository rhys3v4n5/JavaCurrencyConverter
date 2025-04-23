package rhysevans.basiccurrencyconvertergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

/**
 * @author rhyse
 * @version 2.0
 * a class to control the GUI for the currency exchange
 * last updated: 23/04/2025
 */
public class Controller {

    private static final String OUTPUT_STRING = "The converted value is %.2f";
    private static final String INCORRECT_CURRENCY = "One of your inputted currencies doesn't exist, try again.";
    private static final String VALUE_ERROR = "The value must be positive!";

    // The original currency and the desired currency
    private String originalCurrency;
    private String desiredCurrency;

    // the original currency type
    @FXML
    private TextField originalCurrencyType;

    @FXML
    private TextField selectedCurrencyType;

    /*Drop down menu
    @FXML
    private ChoiceBox<String> currencyType;
     */

    // Original value input
    @FXML
    private TextField inputTextField;

    // Final result as text
    @FXML
    private Label resultLabel;

    @FXML

    ImageView poundImageView;
    Image poundImage = new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("PoundSterling.png")));

    @FXML
    public void displayImage() {
        poundImageView.setImage(poundImage);
    }

    @FXML
    private void getInput(ActionEvent event) {
        try {
            originalCurrency = originalCurrencyType.getText();
            desiredCurrency = selectedCurrencyType.getText();
            double originalAmount = Double.parseDouble(inputTextField.getText());

            // Code to check input
            if (originalAmount >= 0) {
                calculate(originalAmount);
            } else {
                resultLabel.setText(VALUE_ERROR);
            }
        } catch (NumberFormatException nfe) {
            System.out.println(nfe);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    @FXML
    private void calculate(double originalAmount) throws IOException {
        APIController controller = new APIController(originalCurrency, desiredCurrency);
        double convertedCurrency = controller.convert(originalAmount);

        String finalOutput = String.format(OUTPUT_STRING + desiredCurrency, convertedCurrency);
        // if it's a valid result, display the value.
        if (convertedCurrency >= 0) {
            resultLabel.setText(finalOutput);
        } else {
            // alert the client that their desired currency doesn't exist.
            resultLabel.setText(INCORRECT_CURRENCY);
        }
    }
}