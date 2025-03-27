package com.duodinamico.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherInformation {
    @SerializedName("dt")
    private final int time;
    @SerializedName("main")
    private final ThermalConditions thermalConditions;
    private final Wind wind;
    private final Clouds clouds;
    @SerializedName("weather")
    private final List<Description> description;

    public WeatherInformation(Wind wind, Clouds clouds, int time, ThermalConditions thermalConditions, List<Description> description) {
        this.wind = wind;
        this.clouds = clouds;
        this.time = time;
        this.thermalConditions = thermalConditions;
        this.description = description;
    }

    public int getTime() {
        return time;
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
}
