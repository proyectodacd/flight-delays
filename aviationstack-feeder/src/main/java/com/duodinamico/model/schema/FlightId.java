package com.duodinamico.model.schema;

import com.google.gson.annotations.SerializedName;

public class FlightId {
    @SerializedName("icao")
    private final String flighticao;
    public FlightId(String flightId) {
        this.flighticao = flightId;
    }

    public String getFlightIcao() {
        return flighticao;
    }
}
