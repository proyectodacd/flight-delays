package com.duodinamico.aviationstackfeeder.domain.schema;

import com.google.gson.annotations.SerializedName;

public class FlightDescription {
    @SerializedName("flight_date")
    private final String flightDate;
    @SerializedName("flight_status")
    private final String flightStatus;
    private final Departure departure;
    private final Arrival arrival;
    @SerializedName("flight")
    private final FlightIdentifier flightIdentifier;

    public FlightDescription(Departure departure, Arrival arrival, String flightDate, String flightStatus, FlightIdentifier flightIdentifier) {
        this.departure = departure;
        this.arrival = arrival;
        this.flightDate = flightDate;
        this.flightStatus = flightStatus;
        this.flightIdentifier = flightIdentifier;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public Departure getDeparture() {
        return departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public FlightIdentifier getFlightId() {
        return flightIdentifier;
    }
}

