package com.duodinamico.controller.apiconsumer;

import com.duodinamico.controller.WeatherMapper;
import com.duodinamico.controller.model.WeatherResult;
import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeatherDeserializer {

    public WeatherResult weatherDeserializer(String json) {

        WeatherMapper mapper = new WeatherMapper();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        WeatherResponse response = gson.fromJson(json, WeatherResponse.class);

        return mapper.getWeatherResult(response);

    }
}
