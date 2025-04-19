package com.duodinamico.controller.apiconsumer.schema;

import com.google.gson.annotations.SerializedName;

public class Flight {
    @SerializedName("flight_date")
    private final String flightDate;
    @SerializedName("flight_status")
    private final String flightStatus;
    private final Departure departure;
    private final Arrival arrival;
    @SerializedName("flight")
    private final FlightId flightId;
    @SerializedName("live")
    private final LiveStatus liveStatus;

    public Flight(Departure departure, Arrival arrival, String flightDate, String flightStatus, LiveStatus liveStatus, FlightId flightId) {
        this.departure = departure;
        this.arrival = arrival;
        this.flightDate = flightDate;
        this.flightStatus = flightStatus;
        this.liveStatus = liveStatus == null ? null : liveStatus;
        this.flightId = flightId;
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

    public LiveStatus getLiveStatus() {
        return liveStatus;
    }

    public FlightId getFlightId() {
        return flightId;
    }
}

