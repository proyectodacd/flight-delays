package com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.activemq;

import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;
import com.google.gson.Gson;

public class WeatherEventDeserializer {
    private final Gson gson = new Gson();

    public WeatherEvent deserializeWeatherEvent(String json) {
        return gson.fromJson(json, WeatherEvent.class);
    }
}
