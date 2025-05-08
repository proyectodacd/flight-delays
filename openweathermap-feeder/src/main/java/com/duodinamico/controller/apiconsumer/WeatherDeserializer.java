package com.duodinamico.controller.apiconsumer;

import com.duodinamico.controller.WeatherResultMapper;
import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeatherDeserializer {

    public WeatherResponse weatherDeserializer(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        WeatherResponse response = gson.fromJson(json, WeatherResponse.class);

        return response;
    }

}
