package com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer;

import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;
import com.duodinamico.openweathermapfeeder.domain.model.Coordinates;
import com.duodinamico.openweathermapfeeder.infrastructure.ports.WeatherProvider;

public class OpenWeatherMapProvider implements WeatherProvider {

    private final String[] preferredAirports;
    private final OpenWeatherMapProcessor openWeatherMapProcessor;
    private final WeatherJSONParser weatherJSONParser;

    public OpenWeatherMapProvider(OpenWeatherMapProcessor openWeatherMapProcessor, WeatherJSONParser weatherJSONParser, String[] preferredAirports) {
        this.openWeatherMapProcessor = openWeatherMapProcessor;
        this.weatherJSONParser = weatherJSONParser;
        this.preferredAirports = preferredAirports;
    }

    public String[] getPreferredAirports() {
        return preferredAirports;
    }

    @Override
    public WeatherResponse provideWeather(Coordinates coordinates, String time) {
        return this.weatherJSONParser.weatherDeserializer(this.openWeatherMapProcessor.weatherPetition(coordinates, time));
    }

}
