package com.duodinamico.controller.persistency;

import com.duodinamico.domain.model.FlightModel;
import java.util.ArrayList;

public interface WeatherStore {

    void saveDepartureWeather (ArrayList<FlightModel> flights);
    void saveArrivalWeather (ArrayList<FlightModel> flights);

}
