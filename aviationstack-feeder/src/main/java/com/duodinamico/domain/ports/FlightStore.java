package com.duodinamico.domain.ports;

import com.duodinamico.infrastructure.adapters.apiconsumer.schema.FlightResponse;

import java.util.ArrayList;

public interface FlightStore<T> {
    public void saveFlights (FlightResponse flightResponse);
}
