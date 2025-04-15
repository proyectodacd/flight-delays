package com.duodinamico.controller.apiconsumer;

import com.duodinamico.controller.persistency.Coordinates;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class OpenWeatherMapProcessor {

    private final String apiKey;
    public OpenWeatherMapProcessor(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }


    public String weatherPetition(Coordinates coordinates, String time) {
        Connection.Response response;
        try {
            String endpoint = "https://history.openweathermap.org/data/2.5/history/city";
            Connection connection = Jsoup.connect(endpoint);
            connection.ignoreContentType(true);
            connection.data("lon", String.valueOf(coordinates.getLongitude()));
            connection.data("lat", String.valueOf(coordinates.getLatitude()));
            connection.data("type", "hour");
            connection.data("appid", getApiKey());
            connection.data("units", "metric");
            connection.data("start", time);
            connection.data("cnt", "1");

            response = connection.method(Connection.Method.GET).execute();
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
