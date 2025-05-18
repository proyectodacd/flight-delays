package com.duodinamico.aviationstackfeeder.infrastructure.ports;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;

public interface FlightProvider {
    FlightResponse flightProvider(String airportType, String airportIata);
    String[] getPreferredAirports();
}
