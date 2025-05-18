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

}
