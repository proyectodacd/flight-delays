package com.duodinamico.openweathermapfeeder.domain.schema;

import java.util.List;

public class WeatherResponse {

    private final String message;
    private final String code;
    private final int city_id;
    private final double calctime;
    private final int cnt;
    private final List<WeatherInformation> list;

    public WeatherResponse(String message, String code, int city_id, double calctime, int cnt, List<WeatherInformation> list) {
        this.message = message;
        this.code = code;
        this.city_id = city_id;
        this.calctime = calctime;
        this.cnt = cnt;
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public int getCity_id() {
        return city_id;
    }

    public double getCalctime() {
        return calctime;
    }

    public int getCnt() {
        return cnt;
    }

    public List<WeatherInformation> getList() {
        return list;
    }
}
