package com.duodinamico.controller.eventintegration;

import com.duodinamico.controller.persistency.UnixConverter;
import com.duodinamico.controller.persistency.UnixToUTCDateFormatter;

import java.time.Instant;

public class WeatherEvent {

    private final String ts;
    private final String ss;
    private final String city;
    private final int dataCalculationTime;
    private final String standardTime;
    private final float temperature;
    private final int percentageOfClouds;
    private final float feelsLike;
    private final float windSpeed;
    private final int windDirection;
    private final float precipitation;
    private final float snowmeasurement;
    private final String weatherDescription;


    public WeatherEvent(String city, int time, String standardTime, float temperature, float feelsLike, float windSpeed, int windDirection, int percentageOfClouds, float precipitation, float snowmeasurement, String weatherDescription) {
        this.ts = Instant.now().toString();
        this.ss = "OpenWeatherMapFeeder";
        this.city = city;
        this.dataCalculationTime = time;
        this.standardTime = standardTime;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.percentageOfClouds = percentageOfClouds;
        this.precipitation = precipitation;
        this.snowmeasurement = snowmeasurement;
        this.weatherDescription = weatherDescription;
    }

    public String getTs() {
        return ts;
    }

    public String getSs() {
        return ss;
    }


    public String getCity() {
        return this.city;
    }

    public int getDataCalculationTime() {
        return this.dataCalculationTime;
    }

    public String getStandardTime() {
        return this.standardTime;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public int getPercentageOfClouds() {
        return percentageOfClouds;
    }

    public float getPrecipitation() {
        return precipitation;
    }

    public float getSnowmeasurement() {
        return snowmeasurement;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }
}
