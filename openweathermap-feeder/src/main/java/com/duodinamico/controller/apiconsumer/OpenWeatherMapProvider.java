package com.duodinamico.controller.apiconsumer;

import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.persistency.Coordinates;
import com.duodinamico.controller.model.WeatherResult;

public class OpenWeatherMapProvider implements WeatherProvider{

    private final String apiKey;
    public OpenWeatherMapProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public WeatherResponse weatherProvider(Coordinates coordinates, String time) {
        OpenWeatherMapProcessor openWeatherMapProcessor = new OpenWeatherMapProcessor(this.apiKey);
        WeatherDeserializer weatherDeserializer = new WeatherDeserializer();
        return weatherDeserializer.weatherDeserializer(openWeatherMapProcessor.weatherPetition(coordinates, time));
    }

}
