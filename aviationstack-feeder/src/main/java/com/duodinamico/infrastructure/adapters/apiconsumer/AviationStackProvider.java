
package com.duodinamico.infrastructure.adapters.apiconsumer;

import com.duodinamico.domain.ports.FlightProvider;
import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.domain.model.FlightModel;
import com.duodinamico.infrastructure.adapters.apiconsumer.schema.FlightResponse;

import java.util.ArrayList;

public class AviationStackProvider implements FlightProvider {

    private final String[] apiKeys;

    public AviationStackProvider(String[] apiKeys) {
        this.apiKeys = apiKeys;
    }

    @Override
    public FlightResponse flightProvider(String airportType, String airportIata) {
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor(this.apiKeys);
        FlightDeserializer flightDeserializer = new FlightDeserializer();
        return flightDeserializer.flightDeserializer(aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition(airportType, airportIata),airportType,airportIata));
    }

}

