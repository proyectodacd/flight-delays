package com.duodinamico.controller;

import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.persistency.AirportToCoordinates;
import com.duodinamico.controller.persistency.WeatherStore;
import com.duodinamico.controller.persistency.UnixConverter;


import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherController {
    private OpenWeatherMapProvider openWeatherMapProvider;
    private WeatherStore weatherStore;
    private TaskScheduler taskScheduler;
    private AirportToCoordinates airportToCoordinates;
    private UnixConverter unixConverter;

    public WeatherController(OpenWeatherMapProvider openWeatherMapProvider, WeatherStore weatherStore, TaskScheduler taskScheduler, AirportToCoordinates airportToCoordinates) {
        this.openWeatherMapProvider = openWeatherMapProvider;
        this.weatherStore = weatherStore;
        this.taskScheduler = taskScheduler;
        this.airportToCoordinates = airportToCoordinates;
        this.unixConverter = new UnixConverter();
    }

    public void execute() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable tarea2 = () -> { runnableCreator(); };

        taskScheduler.programarTarea(scheduler, tarea2, 12, 43);
        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runnableCreator() {
        System.out.println("Ejecutando WeatherController a las: " + LocalDateTime.now());
        for (String airportIata : List.of("MAD","AMS","JFK","ZRH")){
            weatherStore.saveWeather(openWeatherMapProvider.weatherProvider(airportToCoordinates.getAirportCoordinates(airportIata), unixConverter.findUnixOfYesterday()), airportIata);
        }
        System.out.println("Clima de aeropuertos guardados.");
    }
}


