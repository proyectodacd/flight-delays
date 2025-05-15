package com.duodinamico.openweathermapfeeder.infrastructure.ports;

import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;

public interface WeatherStore {
    void saveWeather(WeatherResponse weather, String city);
}
