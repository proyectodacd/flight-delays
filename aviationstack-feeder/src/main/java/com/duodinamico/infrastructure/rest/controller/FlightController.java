package com.duodinamico.infrastructure.rest.controller;


import com.duodinamico.infrastructure.adapters.apiconsumer.AviationStackProvider;
import com.duodinamico.domain.ports.FlightStore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlightController {
    private AviationStackProvider aviationStackProvider;
    private FlightStore flightStore;
    private TaskScheduler taskScheduler;

    public FlightController(AviationStackProvider aviationStackProvider, FlightStore flightStore,
                            TaskScheduler taskScheduler) {
        this.aviationStackProvider = aviationStackProvider;
        this.taskScheduler = taskScheduler;
        this.flightStore = flightStore;
    }

    public void execute() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable tarea = () -> { runnableCreator(); };
        this.taskScheduler.programarTarea(scheduler, tarea, 16, 23);
        this.taskScheduler.programarTarea(scheduler, tarea, 23, 32);

        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runnableCreator(){
        System.out.println("Ejecutando FlightController a las: " + LocalDateTime.now());
        for (String airportType: List.of("dep_iata","arr_iata")) {
            for (String airportIata: List.of("MAD","AMS","JFK","ZRH")) {
                flightStore.saveFlights(aviationStackProvider.flightProvider(airportType, airportIata));
            }
        }
        System.out.println("Vuelos guardados.");
    }

}
