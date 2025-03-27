package com.duodinamico.model;

import com.duodinamico.model.schema.WeatherResponse;

public class WeatherResult {

    private final int time;
    private final float temperature;
    private final float feelsLike;
    private final float windSpeed;
    private final int windDirection;
    private final int percentageOfClouds;
    private final float precipitation;
    private final float snowmeasurement;
    private final String weatherDescription;

    public WeatherResult(WeatherResponse weatherResponse) {
        this.time = weatherResponse.getList().getFirst().getTime();
        this.temperature = weatherResponse.getList().getFirst().getThermalConditions().getTemperature();
        this.feelsLike = weatherResponse.getList().getFirst().getThermalConditions().getFeelsLike();
        this.windSpeed = weatherResponse.getList().getFirst().getWind().getSpeed();
        this.windDirection = weatherResponse.getList().getFirst().getWind().getDeg();
        this.percentageOfClouds = weatherResponse.getList().getFirst().getClouds().getCloudiness();
        this.precipitation = weatherResponse.getList().getFirst().getRain().getRainOneHour();
        this.snowmeasurement = weatherResponse.getList().getFirst().getSnow().getSnowOneHour();
        this.weatherDescription = weatherResponse.getList().getFirst().getDescription().getFirst().getDescription();

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
