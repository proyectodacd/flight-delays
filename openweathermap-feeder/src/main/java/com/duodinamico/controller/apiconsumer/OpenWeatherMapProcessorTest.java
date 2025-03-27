package com.duodinamico.controller.apiconsumer;

public class OpenWeatherMapProcessorTest {
    public static void main(String[] args) {
        OpenWeatherMapProcessor processor = new OpenWeatherMapProcessor();
        System.out.println(processor.weatherPetition("27.9319","-15.3866","1743091200",args));
    }
}
