package com.duodinamico.aviationstackfeeder.application.usecases.collectorandstore;


import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProvider;
import com.duodinamico.aviationstackfeeder.infrastructure.ports.FlightStore;
import com.duodinamico.aviationstackfeeder.tools.scheduler.TaskScheduler;

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
        Runnable tarea = () -> { runnableCreatorold(); };
        this.taskScheduler.programarTarea(scheduler, tarea, 22, 39);
        this.taskScheduler.programarTarea(scheduler, tarea, 23, 32);

        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Runnable runnableCreator() {
        return () -> {
            System.out.println("Ejecutando FlightController a las: " + LocalDateTime.now());
            for (String airportType : List.of("dep_iata", "arr_iata")) {
                for (String airportIata : List.of("MAD", "AMS", "JFK", "ZRH")) {
                    flightStore.saveFlights(aviationStackProvider.flightProvider(airportType, airportIata));
                }
            }
            System.out.println("Vuelos guardados.");
        };
    }

        public void runnableCreatorold(){

                System.out.println("Ejecutando FlightController a las: " + LocalDateTime.now());
                for (String airportType: List.of("dep_iata","arr_iata")) {
                    System.out.println(airportType);
                    for (String airportIata: List.of("MAD","AMS","JFK","ZRH")) {
                        flightStore.saveFlights(aviationStackProvider.flightProvider(airportType, airportIata));
                    }
                }
                System.out.println("Vuelos guardados.");
        }

    }


