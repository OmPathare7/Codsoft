package task4;

//bfb9473790b5c46bf2ba9004
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class currencyConverter {

    private static final String API_KEY = "bfb9473790b5c46bf2ba9004";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Available currencies: USD, EUR, GBP, JPY, AUD");
        System.out.print("Enter base currency: ");
        String baseCurrency = scanner.nextLine().toUpperCase();
        
        System.out.print("Enter target currency: ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
    
            double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
            if (exchangeRate != -1) {
              
                double convertedAmount = amount * exchangeRate;
                System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
            } else {
                System.out.println("Currency conversion failed. Please check the currencies entered.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String urlString = API_URL + baseCurrency;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder response = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        conn.disconnect();

        // Parse the JSON response
        return parseExchangeRate(response.toString(), targetCurrency);
    }

    private static double parseExchangeRate(String jsonResponse, String targetCurrency) {
        Map<String, Double> rates = new HashMap<>();

        // Basic JSON parsing (for simplicity)
        String[] parts = jsonResponse.split(",");
        for (String part : parts) {
            if (part.contains(targetCurrency)) {
                String[] keyValue = part.split(":");
                rates.put(keyValue[0].replaceAll("[\"{}]", "").trim(), Double.parseDouble(keyValue[1]));
            }
        }

        return rates.getOrDefault(targetCurrency, -1.0);
    }
}