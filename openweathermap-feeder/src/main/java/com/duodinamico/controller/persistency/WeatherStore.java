package com.duodinamico.controller.persistency;

import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;

public interface WeatherStore {
    void saveWeather(WeatherResponse weather, String city);
}
