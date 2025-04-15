
package com.duodinamico.controller.apiconsumer;
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

    public void setCurrentKeyNumber(int currentKeyNumber) {
        this.currentKeyNumber = currentKeyNumber;
    }

    public Connection.Response flightsPetition() {
        Connection.Response response;
        try {
            String endpoint = "https://api.aviationstack.com/v1/flights";
            Connection connection = Jsoup.connect(endpoint);
            connection.ignoreContentType(true);
            connection.data("access_key", getApiKeyList()[getCurrentKeyNumber()]);
            connection.data("flight_status", "active");

            response = connection.method(Connection.Method.GET).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String petitionValidator(Connection.Response response) {
        int maxKeys = getApiKeyList().length;
        int attempts = 0;

        while (response == null || response.statusCode() != 200) {
            if (attempts >= maxKeys) {
                return "Error: No se pudo obtener una respuesta válida después de probar todas las claves.";
            }

            setCurrentKeyNumber((getCurrentKeyNumber() + 1) % maxKeys);

            response = flightsPetition();

            attempts++;
        }

        return response.body();
    }

}

