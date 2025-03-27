package com.duodinamico.controller.apiconsumer;

import com.duodinamico.model.WeatherResult;
import com.duodinamico.model.schema.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class WeatherDeserializer {

    public WeatherResult weatherDeserializer(String json) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        WeatherResponse response = gson.fromJson(json, WeatherResponse.class);

        return new WeatherResult(response);

    }
}
