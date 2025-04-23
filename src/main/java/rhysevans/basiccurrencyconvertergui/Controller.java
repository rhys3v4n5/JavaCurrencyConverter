package rhysevans.basiccurrencyconvertergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

/**
 * @author rhyse
 * @version 1.0
 * a class to control the GUI for the currency exchange
 * last updated: 26/07/2024
 * TODO MAKE IT POSSIBLE TO CONVERT FROM MULTIPLE CURRENCIES NOT JUST GBP
 */
public class Controller {

    // Pre-determined options & selected currency variable
    private final String[] currencyOptions = {"EUR", "USD", "AUD"};
    private String selectedCurrency;
    private String originalCurrency;

    @FXML
    private TextField originalCurrencyType;
    // Drop down menu
    @FXML
    private ChoiceBox<String> currencyType;

    // Original number input
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
    private void initialize(){
        currencyType.getItems().addAll(currencyOptions);
        currencyType.setOnAction(this::getSelectedCurrency);
    }

    public void getSelectedCurrency(ActionEvent actionEvent) {
        selectedCurrency = currencyType.getValue();
        System.out.println(selectedCurrency);
    }

    @FXML
    private void getInput(ActionEvent event) {
        try {

            originalCurrency = originalCurrencyType.getText();
            System.out.println(originalCurrency);
            double originalAmount = Double.parseDouble(inputTextField.getText());
            System.out.println(originalAmount);
            // Code to check input
            if (originalAmount >= 0) {
                calculate(originalAmount);
            } else {
                resultLabel.setText("The value must be positive");
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void calculate(double originalAmount) throws IOException {
       //  double convertedCurrency = CurrencyExchange.getConvertedCurrency(selectedCurrency, originalAmount);

        APIController controller = new APIController(originalCurrency, selectedCurrency);
        double convertedCurrency = controller.convert(originalAmount);

        String finalOutput = String.format("The converted value is %.2f" + selectedCurrency, convertedCurrency);
        resultLabel.setText(finalOutput);
    }
}