package com.duodinamico.controller.eventintegration;

import java.time.Instant;

public class WeatherEvent {

    private final String ts;
    private final String ss;
    private final int time;
    private final float temperature;
    private final float feelsLike;
    private final float windSpeed;
    private final int windDirection;
    private final int percentageOfClouds;
    private final float precipitation;
    private final float snowmeasurement;
    private final String weatherDescription;

    public WeatherEvent(int time, float temperature, float feelsLike, float windSpeed, int windDirection, int percentageOfClouds, float precipitation, float snowmeasurement, String weatherDescription) {
        this.ts = Instant.now().toString();
        this.ss = "OpenWeatherMapFeeder";
        this.time = time;
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

    public int getTime() {
        return time;
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
