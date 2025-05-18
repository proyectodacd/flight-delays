package com.duodinamico.openweathermapfeeder.application.usecases.collectorandstore;

import com.duodinamico.openweathermapfeeder.infrastructure.ports.WeatherProvider;
import com.duodinamico.openweathermapfeeder.tools.scheduler.TaskScheduler;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.openweathermapfeeder.tools.converters.AirportToCoordinates;
import com.duodinamico.openweathermapfeeder.infrastructure.ports.WeatherStore;
import com.duodinamico.openweathermapfeeder.tools.converters.UnixUtils;


import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherController {
    private final WeatherProvider weatherProvider;
    private WeatherStore weatherStore;
    private TaskScheduler taskScheduler;
    private AirportToCoordinates airportToCoordinates;
    private UnixUtils unixUtils;

    public WeatherController(WeatherProvider weatherProvider, WeatherStore weatherStore, TaskScheduler taskScheduler, AirportToCoordinates airportToCoordinates, UnixUtils unixUtils) {
        this.weatherProvider = weatherProvider;
        this.weatherStore = weatherStore;
        this.taskScheduler = taskScheduler;
        this.airportToCoordinates = airportToCoordinates;
        this.unixUtils = unixUtils;
    }

    public void execute() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable tarea2 = runnableCreator();

        this.taskScheduler.scheduleTask(scheduler, tarea2, 11, 0);
        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Runnable runnableCreator() {
        return () -> { System.out.println("Ejecutando WeatherController a las: " + LocalDateTime.now());
        for (String airportIata : List.of(this.weatherProvider.getPreferredAirports())){
            this.weatherStore.saveWeather(this.weatherProvider.provideWeather(this.airportToCoordinates.getAirportCoordinates(airportIata), this.unixUtils.findUnixOfYesterday()), airportIata);
        }
        System.out.println("Clima de aeropuertos guardados.");
        };
    }

}


