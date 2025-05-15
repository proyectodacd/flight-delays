package com.duodinamico.openweathermapfeeder.domain.schema;

import com.google.gson.annotations.SerializedName;

public class ThermalConditions {

    @SerializedName("temp")
    private float temperature;
    @SerializedName("feels_like")
    private float feelsLike;

    public ThermalConditions(float temperature, float feelsLike) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getFeelsLike() {
        return feelsLike;
    }
}
