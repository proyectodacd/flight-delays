package com.duodinamico.controller.eventintegration;

import com.google.gson.Gson;

public class WeatherEventDeserializer {
    private final Gson gson = new Gson();

    public WeatherEvent deserializeWeatherEvent(String json) {
        return gson.fromJson(json, WeatherEvent.class);
    }
}
