import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorDeMonedas {

    private static final String API_KEY = "9e094db2e9ba867400811df0";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        // Menu loop
        do {
            System.out.println("************************************************");
            System.out.println(" ");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println(" ");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println(" ");
            System.out.println("7) Salir");
            System.out.println(" ");
            System.out.println("Elija una opción válida:");
            System.out.println(" ");
            System.out.println("************************************************");
            System.out.println(" ");

            option = scanner.nextInt();

            if (option >= 1 && option <= 6) {
                System.out.println(" ");
                System.out.println("Ingresar el valor que deseas convertir:");
                double amount = scanner.nextDouble();

                // Process conversion
                processConversion(option, amount);
            }
        } while (option != 7);

        System.out.println("Gracias por usar el Conversor de Moneda.");
        scanner.close();
    }

    private static void processConversion(int option, double amount) {
        try {
            // Create HTTP client
            HttpClient client = HttpClient.newHttpClient();

            // Create request to fetch currency rates
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            // Send request and get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON response using Gson
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // Get the conversion rates
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

            // Call the appropriate conversion method based on the user's choice
            switch (option) {
                case 1 -> convert(amount, "ARS", conversionRates);  // Dólar a Peso Argentino
                case 2 -> convertToUSD(amount, "ARS", conversionRates);  // Peso Argentino a Dólar
                case 3 -> convert(amount, "BRL", conversionRates);  // Dólar a Real Brasileño
                case 4 -> convertToUSD(amount, "BRL", conversionRates);  // Real Brasileño a Dólar
                case 5 -> convert(amount, "COP", conversionRates);  // Dólar a Peso Colombiano
                case 6 -> convertToUSD(amount, "COP", conversionRates);  // Peso Colombiano a Dólar
            }
        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }
    }

    // Conversion methods
    private static void convert(double amount, String currencyCode, JsonObject conversionRates) {
        double rate = conversionRates.get(currencyCode).getAsDouble();
        double convertedAmount = amount * rate;
        System.out.println(" ");
        System.out.printf("El valor de %.2f [USD] corresponde al valor final de =>>> %.2f [%s]%n", amount, convertedAmount, currencyCode);
        System.out.println(" ");
        System.out.println(" ");
    }

    private static void convertToUSD(double amount, String currencyCode, JsonObject conversionRates) {
        double rate = conversionRates.get(currencyCode).getAsDouble();
        double convertedAmount = amount / rate;
        System.out.println(" ");
        System.out.printf("El valor de %.2f [%s] corresponde al valor final de =>>> %.2f [USD]%n", amount, currencyCode, convertedAmount);
        System.out.println(" ");
        System.out.println(" ");
    }
}

