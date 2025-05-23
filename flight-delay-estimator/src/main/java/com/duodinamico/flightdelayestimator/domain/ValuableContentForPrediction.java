package com.duodinamico.flightdelayestimator.domain;

public class ValuableContentForPrediction {

    private String standardTime;
    private String timezone;
    private String airportType;
    private String airportName;
    private double temperature;
    private int percentageOfClouds;
    private double windSpeed;
    private double windDirection;
    private double precipitations;
    private double snow;
    private String description;
    private int delay;

    public ValuableContentForPrediction(String standardTime, String timezone, String airportType, String airportName, double temperature, int percentageOfClouds, double windSpeed, double windDirection, double precipitations, double snow, String description, int delay) {
        this.standardTime = standardTime;
        this.timezone = timezone;
        this.airportType = airportType;
        this.airportName = airportName;
        this.temperature = temperature;
        this.percentageOfClouds = percentageOfClouds;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.precipitations = precipitations;
        this.snow = snow;
        this.description = description;
        this.delay = delay;
    }

    public String getStandardTime() {
        return standardTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getAirportType() {
        return airportType;
    }

    public String getAirportName() {
        return airportName;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getPercentageOfClouds() {
        return percentageOfClouds;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public double getPrecipitations() {
        return precipitations;
    }

    public double getSnow() {
        return snow;
    }

    public String getDescription() {
        return description;
    }

    public int getDelay() {
        return delay;
    }
}

