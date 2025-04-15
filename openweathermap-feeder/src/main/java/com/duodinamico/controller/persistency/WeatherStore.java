package com.duodinamico.controller.persistency;

import com.duodinamico.controller.model.FlightModel;

public interface WeatherStore {

    public void saveWeather (FlightModel flight);

}
