package com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer;

import com.duodinamico.openweathermapfeeder.domain.model.Coordinates;
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
        String endpoint = "https://history.openweathermap.org/data/2.5/history/city";
        Connection connection = Jsoup.connect(endpoint);
        connection.ignoreContentType(true);
        connection.data("lon", String.valueOf(coordinates.getLongitude()));
        connection.data("lat", String.valueOf(coordinates.getLatitude()));
        connection.data("start", time);
        return executePetition(connection).body();
    }

    public void adjustAdditionalParameters(Connection connection) {
        connection.data("type", "hour");
        connection.data("appid", this.apiKey);
        connection.data("units", "metric");
        connection.data("cnt", "24");
    }

    public Connection.Response executePetition(Connection connection) {
        try {
            adjustAdditionalParameters(connection);
            return connection.method(Connection.Method.GET).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
