package com.duodinamico.controller.persistency;
import com.duodinamico.model.FlightModel;
import com.duodinamico.model.schema.Flight;

import java.util.ArrayList;
import java.util.List;

public interface FlightStore {
    public void saveFlights (ArrayList<FlightModel> flights);
    public ArrayList<FlightModel> loadFlights ();
}
