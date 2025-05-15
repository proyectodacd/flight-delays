package com.duodinamico.openweathermapfeeder.domain.schema;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherInformation {
    @SerializedName("dt")
    private final int dataCalculationTime;
    @SerializedName("main")
    private final ThermalConditions thermalConditions;
    private final Wind wind;
    private final Clouds clouds;
    @SerializedName("weather")
    private final List<Description> description;
    private final Rain rain;
    private final Snow snow;

    public WeatherInformation(Wind wind, Clouds clouds, int dataCalculationTime, ThermalConditions thermalConditions, List<Description> description, Rain rain, Snow snow) {
        this.wind = wind;
        this.clouds = clouds;
        this.dataCalculationTime = dataCalculationTime;
        this.thermalConditions = thermalConditions;
        this.description = description;
        this.rain = rain;
        this.snow = snow;
    }

    public int getDataCalculationTime() {
        return dataCalculationTime;
    }

    public ThermalConditions getThermalConditions() {
        return thermalConditions;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public List<Description> getDescription() {
        return description;
    }

    public Rain getRain() {
        return rain == null ? new Rain(0) : rain;
    }

    public Snow getSnow() {
        return snow == null ? new Snow(0) : snow;
    }

}
