package com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer;

import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeatherJSONParser {

    public WeatherResponse weatherDeserializer(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        WeatherResponse response = gson.fromJson(json, WeatherResponse.class);
        return response;
    }

}
