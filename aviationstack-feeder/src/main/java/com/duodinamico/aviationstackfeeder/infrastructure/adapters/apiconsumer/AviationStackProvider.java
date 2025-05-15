
package com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer;

import com.duodinamico.aviationstackfeeder.infrastructure.ports.FlightProvider;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;

public class AviationStackProvider implements FlightProvider {

    private final String[] apiKeys;
    private final String[] preferredAirports;

    public AviationStackProvider(String[] apiKeys, String[] preferredAirports) {
        this.apiKeys = apiKeys;
        this.preferredAirports = preferredAirports;
    }

    public String[] getPreferredAirports() {
        return preferredAirports;
    }

    @Override
    public FlightResponse flightProvider(String airportType, String airportIata) {
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor(this.apiKeys);
        FlightDeserializer flightDeserializer = new FlightDeserializer();
        return flightDeserializer.flightDeserializer(aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition(airportType, airportIata),airportType,airportIata));
    }

}

