package rhysevans.basiccurrencyconvertergui;

/**
 * @author rhyse
 * @version 1.1
 * purpose: a class to calculate the exchange
 * version history: 1.0 - basic functions, 1.1 - changed to be paramaterised for GUI
 * last updated: 26/07/2024
 * TODO USE CURRENCY CONVERTER API TO HAVE WIDER CONVERSIONS TO ALMOST EVERY CURRENCY
 */
public class CurrencyExchange {
    // The current exchange rates as of 25/07/2024
    private static final double USD_RATE = 0.77604;
    private static final double AUD_RATE = 0.50882;
    private static final double EUR_RATE = 0.84292;

    /**
     * Method to convert the money.
     * @param currencyType the currency selected on the dropdown.
     * @param originalAmount the original amount of money inputted.
     * @return the converted amount of money.
     */
    public static double getConvertedCurrency(String currencyType, double originalAmount) {
        // Goes through, chooses exchange rate and calculates
        return switch (currencyType) {
            case "EUR" -> originalAmount / EUR_RATE;
            case "USD" -> originalAmount / USD_RATE;
            case "AUD" -> originalAmount / AUD_RATE;
            default -> 0.0;
        };
    }
}
