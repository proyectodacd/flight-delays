
package com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class AviationStackProcessor {

    private final String[] apiKeyList;
    private int currentKeyNumber;

    public AviationStackProcessor(String[] apiKeyList) {
        this.currentKeyNumber = 0;
        this.apiKeyList = apiKeyList;
    }

    public String[] getApiKeyList() {
        return apiKeyList;
    }

    public int getCurrentKeyNumber() {
        return currentKeyNumber;
    }

    public Connection.Response flightsPetition(String airportType, String airportIata) {
        Connection.Response response;
        try {
            String endpoint = "https://api.aviationstack.com/v1/flights";
            Connection connection = createConnection(endpoint, airportType, airportIata);
            response = connection.method(Connection.Method.GET).timeout(10000).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String petitionValidator(Connection.Response response, String airportType, String airportIata) {
        int maxKeys = this.apiKeyList.length;
        int attempts = 0;
        while (response == null || response.statusCode() != 200) {
            if (attempts >= maxKeys) {
                return "Error: No se pudo obtener una respuesta válida después de probar todas las claves.";
            }
            this.currentKeyNumber = (this.currentKeyNumber + 1) % maxKeys;
            response = flightsPetition(airportType, airportIata);
            attempts++;
        }
        return response.body();
    }

    public Connection createConnection(String endpoint, String airportType, String airportIata) {
        Connection connection = Jsoup.connect(endpoint);
        connection.ignoreContentType(true);
        connection.data("access_key", this.apiKeyList[this.currentKeyNumber]);
        connection.data("flight_status", "active");
        connection.data(airportType, airportIata);
        return connection;
    }

}

