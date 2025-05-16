
package com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer;

import com.duodinamico.aviationstackfeeder.infrastructure.ports.FlightProvider;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;

public class AviationStackProvider implements FlightProvider {

    private final AviationStackProcessor aviationStackProcessor;
    private final FlightDeserializer flightDeserializer;
    private final String[] preferredAirports;

    public AviationStackProvider(AviationStackProcessor aviationStackProcessor, FlightDeserializer flightDeserializer, String[] preferredAirports) {
        this.aviationStackProcessor = aviationStackProcessor;
        this.flightDeserializer = flightDeserializer;
        this.preferredAirports = preferredAirports;
    }

    public String[] getPreferredAirports() {
        return preferredAirports;
    }

    @Override
    public FlightResponse flightProvider(String airportType, String airportIata) {
        return this.flightDeserializer.flightDeserializer(this.aviationStackProcessor.petitionValidator(this.aviationStackProcessor.flightsPetition(airportType, airportIata),airportType,airportIata));
    }

}

