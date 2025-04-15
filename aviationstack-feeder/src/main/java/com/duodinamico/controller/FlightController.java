package com.duodinamico.controller;


import com.duodinamico.controller.apiconsumer.AviationStackProvider;
import com.duodinamico.controller.persistency.FlightSQLStore;

public class FlightController {
    private AviationStackProvider aviationStackProvider;
    private FlightSQLStore flightSQLStore;

    public FlightController(String[] apiKeys, String databasePath) {
        this.aviationStackProvider = new AviationStackProvider(apiKeys);
        this.flightSQLStore = new FlightSQLStore(databasePath);
    }

    public void execute() {
        flightSQLStore.saveFlights(aviationStackProvider.flightProvider());
        System.out.println("Vuelos guardados.");
    }
}
