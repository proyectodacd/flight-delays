package com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.activemq;

import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;
import com.google.gson.Gson;

public class WeatherEventSerializer {

    private final Gson gson = new Gson();

    public String serializeWeatherEvent(WeatherEvent weatherEvent) {
        return gson.toJson(weatherEvent);
    }

}