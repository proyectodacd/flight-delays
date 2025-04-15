package com.duodinamico.controller.apiconsumer;

import com.duodinamico.controller.persistency.Coordinates;
import com.duodinamico.model.WeatherResult;

import java.util.List;

public class OpenWeatherMapProvider implements WeatherProvider{

    private final String apiKey;
    public OpenWeatherMapProvider(String apiKey) {
        this.apiKey = apiKey;
    }
    public String getApiKey() {
        return apiKey;
    }

    @Override
    public WeatherResult weatherProvider(Coordinates coordinates, String time) {
        OpenWeatherMapProcessor openWeatherMapProcessor = new OpenWeatherMapProcessor(getApiKey());
        WeatherDeserializer weatherDeserializer = new WeatherDeserializer();
        return weatherDeserializer.weatherDeserializer(openWeatherMapProcessor.weatherPetition(coordinates, time));
    }

}
