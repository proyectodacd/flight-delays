package com.duodinamico.controller.apiconsumer;

import com.duodinamico.model.WeatherResult;

public class WeatherDeserializerTest {

    public static void main(String[] args) {

        OpenWeatherMapProcessor processor = new OpenWeatherMapProcessor();
        WeatherDeserializer deserializer = new WeatherDeserializer();

            WeatherResult result = deserializer.weatherDeserializer( processor.weatherPetition("27.75417","-97.17333","1743091200",args));
        System.out.println(result.getTime());
        System.out.println(result.getTemperature());
        System.out.println(result.getFeelsLike());
        System.out.println(result.getWindSpeed());
        System.out.println(result.getWindDirection());
        System.out.println(result.getPercentageOfClouds());
        System.out.println(result.getPrecipitation());
        System.out.println(result.getSnowmeasurement());
        System.out.println(result.getWeatherDescription());

    }

}
