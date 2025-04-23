package rhysevans.basiccurrencyconvertergui;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;

public class APIController {
    private static String baseCurrency;
    private static String desiredCurrency;
    private static final String BASE_URL = "https://api.frankfurter.dev/v1/latest?base=%s&symbols=%s";

    public APIController(String base, String desired) {
        baseCurrency = base;
        desiredCurrency = desired;
        System.out.println("baseCurrency: " + baseCurrency);
        System.out.println("desiredCurrency: " + desiredCurrency);
    }

    public double convert(double originalAmount) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = String.format(BASE_URL, baseCurrency, desiredCurrency);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject ratesObject = jsonObject.getJSONObject("rates");
        Double rate = ratesObject.getDouble(desiredCurrency.toUpperCase());
        return originalAmount * rate;
    }
}
