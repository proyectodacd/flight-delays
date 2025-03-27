package com.duodinamico.controller.persistency;

import com.duodinamico.model.WeatherResult;

public interface WeatherStore {

    public void saveWeather (WeatherResult weather, String[] args);

}
