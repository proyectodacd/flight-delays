package com.duodinamico.model;

import com.duodinamico.model.schema.Flight;

public class FlightModel {

    private final String flightIcao;
    private final String flightDate;
    private final String flightStatus;
    private final String departureAirport;
    private final String departureTimezone;
    private final String departureIata;
    private final String departureIcao;
    private final int departureDelay;
    private final String estimatedDepartureTime;
    private final String actualDepartureTime;
    private final String arrivalAirport;
    private final String arrivalTimezone;
    private final String arrivalIata;
    private final String arrivalIcao;
    private final int arrivalDelay;
    private final String estimatedArrivalTime;
    private final String actualArrivalTime;
    private final String liveStatusTimeStamp;
    private final float latitude;
    private final float longitude;
    private final float altitude;
    private final float horizontalSpeed;
    private final float verticalSpeed;
    private final boolean isOnGround;

    public FlightModel(Flight flight) {
        this.flightIcao = flight.getFlightId().getFlightIcao();
        this.flightDate = flight.getFlightDate();
        this.flightStatus = flight.getFlightStatus();
        this.departureAirport = flight.getDeparture().getAirport();
        this.departureTimezone = flight.getDeparture().getTimezone();
        this.departureIata = flight.getDeparture().getIata();
        this.departureIcao = flight.getDeparture().getIcao();
        this.departureDelay = flight.getDeparture().getDelay();
        this.estimatedDepartureTime = flight.getDeparture().getScheduled();
        this.actualDepartureTime = flight.getDeparture().getActual();
        this.arrivalAirport = flight.getArrival().getAirport();
        this.arrivalTimezone = flight.getArrival().getTimezone();
        this.arrivalIata = flight.getArrival().getIata();
        this.arrivalIcao = flight.getArrival().getIcao();
        this.arrivalDelay = flight.getArrival().getDelay();
        this.estimatedArrivalTime = flight.getArrival().getScheduled();
        this.actualArrivalTime = flight.getArrival().getActual();
        this.liveStatusTimeStamp = flight.getLiveStatus() == null ? null : flight.getLiveStatus().getUpdated();
        this.latitude = flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getLatitude();
        this.longitude = flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getLongitude();
        this.altitude = flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getAltitude();
        this.horizontalSpeed = flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getSpeedHorizontal();
        this.verticalSpeed = flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getSpeedVertical();
        this.isOnGround = flight.getLiveStatus() == null ? false : flight.getLiveStatus().getIsGround();
    }

    public String getFlightIcao() {
        return flightIcao;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public String getFlightStatus() {
        return flightStatus;
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

    public String getDepartureIcao() {
        return departureIcao;
    }

    public int getDepartureDelay() {
        return departureDelay;
    }

    public String getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    public String getActualDepartureTime() {
        return actualDepartureTime;
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

    public String getArrivalIcao() {
        return arrivalIcao;
    }

    public int getArrivalDelay() {
        return arrivalDelay;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public String getActualArrivalTime() {
        return actualArrivalTime;
    }

    public String getLiveStatusTimeStamp() {
        return liveStatusTimeStamp;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public float getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public float getVerticalSpeed() {
        return verticalSpeed;
    }

    public boolean isOnGround() {
        return isOnGround;
    }
}
