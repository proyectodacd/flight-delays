package com.duodinamico.controller;
import com.duodinamico.model.Flight;

import java.util.List;

public interface FlightProvider {
    List<Flight> flightProvider(String[] args);
}
