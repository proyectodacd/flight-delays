package com.duodinamico.openweathermapfeeder.domain.model;

public class WeatherResult {

    private final String city;
    private final int dataCalculationTime;
    private final float temperature;
    private final float windSpeed;
    private final int windDirection;
    private final int percentageOfClouds;
    private final float precipitation;
    private final float snowmeasurement;
    private final String weatherDescription;

    public WeatherResult(String city, int dataCalculationTime, float temperature, float windSpeed, int windDirection, int percentageOfClouds, float precipitation, float snowmeasurement, String weatherDescription) {
        this.city = city;
        this.dataCalculationTime = dataCalculationTime;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.percentageOfClouds = percentageOfClouds;
        this.precipitation = precipitation;
        this.snowmeasurement = snowmeasurement;
        this.weatherDescription = weatherDescription;
    }

    public String getCity() {
        return city;
    }

    public int getDataCalculationTime() {
        return dataCalculationTime;
    }

    public float getTemperature() {
        return temperature;
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
