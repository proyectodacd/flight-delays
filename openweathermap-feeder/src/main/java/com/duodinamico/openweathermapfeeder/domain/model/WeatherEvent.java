package com.duodinamico.openweathermapfeeder.domain.model;

import java.time.Instant;

public class WeatherEvent {

    private final String ts;
    private final String ss;
    private final String city;
    private final int dataCalculationTime;
    private final String standardTime;
    private final float temperature;
    private final int percentageOfClouds;
    private final float windSpeed;
    private final int windDirection;
    private final float precipitation;
    private final float snowmeasurement;
    private final String weatherDescription;

    public WeatherEvent(String city, int time, String standardTime, float temperature, float windSpeed, int windDirection, int percentageOfClouds, float precipitation, float snowmeasurement, String weatherDescription) {
        this.ts = Instant.now().toString();
        this.ss = "OpenWeatherMapFeeder";
        this.city = city;
        this.dataCalculationTime = time;
        this.standardTime = standardTime;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.percentageOfClouds = percentageOfClouds;
        this.precipitation = precipitation;
        this.snowmeasurement = snowmeasurement;
        this.weatherDescription = weatherDescription;
    }

    public String getSs() {
        return ss;
    }

    public String getCity() {
        return this.city;
    }

}
