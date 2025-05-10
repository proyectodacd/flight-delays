package com.duodinamico.domain.ports;
import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.domain.model.FlightModel;
import com.duodinamico.infrastructure.adapters.apiconsumer.schema.FlightResponse;

import java.util.ArrayList;

public interface FlightProvider {
    public FlightResponse flightProvider(String airportType, String airportIata);
}
