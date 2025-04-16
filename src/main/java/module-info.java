module rhysevans.basiccurrencyconvertergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens rhysevans.basiccurrencyconvertergui to javafx.fxml;
    exports rhysevans.basiccurrencyconvertergui;
}