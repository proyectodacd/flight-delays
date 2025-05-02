package com.duodinamico.controller;

import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.apiconsumer.WeatherProvider;
import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.eventintegration.WeatherEventSender;
import com.duodinamico.controller.persistency.AirportToCoordinates;
import com.duodinamico.controller.persistency.WeatherStore;
import com.duodinamico.domain.ports.FlightStore;
import com.duodinamico.infrastructure.adapters.sqlite.FlightSQLStore;
import com.duodinamico.controller.persistency.UnixConverter;
import com.duodinamico.controller.persistency.WeatherSQLStore;
import com.duodinamico.domain.model.FlightModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherController {
    private OpenWeatherMapProvider openWeatherMapProvider;
    private FlightStore flightStore;
    private WeatherStore weatherStore;
    private TaskScheduler taskScheduler;

    public WeatherController(OpenWeatherMapProvider openWeatherMapProvider, FlightStore flightStore, WeatherStore weatherStore, TaskScheduler taskScheduler) {
        this.openWeatherMapProvider = openWeatherMapProvider;
        this.flightStore = flightStore;
        this.weatherStore = weatherStore;
        this.taskScheduler = taskScheduler;
    }

    public void execute() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarea2 = () -> {
            System.out.println("Ejecutando WeatherController a las: " + LocalDateTime.now());

            try {
                weatherStore.saveDepartureWeather(flightStore.loadFlights());
                weatherStore.saveArrivalWeather(flightStore.loadFlights());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Clima de aeropuertos guardados.");
        };

        taskScheduler.programarTarea(scheduler, tarea2, 19, 35);

        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


