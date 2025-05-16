package com.duodinamico.aviationstackfeeder.domain.schema;

import com.google.gson.annotations.SerializedName;

public class FlightIdentifier {
    @SerializedName("icao")
    private final String flighticao;
    public FlightIdentifier(String flightId) {
        this.flighticao = flightId;
    }

    public String getFlightIcao() {
        return flighticao;
    }
}
