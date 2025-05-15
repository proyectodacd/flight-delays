package com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer;

import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;
import com.duodinamico.openweathermapfeeder.domain.model.Coordinates;
import com.duodinamico.openweathermapfeeder.infrastructure.ports.WeatherProvider;

public class OpenWeatherMapProvider implements WeatherProvider {

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
