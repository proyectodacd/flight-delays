package com.duodinamico.openweathermapfeeder.domain.schema;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    private final int cnt;
    @SerializedName("list")
    private final List<WeatherInformation> weatherList;

    public WeatherResponse(int cnt, List<WeatherInformation> weatherList) {
        this.cnt = cnt;
        this.weatherList = weatherList;
    }

    public int getCnt() {
        return cnt;
    }

    public List<WeatherInformation> getList() {
        return weatherList;
    }
}
