package com.duodinamico.controller.apiconsumer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class OpenWeatherMapProcessor {

    public String weatherPetition(String latitude, String longitude, String time, String[] args) {
        Connection.Response response;
        try {
            String endpoint = "https://history.openweathermap.org/data/2.5/history/city";
            Connection connection = Jsoup.connect(endpoint);
            connection.ignoreContentType(true);
            connection.data("lon", longitude);
            connection.data("lat", latitude);
            connection.data("type", "hour");
            connection.data("appid", args[1]);
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
