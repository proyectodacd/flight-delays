package com.duodinamico.controller.apiconsumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class WeatherDeserializer {

    public WeatherInformation weatherDeserializer(String json) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        WeatherInformation response = gson.fromJson(json, WeatherInformation.class);

        return response;

    }
}
