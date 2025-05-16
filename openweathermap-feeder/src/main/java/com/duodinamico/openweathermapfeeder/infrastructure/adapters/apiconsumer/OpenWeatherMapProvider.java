package com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer;

import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;
import com.duodinamico.openweathermapfeeder.domain.model.Coordinates;
import com.duodinamico.openweathermapfeeder.infrastructure.ports.WeatherProvider;

public class OpenWeatherMapProvider implements WeatherProvider {

    private final String[] preferredAirports;
    private final OpenWeatherMapProcessor openWeatherMapProcessor;
    private final WeatherDeserializer weatherDeserializer;

    public OpenWeatherMapProvider(OpenWeatherMapProcessor openWeatherMapProcessor, WeatherDeserializer weatherDeserializer, String[] preferredAirports) {
        this.openWeatherMapProcessor = openWeatherMapProcessor;
        this.weatherDeserializer = weatherDeserializer;
        this.preferredAirports = preferredAirports;
    }

    public String[] getPreferredAirports() {
        return preferredAirports;
    }

    @Override
    public WeatherResponse provideWeather(Coordinates coordinates, String time) {
        return this.weatherDeserializer.weatherDeserializer(this.openWeatherMapProcessor.weatherPetition(coordinates, time));
    }

}
