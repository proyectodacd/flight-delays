package com.duodinamico.controller.eventintegration;

import com.google.gson.Gson;

public class WeatherEventSerializer {

    private final Gson gson = new Gson();

    public String serializeWeatherEvent(WeatherEvent weatherEvent) {
        return gson.toJson(weatherEvent);
    }

}