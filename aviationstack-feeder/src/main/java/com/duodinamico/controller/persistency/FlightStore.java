package com.duodinamico.controller.persistency;
import com.duodinamico.controller.model.FlightModel;

import java.util.ArrayList;

public interface FlightStore {
    public void saveFlights (ArrayList<FlightModel> flights);
    public ArrayList<FlightModel> loadFlights ();
}
