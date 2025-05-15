package com.duodinamico.aviationstackfeeder.infrastructure.ports;

import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;

public interface FlightStore<T> {
    public void saveFlights (FlightResponse flightResponse);
}
