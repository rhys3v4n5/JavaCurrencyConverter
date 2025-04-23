module rhysevans.basiccurrencyconvertergui {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires org.json;


    opens rhysevans.basiccurrencyconvertergui to javafx.fxml;
    exports rhysevans.basiccurrencyconvertergui;
}