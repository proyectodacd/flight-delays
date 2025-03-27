package com.duodinamico.controller.persistency;
import com.duodinamico.model.Flight;

import java.util.List;

public interface FlightStore {
    public void saveFlights (List<Flight> flights, String[] args);
    public Flight loadFlight (String flightIcao, String date, String[] args);
}
