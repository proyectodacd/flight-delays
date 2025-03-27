package com.duodinamico.controller.apiconsumer;
import com.duodinamico.model.Flight;

import java.util.List;

public interface FlightProvider {
    List<Flight> flightProvider(String[] args);
}
