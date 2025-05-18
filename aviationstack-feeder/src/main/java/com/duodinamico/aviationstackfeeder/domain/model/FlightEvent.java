package com.duodinamico.aviationstackfeeder.domain.model;

import java.time.Instant;

public class FlightEvent {
    private final String ts;
    private final String ss;
    private final String flightIcao;
    private final String flightDate;
    private final String departureAirport;
    private final String departureTimezone;
    private final String departureIata;
    private final int departureDelay;
    private final String estimatedDepartureTime;
    private final String arrivalAirport;
    private final String arrivalTimezone;
    private final String arrivalIata;
    private final int arrivalDelay;
    private final String estimatedArrivalTime;


    public FlightEvent(String flightIcao, String flightDate, String departureAirport, String departureTimezone, String departureIata, int departureDelay, String estimatedDepartureTime, String arrivalAirport, String arrivalTimezone, String arrivalIata, int arrivalDelay, String estimatedArrivalTime) {
        this.ts = Instant.now().toString();
        this.ss = "AviationStackFeeder";
        this.flightIcao = flightIcao;
        this.flightDate = flightDate;
        this.departureAirport = departureAirport;
        this.departureTimezone = departureTimezone;
        this.departureIata = departureIata;
        this.departureDelay = departureDelay;
        this.estimatedDepartureTime = estimatedDepartureTime;
        this.arrivalAirport = arrivalAirport;
        this.arrivalTimezone = arrivalTimezone;
        this.arrivalIata = arrivalIata;
        this.arrivalDelay = arrivalDelay;
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public String getTs() {
        return ts;
    }

    public String getSs() {
        return ss;
    }

    public String getFlightIcao() {
        return flightIcao;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getDepartureTimezone() {
        return departureTimezone;
    }

    public String getDepartureIata() {
        return departureIata;
    }

    public int getDepartureDelay() {
        return departureDelay;
    }

    public String getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getArrivalTimezone() {
        return arrivalTimezone;
    }

    public String getArrivalIata() {
        return arrivalIata;
    }

    public int getArrivalDelay() {
        return arrivalDelay;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

}
