
package com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer;

import com.duodinamico.aviationstackfeeder.infrastructure.ports.FlightProvider;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;

public class AviationStackProvider implements FlightProvider {

    private final AviationStackProcessor aviationStackProcessor;
    private final FlightJSONParser flightJSONParser;
    private final String[] preferredAirports;

    public AviationStackProvider(AviationStackProcessor aviationStackProcessor, FlightJSONParser flightJSONParser, String[] preferredAirports) {
        this.aviationStackProcessor = aviationStackProcessor;
        this.flightJSONParser = flightJSONParser;
        this.preferredAirports = preferredAirports;
    }

    public String[] getPreferredAirports() {
        return preferredAirports;
    }

    @Override
    public FlightResponse flightProvider(String airportType, String airportIata) {
        return this.flightJSONParser.flightDeserializer(this.aviationStackProcessor.petitionValidator(this.aviationStackProcessor.flightsPetition(airportType, airportIata),airportType,airportIata));
    }

}

