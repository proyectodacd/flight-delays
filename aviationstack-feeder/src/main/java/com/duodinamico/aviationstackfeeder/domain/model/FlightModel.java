package com.duodinamico.aviationstackfeeder.domain.model;

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

    public FlightModel(String flightIcao, String flightDate, String flightStatus, String departureAirport, String departureTimezone, String departureIata, String departureIcao, int departureDelay, String estimatedDepartureTime, String actualDepartureTime, String arrivalAirport, String arrivalTimezone, String arrivalIata, String arrivalIcao, int arrivalDelay, String estimatedArrivalTime, String actualArrivalTime, String liveStatusTimeStamp, float latitude, float longitude, float altitude, float horizontalSpeed, float verticalSpeed, boolean isOnGround) {
        this.flightIcao = flightIcao;
        this.flightDate = flightDate;
        this.flightStatus = flightStatus;
        this.departureAirport = departureAirport;
        this.departureTimezone = departureTimezone;
        this.departureIata = departureIata;
        this.departureIcao = departureIcao;
        this.departureDelay = departureDelay;
        this.estimatedDepartureTime = estimatedDepartureTime;
        this.actualDepartureTime = actualDepartureTime;
        this.arrivalAirport = arrivalAirport;
        this.arrivalTimezone = arrivalTimezone;
        this.arrivalIata = arrivalIata;
        this.arrivalIcao = arrivalIcao;
        this.arrivalDelay = arrivalDelay;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.actualArrivalTime = actualArrivalTime;
        this.liveStatusTimeStamp = liveStatusTimeStamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.horizontalSpeed = horizontalSpeed;
        this.verticalSpeed = verticalSpeed;
        this.isOnGround = isOnGround;
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
