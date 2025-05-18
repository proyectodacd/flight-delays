package com.duodinamico.openweathermapfeeder.infrastructure.ports;

import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;
import com.duodinamico.openweathermapfeeder.domain.model.Coordinates;

public interface WeatherProvider {
    WeatherResponse provideWeather(Coordinates coordinates, String time);
    String[] getPreferredAirports();
}
