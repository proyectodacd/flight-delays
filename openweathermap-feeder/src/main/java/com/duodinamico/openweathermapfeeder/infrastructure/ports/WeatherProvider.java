package com.duodinamico.openweathermapfeeder.infrastructure.ports;

import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;
import com.duodinamico.openweathermapfeeder.domain.model.Coordinates;

public interface WeatherProvider {
    WeatherResponse weatherProvider(Coordinates coordinates, String time);
}
